package com.shane.example.log;

/**
 * @author Shane
 * @version 1.0
 * @date 2023-07-15 18:42
 */
public interface LogEntry {
    int getType();

    int getIndex();

    int getTerm();

    LogEntryMeta getMeta();

    byte[] getCommand();
}
