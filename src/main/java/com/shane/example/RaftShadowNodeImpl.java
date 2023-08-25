package com.shane.example;

import com.shane.example.core.role.AbstractRole;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;

/**
 * 在 raft 的影子节点中添加组件
 *
 * @author Shane
 * @version 1.0
 * @date 2023-08-23 23:12
 */
public class RaftShadowNodeImpl implements RaftShadowNode {


    /**
     * raft 节点是否已启动
     */
    private boolean started;

    /**
     * 角色
     */
    private AbstractRole role;

    private RandomAccessFile randomAccessFile;


    @Override
    public synchronized void start() {
        if (started) {
            return;
        }

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
    public int loadTerm(File file) throws IOException {
        // 从文件中加载
        if(!file.exists() && !file.createNewFile()){
            throw new IllegalStateException("fail to touch file "+ file);
        }
        randomAccessFile = new RandomAccessFile(file, "rw");
        if(randomAccessFile.length() == 0){
            //
            randomAccessFile.setLength(8L);
            randomAccessFile.seek(0L);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(0);
        }else {

        }
        return 0;
    }

    @Override
    public String getVotedFor() {
        return null;
    }

    @Override
    public String loadVotedFor(File file) {
        return null;
    }

    @Override
    public void close() {

    }

    private void loadFromFile() {

    }
}
