package event;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.TransferContext;
import utility.Constant;
import utility.JenaQueryUtility;
import utility.VirtuosoQuery;
import utility.VirtuosoQueryUtility;
import view.ServerLogger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import static utility.Constant.*;
import static utility.JenaQuery.*;

public class EventHandler implements HttpHandler {
	SessionManager sessionManager = SessionManager.getInstance();

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		if(!"post".equalsIgnoreCase(exchange.getRequestMethod())) {
			// error response
			exchange.sendResponseHeaders(405, 0);
			exchange.close();
		} else {
			Model model = ModelFactory.createDefaultModel();
			model.read(exchange.getRequestBody(), null, "TTL");
			
			Headers requestHeaders = exchange.getRequestHeaders();
			Headers responseHeaders = exchange.getResponseHeaders();
//			OutputStream responseBody = exchange.getResponseBody();
			
			if(!requestHeaders.containsKey("Session-ID")) {
				// validate transfer context
				if(!validateTransferContext(model)) {
					// error response
					exchange.sendResponseHeaders(422, 0);
				} else {
					ServerLogger.printLog("Transfer Context Recieved : \n");
					StringWriter stringWriter = new StringWriter();
					model.write(stringWriter, "N-Triples");
					ServerLogger.printLog(stringWriter.toString() + "\n\n");
					
					// create session
					String sessionID = createSession(model);

					// response session id
					responseHeaders.set("Session-ID", sessionID);
					exchange.sendResponseHeaders(200, 0);
				}
				
			} else {
				ServerLogger.printLog("Event Recieved : \n");
				StringWriter stringWriter = new StringWriter();
				model.write(stringWriter, "N-Triples");
				ServerLogger.printLog(stringWriter.toString() + "\n\n");
				
				String sessionID = requestHeaders.get("Session-ID").get(0);

				// add event
				sessionManager.addEvent(sessionID, model);

				if(requestHeaders.get("EndOfEvent").get(0) == "false")
					exchange.sendResponseHeaders(200, 0);
				else {
					int eventNumber = sessionManager.getEventNumber(sessionID);
					Model eventList = sessionManager.getEventList(sessionID);
					if(validateEvent(eventNumber, eventList)) {
						exchange.sendResponseHeaders(200, 0);
						storeEvent(eventNumber,eventList);
					} else {
						exchange.sendResponseHeaders(422, 0);
					}
					
					sessionManager.removeSession(sessionID);
				}
			}
			
			exchange.close();
		}
	}

	private void storeEvent(int eventNumber, Model eventList) {
		int count = VirtuosoQueryUtility.queryResultToInt(VirtuosoQueryUtility.select(VirtuosoQuery.EVENT_COUNT));
		List<String> oldID = JenaQueryUtility.runSelectQuery(eventList, generateSubjectQuery(EVENT_VALIDATION), "s");

		StringWriter out = new StringWriter();
		eventList.write(out, "N-Triples");
		String rdfString = out.toString();
		
		for(int i = 0; i < eventNumber; i ++) {
			rdfString = rdfString.replace(oldID.get(i), createEventID(count+i));
		}
		rdfString = rdfString.replace("\n", "");
		
		eventList = ModelFactory.createDefaultModel();
		eventList.read(new ByteArrayInputStream(rdfString.getBytes()), null, "N-Triples");

		sessionManager.setEventType(checkEventType(eventList));
		sessionManager.setFlag(VirtuosoQueryUtility.insert(eventList));
	}
	
	private Hashtable<String, Boolean> checkEventType(Model model) {
		Hashtable<String, Boolean> hash = sessionManager.getEventType();
		List<String> eventType = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(TYPE), "o");
		
		for (String string : eventType) {
			if(string.equals(SESSION_EVENT_TYPE))
				hash.replace(SESSION_EVENT_TYPE, true);
			else if(string.equals(VIEW_EVENT_TYPE))
				hash.replace(VIEW_EVENT_TYPE, true);
			else if(string.equals(NAVIGATION_EVENT_TYPE))
				hash.replace(NAVIGATION_EVENT_TYPE, true);
			else if(string.equals(ANNOTATION_EVENT_TYPE))
				hash.replace(ANNOTATION_EVENT_TYPE, true);
			
//			else if(string.equals(READING_EVENT_TYPE))
//				hash.replace(READING_EVENT_TYPE, true);
//			else if(string.equals(OUTCOME_EVENT_TYPE))
//				hash.replace(OUTCOME_EVENT_TYPE, true);
//			else if(string.equals(ASSESSMENT_EVENT_TYPE))
//				hash.replace(ASSESSMENT_EVENT_TYPE, true);
			
			if(!hash.contains(false))
				break;
		}
		
