package com.shane.example;

import com.sun.jndi.cosnaming.IiopUrl;
import com.sun.xml.internal.ws.wsdl.writer.document.http.Address;

import java.util.Objects;

/**
 * @author: shane
 * @date: 2023-08-08 15:51:28
 * @version: 1.0
 */
public class NodeEndpoint {

    private final String host;
    private final int port;

    public NodeEndpoint(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeEndpoint that = (NodeEndpoint) o;
        return port == that.port && Objects.equals(host, that.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port);
    }
}
