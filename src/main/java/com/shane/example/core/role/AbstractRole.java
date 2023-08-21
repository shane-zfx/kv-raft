package com.shane.example.core.role;

import com.shane.example.NodeEndpoint;

/**
 * @author: shane
 * @date: 2023-08-08 15:44:56
 * @version: 1.0
 */
public abstract class AbstractRole {

    private final int role;
    private final int currentTerm;

    public AbstractRole(int role, int term) {
        this.role = role;
        this.currentTerm = term;
    }

    public int getRole() {
        return this.role;
    }

    public int getCurrentTerm() {
        return this.currentTerm;
    }

    /**
     * 每次角色变化，都需要重置超时任务
     */
    public abstract void cancelPreCycleTask();
}
