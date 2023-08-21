package com.shane.example.core.role;

import com.shane.example.core.LogReplicationScheduler;

/**
 * @author: shane
 * @date: 2023-08-09 17:44:10
 * @version: 1.0
 */
public class LeaderRole extends AbstractRole {

    private final LogReplicationScheduler logReplicationScheduler;

    public LeaderRole(int term, LogReplicationScheduler logReplicationScheduler) {
        super(0, term);
        this.logReplicationScheduler = logReplicationScheduler;
    }

    @Override
    public void cancelPreCycleTask() {
        logReplicationScheduler.ca
    }
}
