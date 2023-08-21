package com.shane.example.core.role;

import com.shane.example.core.ElectionTimeout;

/**
 * @author: shane
 * @date: 2023-08-09 17:43:22
 * @version: 1.0
 */
public class CandidateRole extends AbstractRole {

    private final int totalVotes;

    private final ElectionTimeout electionTimeout;

    /**
     * The node status is converted to candidate for the first time.
     *
     * @param term            current term
     * @param electionTimeout new election timer
     */
    public CandidateRole(int term, ElectionTimeout electionTimeout) {
        super(1, term);
        this.totalVotes = 1;
        this.electionTimeout = electionTimeout;
    }

    public CandidateRole(int term, int totalVotes, ElectionTimeout electionTimeout) {
        super(1, term);
        this.totalVotes = totalVotes;
        this.electionTimeout = electionTimeout;
    }

    @Override
    public void cancelPreCycleTask() {
        electionTimeout.cancel();
    }
}
