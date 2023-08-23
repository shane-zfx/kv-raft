package com.shane.example;

import java.io.Closeable;

/**
 * @author Shane
 * @version 1.0
 * @date 2023-08-23 23:09
 */
public interface RaftShadowNode extends Closeable {

    void start();

    void close();
}
