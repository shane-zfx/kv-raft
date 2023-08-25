package com.shane.example.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: shane
 * @date: 2023-08-24 10:19:40
 * @version: 1.0
 */
public class Mian {
    static Logger LOGGER = LoggerFactory.getLogger(Mian.class);
    public static void main(String[] args) {
       // System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY,"logback.xml");
        LOGGER.debug("执行函数");
        System.out.println("输出");
    }
}
