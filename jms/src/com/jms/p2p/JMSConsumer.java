package com.jms.p2p;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSConsumer {

	private static final String USERNAME = ActiveMQConnectionFactory.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnectionFactory.DEFAULT_PASSWORD;
	private static final String BROKER_URL = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
	
	public static void main(String[] args) {
		
		ConnectionFactory connectionFactory = null;// 连接工厂
		Connection connection = null;// 连接
		Session session;// 会话
		Destination destination;// 目标
		MessageConsumer messageConsumer;//设置消息消费者
		
		try {
			connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
			connection = connectionFactory.createConnection();
			connection.start();
			// 第一个参数表示是否加事物操作，第二个参数Session.AUTO_ACKNOWLEDGE表示自动确认接收，
			//消费消息不需要加事物
			session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("myFirstQueue");// 创建一个叫做myFirstQueue的目标队列
			messageConsumer = session.createConsumer(destination);
			while(true) {
				//receive（long argue）在取完队列中的所有消息后，会按每1s钟的时间进行读取
				TextMessage textMessage = (TextMessage) messageConsumer.receive(1000);
				if(textMessage != null) {
					
					System.out.println(textMessage.getText());
					
				}
				System.out.println(textMessage);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}

		}
		
	}
	
}
