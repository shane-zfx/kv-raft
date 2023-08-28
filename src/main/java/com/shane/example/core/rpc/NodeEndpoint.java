package com.shane.example.core.rpc;

/**
 * @author Shane
 * @version 1.0
 * @date 2023-08-28 21:09
 */
public class NodeEndpoint {
    private final String host;
    private final String port;

    public NodeEndpoint(String host, String port) {
        this.host = host;
        this.port = port;
    }
}
