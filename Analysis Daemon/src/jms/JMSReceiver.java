package jms;

import java.io.IOException;
import java.util.LinkedList;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class JMSReceiver {
	private final String BROKER_URL = "tcp://aistore.uos.ac.kr:61616";
	private ConnectionFactory factory = null;
	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private MessageConsumer consumer = null;

	public RecievedData recieveMessage() {
		String result = null;
		try {
			factory = new ActiveMQConnectionFactory(BROKER_URL);
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("AnalysisDaemonQueue");
			consumer = session.createConsumer(destination);
			Message message = consumer.receive();

			if (message instanceof TextMessage) {
				TextMessage text = (TextMessage) message;
				result = text.getText();
				System.out.println("Recevie Message: " + result);
			}
			
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		RecievedData data = parseMsg(result);
		
		
		return data;
	}
	
	public RecievedData parseMsg(String input){
		  
		 
		   try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(input);
			
			RecievedData recievedData = new RecievedData();
			
			Element root = doc.getDocumentElement();
			
			recievedData.setType(root.getAttribute("type"));
			recievedData.setKey(root.getAttribute("keyValue"));
			
			NodeList nodeList = root.getElementsByTagName("data");
			
		
			if(recievedData.getType().equals("newType")){
				for(int i = 0; i < nodeList.getLength();i++){
					Element tempElement = (Element)nodeList.item(i);
					recievedData.addGraph(tempElement.getAttribute("value"));
				}
				
			}
			
			return recievedData;
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		   
		
		return null;
	}
	
}
