package com.shane.example.core.role;

import com.shane.example.core.ElectionTimeout;

/**
 * @author: shane
 * @date: 2023-08-09 17:43:22
 * @version: 1.0
 */
public class CandidateRole extends AbstractRole{

    private final int totalVotes;

    private final ElectionTimeout electionTimeout;

    public CandidateRole(int role,
                         int term,
                         int totalVotes,
                         ElectionTimeout electionTimeout) {
        super(role, term);
        this.totalVotes = totalVotes;
        this.electionTimeout = electionTimeout;
    }

    @Override
    public void cancelPreCycleTask() {
        electionTimeout.cancel();
    }
}