//		if(JenaQueryUtility.runAskQuery(model, JenaQuery.ASSEMENT_ITEM_EVENT_TYPE))
//			hash.put(Constant.ASSESSMENT_ITEM_EVENT_TYPE, true);
//		
//		if(JenaQueryUtility.runAskQuery(model, JenaQuery.SESSION_EVENT_TYPE))
//			hash.put(Constant.SESSION_EVENT_TYPE, true);
//		
//		if(JenaQueryUtility.runAskQuery(model, JenaQuery.VIEW_EVENT_TYPE))
//			hash.put(Constant.VIEW_EVENT_TYPE, true);
//		
//		if(JenaQueryUtility.runAskQuery(model, JenaQuery.NAVIGATION_EVENT_TYPE))
//			hash.put(Constant.NAVIGATION_EVENT_TYPE, true);
		
		
		
		return hash;
	}

	private CharSequence createEventID(int i) {
		return Constant.PREFIX + "event" + i;
	}

	private boolean validateEvent(int eventNumber, Model eventList) {
		int count = JenaQueryUtility.runCountQuery(eventList, generateSubjectQuery(EVENT_VALIDATION));
		
		if(count == eventNumber)
			return true;
		
//		return false;
		return true;
	}

	private String createSession(Model model) {
		// create SessionID
		String sessionID = UUID.randomUUID().toString();

		// create Transfer Context
		TransferContext context = createContext(model);
		
		// addSession
		sessionManager.addSession(sessionID, context);
		
		return sessionID;
	}

	private TransferContext createContext(Model model) {
		TransferContext context = new TransferContext();
		
		context.setTransferID(JenaQueryUtility.runSelectQuery(model, generateSubjectQuery(TRANSFER_ID), "s").get(0));
		context.setSender(JenaQueryUtility.runSelectQuery(model, generateObjectQuery(TRANSFER_SENDER), "o").get(0));
		context.setReciever(JenaQueryUtility.runSelectQuery(model, generateObjectQuery(TRANSFER_RECIEVER), "o").get(0));
		context.setTransferInitiateTime(JenaQueryUtility.runSelectQuery(model, generateObjectQuery(TRANSFER_INITIATE_TIME), "o").get(0));
		context.setContentSize(Integer.parseInt(JenaQueryUtility.runSelectQuery(model, generateObjectQuery(TRANSFER_CONTENT_SIZE), "o").get(0)));
		context.setEventNumber(Integer.parseInt(JenaQueryUtility.runSelectQuery(model, generateObjectQuery(TRANSFER_EVENT_NUMBER), "o").get(0)));
		
		JenaQueryUtility.runDeleteQuery(model, DELETE_TRANSFER_QUERY);
		context.setEntityList(model);
		
		return context;
	}

	private boolean validateTransferContext(Model model) {
		List<String> queryResult;
		
		queryResult = JenaQueryUtility.runSelectQuery(model, generateSubjectQuery(TRANSFER_ID), "s");
		
		if(queryResult.size() == 1)
			return true;
		
		return false;
	}

}
