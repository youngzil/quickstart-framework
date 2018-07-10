/**
 * 项目名称：quickstart-spring-integration-kafka 
 * 文件名：KafkaProducerServiceImpl.java
 * 版本信息：
 * 日期：2017年11月10日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.spring.integration.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * KafkaProducerServiceImpl
 * 
 * @author：yangzl@asiainfo.com
 * @2017年11月10日 下午2:34:58
 * @since 1.0
 */
public class KafkaProducerServiceImpl implements IKafkaProducerService {
    private Logger logger = LoggerFactory.getLogger("kafka");

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;// 这个已经在上述xml文件中配置

    @Override
    public void sendMessage(String topic, String data) {
        logger.info("the message is to be send by kafka is : topic = {}, data = {}", topic, data);
        kafkaTemplate.setDefaultTopic(topic);
        kafkaTemplate.sendDefault(data);
    }

    @Override
    public void sendMessage(String topic, int key, String data) {
        logger.info("the message is to be send by kafka is : topic = {}, data = {}", topic, data);
        kafkaTemplate.setDefaultTopic(topic);
        kafkaTemplate.sendDefault(key, data);
    }
}
