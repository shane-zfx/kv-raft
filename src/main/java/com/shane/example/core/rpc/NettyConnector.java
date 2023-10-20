package com.shane.example.core.rpc;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.shane.example.core.rpc.message.Decoder;
import com.shane.example.core.rpc.message.Encoder;
import com.shane.example.core.rpc.message.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: shane
 * @date: 2023-08-29 10:18:04
 * @version: 1.0
 */
public class NettyConnector implements Connector {

    private static final Logger log = LoggerFactory.getLogger(NettyConnector.class);

    private Map<Endpoint, Channel> remoteConnectors = new ConcurrentHashMap<>();

    private final Set<Endpoint> destinationGroup;
    private final int port;

    public NettyConnector(int port, Set<Endpoint> destinationGroup){
        this.port = port;
        this.destinationGroup = destinationGroup;
    }

    @Override
    public void close() throws IOException {


    }

    @Override
    public void initial() {
        ServerBootstrap serverBootstrap = new ServerBootstrap()
                .group(new NioEventLoopGroup(1), new NioEventLoopGroup(5))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
//                        pipeline.addLast(new Decoder());
//                        pipeline.addLast(new Encoder());
//                        pipeline.addLast(new FromRemoteHandler());
                    }
                });
        try {
            serverBootstrap.bind(port).sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send(Message msg, Endpoint destination) throws InterruptedException {
        // 获取 dest 对应的 channel
        // 通过 channel 发送消息
        Channel channel = remoteConnectors.get(destination);
        if (channel != null) {

        }
        // 没有则创建，并放到 map 中去
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup());
        bootstrap.option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        // todo
                        pipeline.addLast();
                    }
                });
        ChannelFuture channelFuture = bootstrap.connect(destination.getHost(), destination.getPort()).sync();

    }

    @Override
    public void send(Message msg, List<Endpoint> destinationGroup) {

    }

    @Override
    public void send(Multimap<String, Endpoint> multiSendInfo) {

    }
}
