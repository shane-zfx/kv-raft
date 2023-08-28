package com.shane.example;

import com.shane.example.core.ElectionTimeout;
import com.shane.example.core.role.AbstractRole;
import com.shane.example.core.role.FollowerRole;
import com.shane.example.core.role.RoleEnum;
import com.shane.example.core.rpc.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 在 raft 的影子节点中添加组件
 *
 * @author Shane
 * @version 1.0
 * @date 2023-08-23 23:12
 */
public class RaftShadowNodeImpl implements RaftShadowNode {

    private static final Logger LOGGER = LoggerFactory.getLogger(RaftShadowNodeImpl.class);

    int term;

    String votedFor;


    /**
     * raft 节点是否已启动
     */
    private boolean started;

    /**
     * 角色
     */
    private AbstractRole role;

    private Connector connector;

    private RandomAccessFile randomAccessFile;


    @Override
    public synchronized void start() {
        if (started) {
            return;
        }
        this.role = new FollowerRole(0, "", "", new ElectionTimeout(this::doElectionTimeoutTask));
    }

    private void doElectionTimeoutTask() {
        // 检查当前角色
        if(role.getRole() == RoleEnum.LEADER.getCode()){
            //throw new IllegalStateException("illegal state exception");
            LOGGER.warn("illegal state exception");
            return;
        }

        int newTerm = this.term + 1;
        // 取消旧一轮
        role.cancelPreCycleTask();
        LOGGER.info("start election");
        // 变为 candidate
        // 发送 rpc 消息

    }

    @Override
    public void stop() {

    }

    @Override
    public int getTerm() {
        // 从内存中获取当前 term
        return role.getCurrentTerm();
    }

    @Override
    public void loadTerm(File file) throws IOException {
        // 从文件中加载
        if (!file.exists() && !file.createNewFile()) {
            throw new IllegalStateException("fail to touch file " + file);
        }
        randomAccessFile = new RandomAccessFile(file, "rw");
        if (randomAccessFile.length() == 0) {
            //
            randomAccessFile.setLength(8L);
            randomAccessFile.seek(0L);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(0);
        } else {
            this.term = randomAccessFile.readInt();
            int len = randomAccessFile.readInt();
            if (len > 0) {
                byte[] bytes = new byte[len];
                randomAccessFile.read(bytes);
                this.votedFor = new String(bytes);
            }
        }
    }

    @Override
    public String getVotedFor() {
        return this.votedFor;
    }

    @Override
    public String loadVotedFor(File file) {
        return null;
    }

    @Override
    public void close() {
        try {
            randomAccessFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
