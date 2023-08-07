package com.shane.example;

/**
 * @author Shane
 * @version 1.0
 * @date 2023-07-15 18:30
 */
public class Node {
    // 服务器程序启动时，所有节点都是从节点
    // 只要收到主节点或者候选节点的有效rpc，当前节点就会一直保持从节点状态
    // 如果没有，晋升候选，发起选举

    /**
     * 任期
     */
    private volatile int term;
    /**
     * 节点类型
     * 0 - leader
     * 1 - candidate
     * 2 - follower
     */
    private int role;

    public Node() {
        // 节点初始化
        // 1. 任期初始化为 0
        // 2. 节点类型初始化为 follower
        // 3. 随机任期时间
    }

    private void roleChange() {
        // 等待随机 150ms ~ 300ms
    }

    /**
     * [i, j]
     * @author "shane"
     * @date 2023/7/19 11:31
     */
    public int test(int i, int j){
        return i;
    }
}
