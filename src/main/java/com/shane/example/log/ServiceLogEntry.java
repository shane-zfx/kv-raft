package com.shane.example.log;

/**
 * @author Shane
 * @version 1.0
 * @date 2023-07-15 18:56
 */
public class ServiceLogEntry extends AbstractLogEntry {

    // 服务日志
    public static int SERVICE = 1;

    private final byte[] command;

    public ServiceLogEntry(int index, int term, byte[] command) {
        super(SERVICE, index, term);
        this.command = command;
    }

    @Override
    public byte[] getCommand() {
        return this.command;
    }
}
