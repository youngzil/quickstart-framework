/**
 * 项目名称：quickstart-spring-integration-kafka 
 * 文件名：IKafkaProducerService.java
 * 版本信息：
 * 日期：2017年11月10日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.spring.integration.kafka;

/**
 * IKafkaProducerService
 * 
 * @author：youngzil@163.com
 * @2017年11月10日 下午2:36:13
 * @since 1.0
 */
public interface IKafkaProducerService {

    public void sendMessage(String topic, String data);

    public void sendMessage(String topic, int key, String data);

}
