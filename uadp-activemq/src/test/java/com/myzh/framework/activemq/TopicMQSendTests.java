package com.myzh.framework.activemq;

import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.junit.Test;

public class TopicMQSendTests {
	@Test
	public void testSender() {
		TopicMQ mq = new TopicMQ("tcp://123.56.232.165:61616", "test-topic");
		try {
			for(int i = 0; i < 10000; i++) {
				mq.send("topic 发送内容1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		mq.close();
	}
}
