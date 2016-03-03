package entity;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import utility.JenaQueryUtility;
import utility.VirtuosoQueryUtility;
import view.ServerLogger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Hashtable;

import static utility.Constant.*;
import static utility.JenaQuery.*;
import static utility.VirtuosoQuery.*;

public class EntityHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		if(!"post".equalsIgnoreCase(exchange.getRequestMethod())) {
			// error response
			exchange.sendResponseHeaders(405, 0);
			exchange.close();
		} else {
			String id;
			
			Model model = ModelFactory.createDefaultModel();
			model.read(exchange.getRequestBody(), null, "TTL");
			
			OutputStream responseBody = exchange.getResponseBody();
			Headers responseHeaders = exchange.getResponseHeaders();
			
			if(!validateEntity(model)) {
				// error response
				exchange.sendResponseHeaders(422, 0);
			
			} else {
				ServerLogger.printLog("Entity Recieved : \n");
				StringWriter stringWriter = new StringWriter();
				model.write(stringWriter, "N-Triples");
				ServerLogger.printLog(stringWriter.toString() + "\n\n");
				
				id = checkEntityID(model);
				if (id == null) {
					// create entity ID
					id = createEntityID(model);
					storeEntity(id, model);
				}
				
				System.out.println("test : " + id);
				
				// send entity ID
				exchange.sendResponseHeaders(200, 0);
				responseHeaders.set("Content-Type", "text/plain");
				responseBody.write(id.getBytes());
			}
			exchange.close();
		}
	}

	private void storeEntity(String id, Model model) {
		String oldID = JenaQueryUtility.runSelectQuery(model, generateSubjectQuery(TYPE), "s").get(0);
		
		StringWriter out = new StringWriter();
		model.write(out, "N-Triples");
		String rdfString = out.toString();
		rdfString = rdfString.replace(oldID, id);
		
		model = ModelFactory.createDefaultModel();
		model.read(new ByteArrayInputStream(rdfString.getBytes()), null, "N-Triples");
		
		VirtuosoQueryUtility.insert(model);
	}

	private String createEntityID(Model model) {
		String type = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(TYPE), "o").get(0);
		String id = null;
		String queryResult;
		int count;
		
		switch(type) {
		case PERSON_TYPE :
//			queryResult = VirtuosoQueryUtility.select(generateCountQuery(PERSON_TYPE));
//			count = VirtuosoQueryUtility.queryResultToInt(queryResult);
//			id = PREFIX + "person/user" + String.valueOf(count);
			id = JenaQueryUtility.runSelectQuery(model, generateSubjectQuery(PERSON_ENTITY_VALIDATION), "s").get(0);
			break;
			
		case SOFTWARE_APPLICATION_TYPE :
			queryResult = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(NAME), "o").get(0);
			id = PREFIX + "software/" + queryResult.toLowerCase().replace(" ", "_");
			break;
			
		case SESSION_TYPE :
			queryResult = VirtuosoQueryUtility.select(generateCountQuery(SESSION_TYPE));
			count = VirtuosoQueryUtility.queryResultToInt(queryResult);
			id = PREFIX + "session" + String.valueOf(count);
			break;
			
		case READING_TYPE :
			queryResult = VirtuosoQueryUtility.select(generateCountQuery(READING_TYPE));
			count = VirtuosoQueryUtility.queryResultToInt(queryResult);
			id = PREFIX + "reading" + String.valueOf(count);
			break;
			
		case RESULT_TYPE:
			queryResult = VirtuosoQueryUtility.select(generateCountQuery(RESULT_TYPE));
			count = VirtuosoQueryUtility.queryResultToInt(queryResult);
			id = PREFIX + "result" + String.valueOf(count);
			break;
			
		case ATTEMPT_TYPE:
			queryResult = VirtuosoQueryUtility.select(generateCountQuery(ATTEMPT_TYPE));
			count = VirtuosoQueryUtility.queryResultToInt(queryResult);
			id = PREFIX + "attempt" + String.valueOf(count);
			break;
			
		case ASSESSMENT_TYPE:
			id = JenaQueryUtility.runSelectQuery(model, generateSubjectQuery(ASSESSMENT_ENTITY_VALIDATION), "s").get(0);
			break;
			
		case FRAME_TYPE:
			queryResult = VirtuosoQueryUtility.select(generateCountQuery(FRAME_TYPE));
			count = VirtuosoQueryUtility.queryResultToInt(queryResult);
			id = PREFIX + "frame" + String.valueOf(count);
			break;
			
		case VOLUME_TYPE:
			id = JenaQueryUtility.runSelectQuery(model, generateSubjectQuery(VOLUME_ENTITY_VALIDATION), "s").get(0);
			break;
			
		case HIGHLIGHT_ANNOTATION_TYPE:
			queryResult = VirtuosoQueryUtility.select(generateCountQuery(HIGHLIGHT_ANNOTATION_TYPE));
			count = VirtuosoQueryUtility.queryResultToInt(queryResult);
			id = PREFIX + "highlight" + String.valueOf(count);
			break;
			
		case BOOKMARK_ANNOTATION_TYPE:
			queryResult = VirtuosoQueryUtility.select(generateCountQuery(BOOKMARK_ANNOTATION_TYPE));
			count = VirtuosoQueryUtility.queryResultToInt(queryResult);
			id = PREFIX + "bookmark" + String.valueOf(count);
			break;
			
		default :
			break;
		}
		 
		return id;
	}
	
	private String checkEntityID(Model model) {
		Hashtable<String, String> entityData = new Hashtable<String, String>();
		String queryString = null;
		String name = "";
		String created = "";
		String modified = "";
		String total = "";
		String actor = "";
		String assignable = "";
		String count = "";
		
		String index = ""; 
		String annotated = "";
		String selectionStart = "";
		String selectionEnd = "";
		String selectionText = "";
		
		String type = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(TYPE), "o").get(0);
		
		switch(type) {
		case SOFTWARE_APPLICATION_TYPE:
		case READING_TYPE:
			name = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(NAME), "o").get(0);
			created =  JenaQueryUtility.runSelectQuery(model, generateObjectQuery(DATE_CREATED), "o").get(0);
			modified = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(DATE_MODIFIED), "o").get(0);
			break;
			
		case SESSION_TYPE:
			created =  JenaQueryUtility.runSelectQuery(model, generateObjectQuery(DATE_CREATED), "o").get(0);
			modified = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(DATE_MODIFIED), "o").get(0);
			break;
			
		case RESULT_TYPE:
			total = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(TOTAL_SCORE), "o").get(0);
			return null;
			
		case ATTEMPT_TYPE:
			actor = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(ACTOR), "o").get(0);
			assignable = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(ASSIGNABLE), "o").get(0);
			count = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(COUNT), "o").get(0);
			break;
			
		case PERSON_TYPE:
		case ASSESSMENT_TYPE:
		case VOLUME_TYPE:
			name = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(NAME), "o").get(0);
			break;
			
			
			
		case FRAME_TYPE:
			name = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(NAME), "o").get(0);
			index = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(INDEX), "o").get(0);
			break;
			
		case HIGHLIGHT_ANNOTATION_TYPE:
			created =  JenaQueryUtility.runSelectQuery(model, generateObjectQuery(DATE_CREATED), "o").get(0);
			modified = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(DATE_MODIFIED), "o").get(0);
			annotated = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(ANNOTATED), "o").get(0);
			selectionStart = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(SELECTION_START), "o").get(0);
			selectionEnd = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(SELECTION_END), "o").get(0);
			selectionText = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(SELECTION_TEXT), "o").get(0);
			break;
			
		case BOOKMARK_ANNOTATION_TYPE:
			created =  JenaQueryUtility.runSelectQuery(model, generateObjectQuery(DATE_CREATED), "o").get(0);
			modified = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(DATE_MODIFIED), "o").get(0);
			annotated = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(ANNOTATED), "o").get(0);
			selectionText = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(SELECTION_TEXT), "o").get(0);
			break;
			
			
		}
		
		entityData.put("type", type);
		entityData.put("name", name);
		entityData.put("created", created);
		entityData.put("modified", modified);
		entityData.put("total", total);
		entityData.put("actor", actor);
		entityData.put("assignable", assignable);
		entityData.put("count", count);
		
		entityData.put("index", index);
		entityData.put("annotated", annotated);
		entityData.put("selectionStart", selectionStart);
		entityData.put("selectionEnd", selectionEnd);
		entityData.put("selectionText", selectionText);
		
		queryString = generateCheckEntityQuery(type, entityData);
		
		String queryResult = VirtuosoQueryUtility.select(queryString);
		String result = VirtuosoQueryUtility.parseQueryResult(queryResult);
		
		return result;
		
	}
	
	private boolean validateEntity(Model model) {
		String type = JenaQueryUtility.runSelectQuery(model, generateObjectQuery(TYPE), "o").get(0);
		String queryString = null;
		
		switch(type) {
		case PERSON_TYPE :
			queryString = generateSubjectQuery(PERSON_ENTITY_VALIDATION);
			break;
			
		case SOFTWARE_APPLICATION_TYPE :
			queryString = generateSubjectQuery(APPLICATION_ENTITY_VALIDATION);
			break;
			
		case SESSION_TYPE :
			queryString = generateSubjectQuery(SESSION_ENTITY_VALIDATION);
			break;
			
		case READING_TYPE :
			queryString = generateSubjectQuery(READING_ENTITY_VALIDATION);
			break;
			
		case RESULT_TYPE :
			queryString = generateSubjectQuery(RESULT_ENTITY_VALIDATION);
			break;
			
		case ATTEMPT_TYPE :
			queryString = generateSubjectQuery(ATTEMPT_ENTITY_VALIDATION);
			break;
			
		case ASSESSMENT_TYPE:
			queryString = generateSubjectQuery(ASSESSMENT_ENTITY_VALIDATION);
			break;
			
		default :
			break;
		}
		
//		return JenaQueryUtility.runAskQuery(model, queryString);
		return true;
	}
}
