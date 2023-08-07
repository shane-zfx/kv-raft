package com.shane.example.log;

import java.io.Closeable;

/**
 * @author Shane
 * @version 1.0
 * @date 2023-07-15 19:03
 */
public interface Log extends Closeable {
    int ALL_ENTRIES = -1;

    //
    LogEntryMeta getLastEntryMeta();

    int getNextEntryIndex();

    int getCommitIndex();

    boolean newer();

    LogEntry appendEntry(int term, byte[] command);
}
