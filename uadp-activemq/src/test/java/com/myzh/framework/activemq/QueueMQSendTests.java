package com.myzh.framework.activemq;

public class QueueMQSendTests {
	
	public static void main(String[] args) {
		System.out.println("ddd");
		QueueMQ mq = new QueueMQ("tcp://123.56.232.165:61616", "test-query");
		try {
			for(int i = 0; i < 2323; i++) {
				
				mq.send("111develper" + i);
				System.out.println(i);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mq.close();
	}
}
