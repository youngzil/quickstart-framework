package com.quickstart.test.jms.springjms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;

public class ConsumerServiceImpl implements ConsumerService {

    private JmsTemplate jmsTemplate;

    /**
     * 接受消息
     * 
     * @throws JmsException
     */
    public void receive(Destination destination) throws Exception, JmsException {
        TextMessage tm = (TextMessage) jmsTemplate.receive(destination);
        try {
            System.out.println("从队列" + destination.toString() + "收到了消息：\t" + tm.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

}
