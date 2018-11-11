/**
 * 项目名称：quickstart-spring-integration-kafka 
 * 文件名：KafkaService.java
 * 版本信息：
 * 日期：2017年11月10日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.spring.integration.kafka.example2;

/**
 * KafkaService 类KafkaService.java的实现描述：发消息接口类
 * 
 * @author：youngzil@163.com
 * @2017年11月10日 下午3:36:54
 * @since 1.0
 */
public interface KafkaService {

    /**
     * 发消息
     * 
     * @param topic 主题
     * @param obj 发送内容
     */
    public void sendUserInfo(String topic, Object obj);

}
