/**
 * 项目名称：ddmp-test 
 * 文件名：MqttClientExecutorThread.java
 * 版本信息：
 * 日期：2017年10月17日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.thread.pool;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MqttClientExecutorThread
 * 
 * @author：yangzl@asiainfo.com
 * @2017年10月17日 下午9:03:33
 * @since 1.0
 */
public class ClientExecutorThread implements Callable<String> {

    private static final Logger logger = LoggerFactory.getLogger(ClientExecutorThread.class);

    private int number;

    ClientExecutorThread(int number) {
        this.number = number;
    }

    @Override
    public String call() throws Exception {

        Thread.sleep(this.number * 1000);

        return Thread.currentThread().getId() + "";

    }

}
