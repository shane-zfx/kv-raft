package com.shane.example.log;

/**
 * @author Shane
 * @version 1.0
 * @date 2023-07-15 18:46
 */
public abstract class AbstractLogEntry implements LogEntry {


    // 通用日志
    public static int GENERAL = 0;
    protected final int index;
    protected final int term;
    private final int type;

    AbstractLogEntry(int type, int index, int term) {
        this.type = type;
        this.index = index;
        this.term = term;
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public int getTerm() {
        return this.term;
    }

    @Override
    public LogEntryMeta getMeta() {
        return new LogEntryMeta(this.type, this.index, this.term);
    }
}
