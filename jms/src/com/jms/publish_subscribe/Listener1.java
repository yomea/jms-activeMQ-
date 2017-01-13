package com.jms.publish_subscribe;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
/**
 * 创建监听器
 * @author may
 *
 */
public class Listener1 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		System.out.println("dfhfdgn");
		TextMessage textMessage = (TextMessage) message;
		
		try {
			String text = textMessage.getText();
			
			System.out.println(text);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
