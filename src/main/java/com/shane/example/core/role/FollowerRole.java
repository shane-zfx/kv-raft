package com.shane.example.core.role;

import com.shane.example.NodeEndpoint;
import com.shane.example.core.ElectionTimeout;

import java.util.Objects;

/**
 * @author: shane
 * @date: 2023-08-08 16:26:03
 * @version: 1.0
 */
public class FollowerRole extends AbstractRole {

    private final String votedFor;

    private final String currentLeader;

    private final ElectionTimeout electionTimeout;

    public FollowerRole(int role, int term,
                        String votedFor,
                        String currentLeader,
                        ElectionTimeout electionTimeout) {
        super(role, term);
        this.votedFor = votedFor;
        this.currentLeader = currentLeader;
        this.electionTimeout = electionTimeout;
    }

    @Override
    public void cancelPreCycleTask() {
        electionTimeout.cancel();
    }

}
