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


    private ElectionTimeout electionTimeout;

    private NodeEndpoint votedFor;

    public FollowerRole(/*Role roleName,*/ int term) {
        super(/*roleName,*/ term);
    }

    public NodeEndpoint getVotedFor(){
        return this.votedFor;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FollowerRole that = (FollowerRole) o;

        if (!electionTimeout.equals(that.electionTimeout)) return false;
        return Objects.equals(votedFor, that.votedFor);
    }

    @Override
    public int hashCode() {
        int result = electionTimeout.hashCode();
        result = 31 * result + (votedFor != null ? votedFor.hashCode() : 0);
        return result;
    }
}
