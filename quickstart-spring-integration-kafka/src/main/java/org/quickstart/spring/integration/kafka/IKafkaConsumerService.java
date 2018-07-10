/**
 * 项目名称：quickstart-spring-integration-kafka 
 * 文件名：IKafkaConsumerService.java
 * 版本信息：
 * 日期：2017年11月10日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.spring.integration.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * IKafkaConsumerService
 * 
 * @author：yangzl@asiainfo.com
 * @2017年11月10日 下午2:40:57
 * @since 1.0
 */
public interface IKafkaConsumerService {

    public void onMessage(ConsumerRecord<Integer, String> record);

}
