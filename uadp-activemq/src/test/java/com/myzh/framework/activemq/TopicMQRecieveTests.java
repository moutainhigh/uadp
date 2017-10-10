package com.myzh.framework.activemq;

public class TopicMQRecieveTests {
	
	public static void main(String[] args) {
		
		TopicMQ mq = new TopicMQ("tcp://123.56.232.165:61616", "test-topic");
		try {
			mq.recieve(new MessageListener() {
				
				@Override
				public void onMessage(Object msg) {
					System.out.println(msg);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("end");
	}
}
