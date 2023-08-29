package com.shane.example.core.rpc;

import io.netty.bootstrap.Bootstrap;

import java.io.IOException;
import java.util.Set;

/**
 * @author: shane
 * @date: 2023-08-29 10:18:04
 * @version: 1.0
 */
public class NettyConnector implements Connector{

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
