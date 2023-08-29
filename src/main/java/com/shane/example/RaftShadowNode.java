package com.shane.example;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

/**
 * @author Shane
 * @version 1.0
 * @date 2023-08-23 23:09
 */
public interface RaftShadowNode extends Closeable {

    void start();

    void stop();
    int getTerm();


    String getVotedFor();


    void close();
}
