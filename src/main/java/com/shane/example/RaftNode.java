package com.shane.example;

import java.io.Closeable;

/**
 * @author: shane
 * @date: 2023-08-23 09:42:57
 * @version: 1.0
 */
public interface RaftNode extends Closeable {

    void start();


}
