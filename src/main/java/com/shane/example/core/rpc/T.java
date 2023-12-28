package com.shane.example.core.rpc;

import java.util.Scanner;

/**
 * @author: shane
 * @date: 2023-12-27 16:08:14
 * @version: 1.0
 */
public class T {
    public static void main(String[] args) {
        NettyConnector node2 = new NettyConnector(4000);
        node2.initial();
        // node2.send("你好.这里是4000号机器", new Endpoint("localhost", 8888));

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            node2.send(s, new Endpoint("localhost", 8888));
        }
    }
}
