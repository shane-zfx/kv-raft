package com.shane.example.log;

/**
 * @author Shane
 * @version 1.0
 * @date 2023-07-15 19:00
 */
public class SystemLogEntry extends AbstractLogEntry {
    // 系统日志
    public static int SYSTEM = -1;

    SystemLogEntry(int index, int term) {
        super(SYSTEM, index, term);
    }

    @Override
    public byte[] getCommand() {
        return new byte[0];
    }
}
