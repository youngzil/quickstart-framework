package com.quickstart.test.jms.activeMQ;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 演示如何从MQ接受消息，和发送差不多
 * 
 * 1.初始化连接工厂ConnectionFactory
 * 
 * 2.创建连接Connection
 * 
 * 3. 创建会话session
 * 
 * 4.打开队列createQueue
 * 
 * 5.获得消息消费者MessageConsumer
 * 
 * 6.使用MessageConsumer接受消息
 * 
 * 7. 关闭会话session和连接Connection
 * 
 */
public class Subscriber {

    public static void main(String[] args) {
        String user = ActiveMQConnection.DEFAULT_USER;
        String password = ActiveMQConnection.DEFAULT_PASSWORD;
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        String subject = "MQ.TOPIC";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
        Connection connection;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(subject);
            // MessageConsumer负责接受消息
            MessageConsumer consumer = session.createConsumer(topic);
            consumer.setMessageListener(new MessageListener() {

                public void onMessage(Message msg) {
                    TextMessage message = (TextMessage) msg;
                    try {
                        String hello = message.getStringProperty("hello");
                        System.out.println("订阅者---SecondSubscriber---收到消息：\t" + hello);
                        session.commit();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            // MessageConsumer负责接受消息
            MessageConsumer consumer2 = session.createConsumer(topic);
            consumer2.setMessageListener(new MessageListener() {

                public void onMessage(Message msg) {
                    TextMessage message = (TextMessage) msg;
                    try {
                        String hello = message.getStringProperty("hello");
                        System.out.println("订阅者---SecondSubscriber2---收到消息：\t" + hello);
                        session.commit();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            // 为了测试效果，注释掉了两行代码，使Session和connection一直处于打开状态
            // session.close();
            // connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
