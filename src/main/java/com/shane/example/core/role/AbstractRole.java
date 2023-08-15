package com.shane.example.core.role;

import com.shane.example.NodeEndpoint;
import com.shane.example.log.LogEntry;

/**
 * @author: shane
 * @date: 2023-08-08 15:44:56
 * @version: 1.0
 */
public abstract class AbstractRole {

    //private Role roleName;
    private int currentTerm;

    private String votedFor;

    private LogEntry[] logEntries;

    private int commitIndex;

    private int lastApplied;




    public AbstractRole(/*Role roleName,*/ int term){
        //this.roleName = roleName;
        this.currentTerm = term;
    }

    // 取消
    public void voteFor(NodeEndpoint endpoint) {

    }

    public synchronized int termIncr(){
        return this.currentTerm++;
    }
}
