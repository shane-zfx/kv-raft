package com.shane.example.core.role;

/**
 * @author: shane
 * @date: 2023-08-28 15:32:04
 * @version: 1.0
 */
public enum RoleEnum {
    FOLLOWER(0),
    CANDIDATE(1),
    LEADER(2);

    private int code;
    RoleEnum(int code){
        this.code = code;
    }

    public int getCode(){
        return this.code;
    }
}
