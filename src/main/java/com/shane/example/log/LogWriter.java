package com.shane.example.log;

/**
 * 日志写入器，目标是将kv的命令按条目写入磁盘中
 *
 * @author: shane
 * @date: 2023-07-24 15:00:39
 * @version: 1.0
 */
public interface LogWriter {

    void write(Object content);

    void write(Object Content, String path);

}
