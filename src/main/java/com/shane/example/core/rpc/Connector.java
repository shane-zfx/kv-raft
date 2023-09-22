package com.shane.example.core.rpc;

import com.google.common.collect.Multimap;
import com.shane.example.core.rpc.message.Message;

import java.io.Closeable;
import java.util.List;
import java.util.Set;

/**
 * @author: shane
 * @date: 2023-08-28 18:17:14
 * @version: 1.0
 */
public interface Connector extends Closeable {

    void send(Message msg, Endpoint destination);
    void send(Message msg, List<Endpoint> destinationGroup);

    void send(Multimap<String, Endpoint> multiSendInfo);
}
