package com.quickstart.test.jms.springjms;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.StreamMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class MessageTypeSender {

    private JmsTemplate jmsTemplate;

    /**
     * 向默认队列发送text消息
     */
    public void sendMessage(final String msg) throws Exception {
        String destination = jmsTemplate.getDefaultDestination().toString();
        System.out.println("ProducerService向队列" + destination + "发送了消息：\t" + msg);
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }

    /**
     * 向默认队列发送map消息
     */
    public void sendMapMessage() {
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                message.setString("name", "小西山");
                return message;
            }
        });
    }

    /**
     * 向默认队列发送Object消息
     */
    public void sendObjectMessage() {
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                Staff staff = new Staff(1, "搬砖工"); // Staff必须实现序列化
                ObjectMessage message = session.createObjectMessage(staff);
                return message;
            }
        });
    }

    /**
     * 向默认队列发送Bytes消息
     */
    public void sendBytesMessage() {
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                String str = "BytesMessage 字节消息";
                BytesMessage message = session.createBytesMessage();
                message.writeBytes(str.getBytes());
                return message;
            }
        });
    }

    /**
     * 向默认队列发送Stream消息
     */
    public void sendStreamMessage() {
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                String str = "StreamMessage 流消息";
                StreamMessage message = session.createStreamMessage();
                message.writeString(str);
                message.writeInt(521);
                return message;
            }
        });
    }

}
