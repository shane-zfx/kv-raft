package com.shane.example.core.role;

import com.shane.example.core.ElectionTimeout;

/**
 * @author: shane
 * @date: 2023-08-08 16:26:03
 * @version: 1.0
 */
public class FollowerRole extends AbstractRole {

    private final String votedFor;

    private final String currentLeader;

    private final ElectionTimeout electionTimeout;

    public FollowerRole(String votedFor, String currentLeader, ElectionTimeout electionTimeout) {
        this(1, votedFor, currentLeader, electionTimeout);
    }

    public FollowerRole(int term, String votedFor, String currentLeader, ElectionTimeout electionTimeout) {
        super(2, term);
        this.votedFor = votedFor;
        this.currentLeader = currentLeader;
        this.electionTimeout = electionTimeout;
    }

    @Override
    public void cancelPreCycleTask() {
        electionTimeout.cancel();
    }

}
