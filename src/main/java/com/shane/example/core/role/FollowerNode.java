package com.shane.example.core.role;

import com.shane.example.NodeEndpoint;
import com.shane.example.core.ElectionTimeout;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * @author: shane
 * @date: 2023-08-08 16:26:03
 * @version: 1.0
 */
public class FollowerNode extends AbstractNodeRole{


    private ElectionTimeout electionTimeout;

    private NodeEndpoint votedFor;

    public FollowerNode(Role roleName, int term) {
        super(roleName, term);
    }

    public NodeEndpoint getVotedFor(){
        return this.votedFor;
    }

}
