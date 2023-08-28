package com.shane.example.core.rpc;

import java.io.Closeable;
import java.util.Set;

/**
 * @author: shane
 * @date: 2023-08-28 18:17:14
 * @version: 1.0
 */
public interface Connector extends Closeable {

    void start();

    void sendRequestVoteRpc(RequestVoteRPC rpc, Set<NodeEndpoint> dest);

    void replyRequestVoteRpc(RequestVoteResult rpcRequest, NodeEndpoint dest);
}
