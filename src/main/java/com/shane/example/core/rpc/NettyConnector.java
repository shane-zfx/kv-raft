package com.shane.example.core.rpc;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * rpc 组件，通过监听指定的端口，获取入站消息，并且可以跟指定的端点发送消息
 *
 * @author: shane
 * @date: 2023-08-29 10:18:04
 * @version: 1.0
 */
public class NettyConnector<T> implements Connector<T> {

    private static final Logger log = LoggerFactory.getLogger(NettyConnector.class);

    private final int port;
    private int status = 0;

    private final Map<EndpointPair, Channel> channelMap = new ConcurrentHashMap<>();

    private int workers;

    private NioEventLoopGroup workerGroup;

    public NettyConnector(int port) {
        this(port, 5);
    }

    public NettyConnector(int port, int workers) {
        this.port = port;
        this.workers = workers;
        workerGroup = new NioEventLoopGroup(this.workers);
    }

    @Override
    public synchronized void initial() {
        if (status != 0) {
            return;
        }
        ServerBootstrap serverBootstrap = new ServerBootstrap()
                .group(new NioEventLoopGroup(1), workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new ByteToMessageDecoder() {
                            @Override
                            protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {
                                int readableBytes = in.readableBytes();
                                if (readableBytes == 0) {
                                    return;
                                }

                                try {
                                    byte[] bytes = new byte[readableBytes];
                                    in.readBytes(bytes);
                                    String decodedString = new String(bytes, StandardCharsets.UTF_8);

                                    list.add(decodedString);
                                } catch (Exception ex) {
                                    throw new DecoderException("Failed to decode message", ex);
                                }
                            }
                        });
                        pipeline.addLast(new MessageToByteEncoder<String>() {
                            @Override
                            protected void encode(ChannelHandlerContext channelHandlerContext, String msg, ByteBuf byteBuf) throws Exception {
                                byteBuf.writeBytes(msg.getBytes(StandardCharsets.UTF_8));
                            }
                        });
                        pipeline.addLast(new ChannelProcessor());
                    }
                });
        try {
            ChannelFuture sync = serverBootstrap.bind(this.port).sync();
            this.status = 1;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send(T msg, Endpoint endpoint) {
        EndpointPair endpointPair = null;
        try {
            endpointPair = new EndpointPair(new Endpoint(InetAddress.getLocalHost().getHostAddress(), port), endpoint);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        if (channelMap.containsKey(endpointPair)) {
            Channel channel = channelMap.get(endpointPair);
            String string = channel.remoteAddress().toString();
            System.out.println("存在连接" + endpoint.toString() + "是来自" + string + "的连接");
        }
        Channel channel = channelMap.computeIfAbsent(endpointPair, x -> {
            Bootstrap bootstrap = new Bootstrap()
                    .group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new ByteToMessageDecoder() {
                                @Override
                                protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {
                                    int readableBytes = in.readableBytes();
                                    if (readableBytes == 0) {
                                        return;
                                    }
                                    try {
                                        byte[] bytes = new byte[readableBytes];
                                        in.readBytes(bytes);
                                        String decodedString = new String(bytes, StandardCharsets.UTF_8);
                                        list.add(decodedString);
                                    } catch (Exception ex) {
                                        throw new DecoderException("Failed to decode message", ex);
                                    }
                                }
                            });
                            pipeline.addLast(new MessageToByteEncoder<String>() {
                                @Override
                                protected void encode(ChannelHandlerContext channelHandlerContext, String msg, ByteBuf byteBuf) throws Exception {
                                    byteBuf.writeBytes(msg.getBytes(StandardCharsets.UTF_8));
                                }
                            });
                            pipeline.addLast(new ChannelProcessor());
                        }
                    });
            ChannelFuture future = null;
            try {
                future = bootstrap.connect(endpoint.getHost(), endpoint.getPort()).sync();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (!future.isSuccess()) {
                throw new ChannelException("failed to connect", future.cause());
            }
            Channel nettyChannel = future.channel();
            nettyChannel.closeFuture().addListener((ChannelFutureListener) cf -> {
                log.debug("channel OUTBOUND-{} disconnected", endpoint);
                channelMap.remove(endpoint);
            });
            return nettyChannel;
        });
        channel.writeAndFlush(msg);
    }

    @Override
    public void send(T msg, List<Endpoint> EndpointGroup) {

    }

    @Override
    public void close() {


    }

    private class ChannelProcessor extends ChannelDuplexHandler {

        /**
         * 当前服务节点，如果是客户端，则使用该节点作为端点
         * 不为空时，则适用
         */
        private Endpoint cNode;

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {

        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println(msg);
        }

        public ChannelProcessor(){}

        public ChannelProcessor(Endpoint cNode){
            this.cNode = cNode;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        NettyConnector node1 = new NettyConnector(8888);
        // 节点 1 8888启用监听
        node1.initial();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            node1.send(s, new Endpoint("localhost", 4000));
        }
    }
}
