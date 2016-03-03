package event;

import com.hp.hpl.jena.rdf.model.Model;
import jms.JMSSender;
import model.TransferContext;
import model.TransferMassage;

import java.util.Hashtable;

import static utility.Constant.*;

public class SessionManager {
	private static SessionManager sessionManager = new SessionManager();
	private boolean flag;
	private Hashtable<String, TransferMassage> sessionTable;
	private Hashtable<String, Boolean> eventType;
	
	private SessionManager() {
		sessionTable = new Hashtable<String, TransferMassage>();
		flag = false;
		
		eventType = new Hashtable<String, Boolean>();
		
		eventType.put(SESSION_EVENT_TYPE, false);
		eventType.put(VIEW_EVENT_TYPE, false);
		eventType.put(NAVIGATION_EVENT_TYPE, false);
		eventType.put(ANNOTATION_EVENT_TYPE, false);
		
//		eventType.put(READING_EVENT_TYPE, false);
//		eventType.put(OUTCOME_EVENT_TYPE, false);
		
		Thread t = new Thread(new Timer());
		t.start();
	}
	
	public static SessionManager getInstance() {
		return sessionManager;
	}
	
	public class Timer implements Runnable {
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(flag) {
					JMSSender.sendMessage(eventType);
					
					eventType.replace(SESSION_EVENT_TYPE, false);
					eventType.put(VIEW_EVENT_TYPE, false);
					eventType.put(NAVIGATION_EVENT_TYPE, false);
					eventType.put(ANNOTATION_EVENT_TYPE, false);
					
//					eventType.replace(READING_EVENT_TYPE, false);
//					eventType.replace(OUTCOME_EVENT_TYPE, false);
					flag = false;
				}
			}
		}
		
	}
	
	public void addSession(String sessionID, TransferContext context) {
		sessionTable.put(sessionID, new TransferMassage(context));
	}
	
	public void removeSession(String sessionID) {
		sessionTable.remove(sessionID);
	}

	public void addEvent(String sessionID, Model model) {
		TransferMassage massage = sessionTable.get(sessionID);
		massage.addEventList(model);
	}
	
	public int getEventNumber(String sessionID) {
		return ((TransferMassage)sessionTable.get(sessionID)).getEventNumber();
	}

	public Model getEventList(String sessionID) {
		return ((TransferMassage)sessionTable.get(sessionID)).getEventList();
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setEventType(Hashtable<String, Boolean> eventType) {
		this.eventType = eventType;
	}

	public Hashtable<String, Boolean> getEventType() {
		return eventType;
	}
}
