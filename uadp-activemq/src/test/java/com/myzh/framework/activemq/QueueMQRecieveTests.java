package com.myzh.framework.activemq;

public class QueueMQRecieveTests {

	public static void main(String[] args) {
		QueueMQ mq = new QueueMQ("tcp://123.56.232.165:61616", "test-query");
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
