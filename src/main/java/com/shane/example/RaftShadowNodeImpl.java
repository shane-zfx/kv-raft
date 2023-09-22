package com.shane.example;

import com.shane.example.core.ElectionTimeout;
import com.shane.example.core.role.AbstractRole;
import com.shane.example.core.role.CandidateRole;
import com.shane.example.core.role.FollowerRole;
import com.shane.example.core.role.RoleEnum;
import com.shane.example.core.rpc.Connector;
import com.shane.example.core.rpc.Endpoint;
import com.shane.example.core.rpc.RequestVoteRPC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Set;

/**
 * 在 raft 的影子节点中添加组件
 *
 * @author Shane
 * @version 1.0
 * @date 2023-08-23 23:12
 */
public class RaftShadowNodeImpl implements RaftShadowNode {

    private static final Logger LOGGER = LoggerFactory.getLogger(RaftShadowNodeImpl.class);

    private int term;

    private String votedFor;


    /**
     * raft 节点是否已启动
     */
    private boolean started;

    /**
     * 角色
     */
    private AbstractRole role;

    private Connector connector;

    private Set<Endpoint> cluster;

    public RaftShadowNodeImpl(int term,
                              String votedFor,
                              Connector connector,
                              Set<Endpoint> cluster){
        this.term = term;
        this.votedFor = votedFor;
        this.connector = connector;
        this.cluster = cluster;
    }


    @Override
    public synchronized void start() {
        if (started) {
            return;
        }
        // 节点当前状态初始化为 follower
        FollowerRole followerRole = new FollowerRole(0, "", "", new ElectionTimeout(this::doElectionTimeoutTask));
        this.role = followerRole;
        this.started = true;
    }

    private void doElectionTimeoutTask() {
        // 检查当前角色
        if (role.getRole() == RoleEnum.LEADER.getCode()) {
            //throw new IllegalStateException("illegal state exception");
            LOGGER.warn("illegal state exception");
            return;
        }
        int newTerm = this.term + 1;
        // 取消旧一轮
        role.cancelPreCycleTask();
        LOGGER.info("start election");
        // 变为 candidate
        this.role = new CandidateRole(newTerm, null);
        // 发送 rpc 消息
        RequestVoteRPC requestVoteRPC = new RequestVoteRPC(newTerm, null, 0, 0);
        connector.sendRequestVoteRpc(requestVoteRPC, cluster);
    }

    @Override
    public void stop() {

    }

    @Override
    public int getTerm() {
        return role.getCurrentTerm();
    }

    @Override
    public String getVotedFor() {
        return this.votedFor;
    }

    @Override
    public void close() {
    }
}
