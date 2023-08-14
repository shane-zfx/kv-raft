package com.shane.example;

import com.shane.example.core.role.AbstractRole;
import com.shane.example.core.role.FollowerRole;

/**
 * @author Shane
 * @version 1.0
 * @date 2023-07-15 18:30
 */
public class Node {
    private int role;
    public void start(){
        // 初始化为 follower
        this.role = 0;
        this.roleChange(new FollowerRole(0));
    }

    public void roleChange(AbstractRole role){
        // 判断当前角色是否支持准换

    }
}
