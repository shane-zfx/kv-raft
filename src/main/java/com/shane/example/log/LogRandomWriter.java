package com.shane.example.log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * @author: shane
 * @date: 2023-07-24 15:07:12
 * @version: 1.0
 */
public class LogRandomWriter extends AbstractLogWriter {

    @Override
    public void write(Object content) {
        // 没有指定文件路径，在工程所在目录下写入
    }

    @Override
    public void write(Object content, String path) {
        if (Objects.isNull(content)) {
            return;
        }
        try (FileOutputStream op = new FileOutputStream("test.txt")){

            byte[] bytes = new byte[]{1, 3, 4, 44, 56};

            op.write(bytes);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        try (RandomAccessFile file = new RandomAccessFile(path, "rw")) {
//            FileChannel channel = file.getChannel();
//            // channel.write()
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }


    public static void main(String[] args) {
        LogRandomWriter logRandomWriter = new LogRandomWriter();
        logRandomWriter.write("", "");
    }
}
