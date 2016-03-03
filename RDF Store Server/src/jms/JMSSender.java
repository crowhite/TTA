package jms;

import java.util.Hashtable;
import java.util.Map.Entry;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import utility.Constant;

public class JMSSender {
	private static Connection connection;
	private static ConnectionFactory factory;
	private static Session session;
	private static Destination destination;
	private static MessageProducer producer;
	
	public static void sendMessage(Hashtable<String, Boolean> eventType) {
		try {
			factory = new ActiveMQConnectionFactory(Constant.SERVER_URL.replace("http", "tcp") + ":61616");
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("AnalysisDaemonQueue");
			producer = session.createProducer(destination);
			
			for (Entry<String, Boolean> entry : eventType.entrySet()) {
				if(entry.getValue()) {
					TextMessage message = session.createTextMessage(entry.getKey());
					producer.send(message);
				}
			}
			
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
}
