package kr.ac.uos.ai.eventTransferService.transferCore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.LinkedList;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteConcern;

public class MongoDBTrigger implements Runnable{
	private MongoClient 									mongoClient;
	private DB 												db;
	private DBCollection 									collection;
	private EventTransferService							ets;
	private String											mongoDBAddress;
	
	public MongoDBTrigger(EventTransferService ets, String input) {
		this.mongoDBAddress = input;
		
		try {
			mongoClient = new MongoClient(new MongoClientURI(mongoDBAddress));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	
		
		mongoClient.setWriteConcern(WriteConcern.JOURNALED);
		
		db = mongoClient.getDB("LearningAnalyticsDB");
		
		if(db.collectionExists("event_collection")) {
			collection = db.getCollection("event_collection");
		} else {
			DBObject option = BasicDBObjectBuilder.start().add("capped", false).get();
			collection = db.createCollection("event_collection", option);
		}
		this.ets = ets;
	}
	
	
	public String triggerEventFromFile(int readCount) {
		String eventString = null;
		
		try {
			File path = new File(Configuration.DATA_LOCATION);
			File[] fileList = path.listFiles();
			
			JSONParser parser = new JSONParser();
			StringBuilder sb;
			String line;
			
			if(readCount < fileList.length) {
				sb = new StringBuilder();
				BufferedReader bufferedReader = new BufferedReader(
						new FileReader(fileList[readCount]));
				line = bufferedReader.readLine();
			
				while (line != null) {
					sb.append(line);
					sb.append("\n");
					line = bufferedReader.readLine();
				}
				eventString = sb.toString();

			//	System.out.println(fileList[readCount]);
			//	System.out.println(sb.toString());
			}else{
				return null;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return eventString;
	}
	

	public LinkedList<String> triger() {
		DBCursor cursor = collection.find();
		LinkedList<String> tempStringList = new LinkedList<String>();
		//System.out.println("aaaaaa");
		
		while(cursor.hasNext()) {
			
			DBObject object = cursor.next();
			tempStringList.add(object.toString());
			
			collection.remove(object);
		
			
		}
	
		return tempStringList;
	}
	
	
	@Override
	public void run() {
		LinkedList<String> tempStringList = null;
		while(true) {
			tempStringList = triger();
			if(tempStringList.isEmpty() == false)
				ets.eventInput(tempStringList);
			tempStringList = null;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
