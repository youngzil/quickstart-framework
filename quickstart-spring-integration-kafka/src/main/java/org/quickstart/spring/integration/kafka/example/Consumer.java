/**
 * 项目名称：quickstart-spring-integration-kafka 
 * 文件名：Consumer.java
 * 版本信息：
 * 日期：2017年11月10日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.spring.integration.kafka.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;

import ch.qos.logback.classic.Level;

/**
 * Consumer
 * 
 * @author：yangzl@asiainfo.com
 * @2017年11月10日 下午3:04:29
 * @since 1.0
 */
public class Consumer {
    private static final String CONFIG = "/consumer_context.xml";
    private static Random rand = new Random();

    @SuppressWarnings({"unchecked", "unchecked", "rawtypes"})
    public static void main(String[] args) {
        ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.toLevel("info"));

        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(CONFIG, Consumer.class);
        ctx.start();
        final QueueChannel channel = ctx.getBean("inputFromKafka", QueueChannel.class);
        Message msg;
        while ((msg = channel.receive()) != null) {
            HashMap map = (HashMap) msg.getPayload();
            Set<Map.Entry> set = map.entrySet();
            for (Map.Entry entry : set) {
                String topic = (String) entry.getKey();
                System.out.println("Topic:" + topic);
                ConcurrentHashMap<Integer, List<byte[]>> messages = (ConcurrentHashMap<Integer, List<byte[]>>) entry.getValue();
                Collection<List<byte[]>> values = messages.values();
                for (Iterator<List<byte[]>> iterator = values.iterator(); iterator.hasNext();) {
                    List<byte[]> list = iterator.next();
                    for (byte[] object : list) {
                        String message = new String(object);
                        System.out.println("\tMessage: " + message);
                    }

                }

            }

        }

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ctx.close();
    }
}
