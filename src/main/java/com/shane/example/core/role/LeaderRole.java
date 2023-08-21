package com.shane.example.core.role;

/**
 * @author: shane
 * @date: 2023-08-09 17:44:10
 * @version: 1.0
 */
public class LeaderRole extends AbstractRole {

    public LeaderRole(int term) {
        super(0, term);
    }

    @Override
    public void cancelPreCycleTask() {

    }
}
