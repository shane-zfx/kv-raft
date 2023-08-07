package com.shane.example.log;

/**
 * @author Shane
 * @version 1.0
 * @date 2023-07-15 18:43
 */
public class LogEntryMeta {
    private final int type;
    private final int index;
    private final int term;

    public LogEntryMeta(int type, int index, int term) {
        this.type = type;
        this.index = index;
        this.term = term;
    }
}
