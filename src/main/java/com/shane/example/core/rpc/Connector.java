package com.shane.example.core.rpc;

import com.google.common.collect.Multimap;
import com.shane.example.core.rpc.message.Message;

import java.io.Closeable;
import java.util.List;
import java.util.Objects;

/**
 * @author: shane
 * @date: 2023-08-28 18:17:14
 * @version: 1.0
 */
public interface Connector extends Closeable {

    /**
     * 创建 rpc 监听端点
     */
    void initial();

    void send(Object msg, Endpoint endpoint);

    void close();
}
