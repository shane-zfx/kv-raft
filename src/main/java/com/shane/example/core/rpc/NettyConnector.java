package com.shane.example.core.rpc;

import com.google.common.collect.Multimap;
import com.shane.example.core.rpc.message.Message;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: shane
 * @date: 2023-08-29 10:18:04
 * @version: 1.0
 */
public class NettyConnector implements Connector {

    private static final Logger log = LoggerFactory.getLogger(NettyConnector.class);

    private Map<Endpoint, Channel> connects;

    @Override
    public void close() throws IOException {

    }

    @Override
    public void send(Message msg, Endpoint destination) {
        // 获取 dest 对应的 channel
        // 没有则创建，并放到 map 中去
        // 通过 channel 发送消息
    }

    @Override
    public void send(Message msg, List<Endpoint> destinationGroup) {

    }

    @Override
    public void send(Multimap<String, Endpoint> multiSendInfo) {

    }
}
