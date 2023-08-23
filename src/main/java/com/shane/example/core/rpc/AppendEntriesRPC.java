package com.shane.example.core.rpc;

import java.util.List;

/**
 * @author: shane
 * @date: 2023-08-23 09:28:37
 * @version: 1.0
 */
public class AppendEntriesRPC {
    private int term;

    private String leaderId;

    private int preLogIndex;

    private int preLogTerm;

    private List<Object> logs;

    private int leaderCommit;


}
