package com.shane.example.core.rpc;

/**
 * @author: shane
 * @date: 2023-08-23 09:23:53
 * @version: 1.0
 */
public class RequestVoteRPC {

    private int term;
    private String candidateEndpoint;
    private int lastLogIndex;
    private int lastLogTerm;

    public RequestVoteRPC(int term, String candidateEndpoint, int lastLogIndex, int lastLogTerm) {
        this.term = term;
        this.candidateEndpoint = candidateEndpoint;
        this.lastLogIndex = lastLogIndex;
        this.lastLogTerm = lastLogTerm;
    }
}
