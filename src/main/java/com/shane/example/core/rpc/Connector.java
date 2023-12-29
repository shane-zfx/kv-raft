package com.shane.example.core.rpc;

import java.io.Closeable;
import java.util.List;

/**
 * @author: shane
 * @date: 2023-08-28 18:17:14
 * @version: 1.0
 */
public interface Connector<T> extends Closeable {

    /**
     * 启动服务端监听
     */
    void initial();

    /**
     * 单播
     *
     * @param msg      消息内容
     * @param endpoint 端点
     */
    void send(T msg, Endpoint endpoint);

    /**
     * 广播
     *
     * @param msg           消息内容
     * @param EndpointGroup 组
     */
    void send(T msg, List<Endpoint> EndpointGroup);

    void close();
}
