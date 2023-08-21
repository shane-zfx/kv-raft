package com.shane.example;

import com.shane.example.core.ElectionTimeout;

/**
 * @author Shane
 * @version 1.0
 * @date 2023-07-15 18:30
 */
public class Node {
    private int role;

    private boolean started;

    private ElectionTimeout electionTimer;

    public void start(){
        if (started) {
            return;
        }
        // 初始化为 follower
        this.role = 0;
//        electionTimer.submit();
        this.started = true;
    }

    private void followerTimeout() {
        // 变换角色
        // term + 1
        // vote itself
        //
    }
}
