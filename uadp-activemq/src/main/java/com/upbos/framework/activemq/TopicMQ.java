package com.upbos.framework.activemq;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.jms.DeliveryMode;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicMQ {
	private String brokerUrl = ActiveMQConnection.DEFAULT_BROKER_URL;
	private String destination = "test-message-log";

	private TopicConnection conn = null;

	public TopicMQ(String brokerUrl, String destination) {
		this.brokerUrl = brokerUrl;
		this.destination = destination;
	}

	private TopicConnection getConnection() throws JMSException {
		if (conn != null)
			return conn;
		TopicConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
		// 创建mq连接
		conn = connectionFactory.createTopicConnection();
		conn.setExceptionListener(new ExceptionListener() {

			@Override
			public void onException(JMSException exception) {
				exception.printStackTrace();

			}
		});
		// 启动连接
		conn.start();
		return conn;
	}

	public void send(Object message) throws Exception {
		TopicSession session = null;
		try {
			// 创建Session，此方法第一个参数表示会话是否在事务中执行，第二个参数设定会话的应答模式
			session = getConnection().createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(destination);
			// 创建消息发送者
			TopicPublisher publisher = session.createPublisher(topic);
			// 设置持久化模式
			publisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			if (message instanceof String) {
				TextMessage msg = session.createTextMessage((String) message);
				publisher.send(msg);
			} else if (message instanceof Map) {
				MapMessage msg = MapToMapMessage((Map) message, session);
				publisher.send(msg);
			} else {
				throw new Exception("不支持的数据类型！");
			}

		} catch (Exception e) {
			throw e;
		} finally {
			// 关闭释放资源
			if (session != null) {
				session.close();
			}
		}
	}

	public void recieve(final MessageListener listener) throws Exception {
		TopicSession session = null;
		try {
			// 创建一个session会话
			session = getConnection().createTopicSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);// 创建一个消息队列
			// 创建一个消息队列
			Topic topic = session.createTopic(destination);
			// 创建消息制作者
			TopicSubscriber subscriber = session.createSubscriber(topic);

			subscriber.setMessageListener(new javax.jms.MessageListener() {

				@Override
				public void onMessage(Message message) {
					try {
						if (message instanceof MapMessage) {
							listener.onMessage(MapMessageToMap((MapMessage) message));
						} else if (message instanceof TextMessage) {
							listener.onMessage(((TextMessage) message).getText());
						}
					} catch (JMSException e) {
						e.printStackTrace();
					}

				}
			});

		} catch (Exception e) {
			throw e;
		} finally {
			// 关闭释放资源
			// if (session != null) {
			// session.close();
			// }
		}
	}

	public void close() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	private MapMessage MapToMapMessage(Map<String, Object> map, Session session) throws JMSException {
		MapMessage msg = session.createMapMessage();

		Set<String> set = map.keySet();
		for (String key : set) {
			Object value = map.get(key);
			if (value instanceof String) {
				msg.setString(key, value.toString());
			} else if (value instanceof Integer) {
				msg.setInt(key, (int) value);
			} else if (value instanceof Long) {
				msg.setLong(key, (Long) value);
			} else if (value instanceof Boolean) {
				msg.setBoolean(key, (Boolean) value);
			} else {
				msg.setObject(key, value);
			}
		}
		return msg;
	}

	private Map<String, Object> MapMessageToMap(MapMessage msg) throws JMSException {
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> e = msg.getPropertyNames();
		while (e.hasMoreElements()) {
			String str = (String) e.nextElement();
			Object value = msg.getObject(str);
			map.put(str, value);
		}
		return map;
	}

}