package kr.ac.uos.ai.eventTransferService.interactionModel;


import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import kr.ac.uos.ai.eventTransferService.exchangemodel.RDFMessage;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.Entity;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.event.Event;
import kr.ac.uos.ai.eventTransferService.transferCore.Configuration;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class TransferManager {
	
	
	public void transferMessage(RDFMessage msg){
		String sessionId;
		
		System.out.println(msg.toRDFString());
		for(int i = 0 ;i < msg.getEntityList().size();i++){
			sendEntity(msg.getEntityList().get(i));
		}
		
		sessionId = openSession(msg);
		
	
		for(int i =0; i < msg.getEventList().size();i++){
			sendEvent(msg.getEventList().get(i),sessionId);
		}
		
	}
	
	public String openSession(RDFMessage msg){
		String sessionID = "";
	
		try {
			String modelText = "";
			modelText +=  "@prefix " + Configuration.PREFIX_NAME + ": <" + Configuration.PREFIX_URL + ">.\n";
			modelText += msg.toRDFString();
			Model model = ModelFactory.createDefaultModel();

			model.read(new ByteArrayInputStream(modelText.getBytes()), null, "Turtle");
			
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			model.write(System.out,"TTL");
			
			model.write(out, "TTL");
			
			byte[] postData = out.toByteArray();

			int postDataLength = postData.length;
			URL url = new URL(Configuration.EVENT_URL_STRING);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "text/turtle");
			conn.setRequestProperty("charset", "utf-8");
			conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
			
			
			conn.setUseCaches(false);
			DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
			writer.write(postData);

			
			
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			sessionID = conn.getHeaderFields().get("Session-id").get(0);
			System.out.println("SessionID : " + sessionID);
			
			writer.close();
			reader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sessionID;
	}
	
	@SuppressWarnings("unused")
	public void sendEntity(Entity entity){

		try {
			
			String modelText = "";
			modelText +=  "@prefix " + Configuration.PREFIX_NAME + ": <" + Configuration.PREFIX_URL + ">.\n";
			modelText += entity.toString();
			Model model = ModelFactory.createDefaultModel();

			System.out.println("entity : " + modelText);
			
			model.read(new ByteArrayInputStream(modelText.getBytes()), null, "Turtle");
			ByteArrayOutputStream out = new ByteArrayOutputStream();
	
		
			byte[] postData = out.toByteArray();

			int postDataLength = postData.length;
			URL url = new URL(Configuration.ENTITY_URL_STRING);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "text/turtle");
			conn.setRequestProperty("charset", "utf-8");
			conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
			conn.setUseCaches(false);
			DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
			writer.write(postData);

			String line = "";
			String lastLine = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			while (true) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(line==null){
					System.out.println("LastLine : " + lastLine);
					entity.setId(lastLine);
					break;
				}
				else {
//					System.out.println(line);
					lastLine = line;
					line = reader.readLine();
					
				}
				
				
			}
			writer.close();
			reader.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private void sendEvent(Event event, String sessionID) {
		try {
			String modelText = "";
			modelText +=  "@prefix " + Configuration.PREFIX_NAME + ": <" + Configuration.PREFIX_URL + ">.\n";
			modelText += event.toString();
			Model model = ModelFactory.createDefaultModel();
			
			System.out.println(modelText);
			model.read(new ByteArrayInputStream(modelText.getBytes()), null, "Turtle");
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			model.write(out, "TTL");
			model.write(System.out,"TTL");
			
			byte[] postData = out.toByteArray();

			int postDataLength = postData.length;
			URL url = new URL(Configuration.EVENT_URL_STRING);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "text/turtle");
			conn.setRequestProperty("charset", "utf-8");
			conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
			conn.setUseCaches(false);
			conn.setRequestProperty("Session-id", sessionID);
			conn.setRequestProperty("EndOfEvent", "true");
			DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
			writer.write(postData);


			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			writer.close();
			reader.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
