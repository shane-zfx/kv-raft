package com.shane.example.core.rpc;

import java.io.Closeable;

/**
 * @author: shane
 * @date: 2023-08-28 18:17:14
 * @version: 1.0
 */
public interface Connector extends Closeable {

    void start();

    RequestVoteResult sendRequestVoteRpc();


}
