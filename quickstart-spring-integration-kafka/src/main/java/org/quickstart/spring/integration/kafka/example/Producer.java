/**
 * 项目名称：quickstart-spring-integration-kafka 
 * 文件名：Producer.java
 * 版本信息：
 * 日期：2017年11月10日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.spring.integration.kafka.example;

import java.util.Random;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

/**
 * Producer
 * 
 * @author：youngzil@163.com
 * @2017年11月10日 下午3:01:49
 * @since 1.0
 */
public class Producer {

    /**
     * Outbound Channel Adapter用来发送消息到Kafka。 消息从Spring Integration Channel中读取。 你可以在Spring application context指定这个channel。 一旦配置好这个Channel，就可以利用这个Channel往Kafka发消息。 明显地，Spring
     * Integration特定的消息发送给这个Adaptor，然后发送前在内部被转为Kafka消息。当前的版本要求你必须指定消息key和topic作为头部数据 (header)，消息作为有载荷(payload)。 例如
     */

    private static final String CONFIG = "/context.xml";
    private static Random rand = new Random();

    public static void main(String[] args) {
        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(CONFIG, Producer.class);
        ctx.start();
        final MessageChannel channel = ctx.getBean("inputToKafka", MessageChannel.class);
        for (int i = 0; i < 100; i++) {
            channel.send(MessageBuilder.withPayload("Message-" + rand.nextInt()).setHeader("messageKey", String.valueOf(i)).setHeader("topic", "test").build());
        }
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ctx.close();
    }

}
