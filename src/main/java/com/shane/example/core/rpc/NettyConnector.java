package com.shane.example.core.rpc;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Set;

/**
 * @author: shane
 * @date: 2023-08-29 10:18:04
 * @version: 1.0
 */
public class NettyConnector implements Connector {

    private static final Logger log = LoggerFactory.getLogger(NettyConnector.class);
    private final NioEventLoopGroup bossNioEventLoopGroup = new NioEventLoopGroup(1);
    private final NioEventLoopGroup workerNioEventLoopGroup;
    private final int port;

    private final ChannelGroup inbound;


    @Override
    public void start() {
        // 创建连接
    }

    @Override
    public void sendRequestVoteRpc(RequestVoteRPC rpc, Set<NodeEndpoint> dest) {

    }

    @Override
    public void replyRequestVoteRpc(RequestVoteResult rpcRequest, NodeEndpoint dest) {

    }

    @Override
    public void close() throws IOException {

    }
}
