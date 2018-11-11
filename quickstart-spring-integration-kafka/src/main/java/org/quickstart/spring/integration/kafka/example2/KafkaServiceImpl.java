/**
 * 项目名称：quickstart-spring-integration-kafka 
 * 文件名：KafkaServiceImpl.java
 * 版本信息：
 * 日期：2017年11月10日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.spring.integration.kafka.example2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * KafkaServiceImpl 类KafkaServiceImpl.java的实现描述：发消息实现类
 * 
 * @author：youngzil@163.com
 * @2017年11月10日 下午3:38:22
 * @since 1.0
 */
@Service("kafkaService")
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    @Qualifier("kafkaTopicTest")
    MessageChannel channel;

    public void sendUserInfo(String topic, Object obj) {
        channel.send(MessageBuilder.withPayload(obj).setHeader(KafkaHeaders.TOPIC, topic).build());
    }

}
