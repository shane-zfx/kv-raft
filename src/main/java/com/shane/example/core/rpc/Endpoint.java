package com.shane.example.core.rpc;


import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.InterfaceAddress;
import java.util.Objects;

/**
 * @author Shane
 * @version 1.0
 * @date 2023-08-28 21:09
 */
public final class Endpoint {
    private final String host;
    private final int port;

    public Endpoint(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Endpoint(InetSocketAddress socketAddress) {
        this.host = socketAddress.getHostString();
        this.port = socketAddress.getPort();
    }

    public static Endpoint trans(InetSocketAddress socketAddress){
        return new Endpoint(socketAddress.getHostString(), socketAddress.getPort());
    }
    public InetSocketAddress convert(){
        return new InetSocketAddress(host, port);
    }

    public String getHost(){
        return this.host;
    }

    public int getPort(){
        return this.port;
    }

    @Override
    public String toString() {
        return host + ":" + port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endpoint)) return false;
        Endpoint endpoint = (Endpoint) o;
        return Objects.equals(host, endpoint.getHost()) && Objects.equals(port, endpoint.getPort());
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port);
    }
}
