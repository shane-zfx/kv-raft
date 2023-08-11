package com.shane.example.core.role;

import com.shane.example.NodeEndpoint;
import com.shane.example.core.ElectionTimeout;

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

}
