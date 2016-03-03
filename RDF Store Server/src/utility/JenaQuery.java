package utility;

import utility.Constant;

import java.util.Hashtable;

import static utility.Constant.*;
import static utility.VirtuosoQueryUtility.*;

public class JenaQuery {
	// Transfer Context Condition
	public static final String TRANSFER_ID = "?s :sender ?o1; :receiver ?o2; :transferInitiateTime ?o3; :contentSize ?o4; :eventNumber ?o5";
	public static final String TRANSFER_SENDER = "?s :sender ?o";
	public static final String TRANSFER_RECIEVER = "?s :receiver ?o";
	public static final String TRANSFER_INITIATE_TIME = "?s :transferInitiateTime ?o";
	public static final String TRANSFER_CONTENT_SIZE = "?s :contentSize ?o";
	public static final String TRANSFER_EVENT_NUMBER = "?s :eventNumber ?o";
	
	
	
	
	public static final String DELETE_TRANSFER_QUERY = "WITH <" + Constant.PREFIX + "> DELETE { ?s ?p ?o } WHERE { ?s :sender ?sender. ?s ?p ?o }";


	
	

	// Entity Validation Condition
	public static final String DEFAULT_ENTITY_VALIDATAION = "?s :name ?name; :dateCreated ?created; :dateModified ?modified; :type ?type";
	public static final String PERSON_ENTITY_VALIDATION = DEFAULT_ENTITY_VALIDATAION;
	public static final String APPLICATION_ENTITY_VALIDATION = DEFAULT_ENTITY_VALIDATAION;
	public static final String READING_ENTITY_VALIDATION = DEFAULT_ENTITY_VALIDATAION;
	public static final String SESSION_ENTITY_VALIDATION = "?s :type ?type; :dateCreated ?created; :dateModified ?modified";
	public static final String RESULT_ENTITY_VALIDATION = "?s :type ?type; :totalScore ?total";
	public static final String ATTEMPT_ENTITY_VALIDATION = "?s :type ?type; :actor ?actor; :assignable ?assignable; :count ?count";
	public static final String ASSESSMENT_ENTITY_VALIDATION = "?s :type ?type; :name ?name";
	
	
	public static final String FRAME_ENTITY_VALIDATION = "?s :type ?type; :name ?name; :index ?index";
	public static final String VOLUME_ENTITY_VALIDATION = "?s :type ?type; :name ?name";
	public static final String HIGHLIGHT_ANNOTATION_ENTITY_VALIDATION = "?s :type ?type; :dateCreated ?created; :dateModified ?modified; :annotated ?annotated; :selectionStart ?selectionStart; :selectionEnd ?selectionEnd; :selectionText ?selectionEnd";
	public static final String BOOKMARK_ANNOTATION_ENTITY_VALIDATION = "?s :type ?type; :dateCreated ?created; :dateModified ?modified; :annotated ?annotated; :selectionText ?selectionEnd";
	
	
	

	// Event Validation Condition
	public static final String EVENT_VALIDATION = "?s :context ?context; :type ?type; :actor ?actor; :action ?action; :object ?object; :eventTime ?start";

	
	
	
	

	// Entity Data Condition
	public static final String TYPE = "?s :type ?o";
	public static final String NAME = "?s :name ?o";
	public static final String DATE_CREATED = "?s :dateCreated ?o";
	public static final String DATE_MODIFIED = "?s :dateModified ?o";
	public static final String TOTAL_SCORE = "?s :totalScore ?o";
	public static final String ACTOR = "?s :actor ?o";
	public static final String ASSIGNABLE = "?s :assignable ?o";
	public static final String COUNT = "?s :count ?o";
	
	public static final String INDEX = "?s :index ?o";
	public static final String ANNOTATED = "?s :annotated ?o";
	public static final String SELECTION_START = "?s :selectionStart ?o";
	public static final String SELECTION_END = "?s :selectionEnd ?o";
	public static final String SELECTION_TEXT = "?s :selectionText ?o";
	
	
	
	
	
	// Event Type Query
//	public static final String ASSEMENT_ITEM_EVENT_TYPE = "SELECT ?s WHERE { ?s :type <"
//			+ Constant.ASSESSMENT_ITEM_EVENT_TYPE
//			+ "> }";
//	public static final String SESSION_EVENT_TYPE = "SELECT ?s WHERE { ?s :type <"
//			+ Constant.SESSION_EVENT_TYPE
//			+ "> }";
//	public static final String VIEW_EVENT_TYPE = "SELECT ?s WHERE { ?s :type <"
//			+ Constant.VIEW_EVENT_TYPE
//			+ "> }";
//	public static final String NAVIGATION_EVENT_TYPE = "SELECT ?s WHERE { ?s :type <"
//			+ Constant.NAVIGATION_EVENT_TYPE
//			+ "> }";;
	
	
			
			
// Query Generate Methods
			
	public static String generateSubjectQuery(String condition) {
		return "SELECT ?s WHERE { " + condition + " }";
	}
	
	public static String generateObjectQuery(String condition) {
		return "SELECT ?o WHERE { " + condition + " }";
	}
			
			
	// Generate Entity Check Query
	public static String generateCheckEntityQuery(String type, Hashtable<String, String> entityData) {
		String result = null;

		switch (type) {
		case SOFTWARE_APPLICATION_TYPE:
		case READING_TYPE:
			result = "SELECT ?s WHERE { GRAPH "
					+ INPUT_GRAPH
					+ " { ?s :name \""
					+ entityData.get("name") 
					+ "\"; :dateCreated \""
					+ entityData.get("created")
					+ "\"; :dateModified \""
					+ entityData.get("modified")
					+ "\"; :type <"
					+ entityData.get("type") 
					+ "> } }";
			break;
			
		case SESSION_TYPE:
			result = "SELECT ?s WHERE { GRAPH "
					+ INPUT_GRAPH
					+ " { ?s :dateCreated \""
					+ entityData.get("created")
					+ "\"; :dateModified \""
					+ entityData.get("modified")
					+ "\"; :type <"
					+ entityData.get("type") 
					+ "> } }";
			break;
			
		case RESULT_TYPE:
			result = "SELECT ?s WHERE { GRAPH "
					+ INPUT_GRAPH
					+ " { ?s :totalScore \""
					+ entityData.get("total")
					+ "\"; :type <"
					+ entityData.get("type") 
					+ "> } }";
			break;
			
		case ATTEMPT_TYPE:
			result = "SELECT ?s WHERE { GRAPH "
					+ INPUT_GRAPH
					+ " { ?s :actor <"
					+ entityData.get("actor")
					+ ">; :assignable <"
					+ entityData.get("assignable")
					+ ">; :count \""
					+ entityData.get("count")
					+ "\"; :type <"
					+ entityData.get("type") 
					+ "> } }";
			break;
			
		case PERSON_TYPE:
		case ASSESSMENT_TYPE:
		case VOLUME_TYPE:
			result = "SELECT ?s WHERE { GRAPH "
					+ INPUT_GRAPH
					+ " { ?s :name \""
					+ entityData.get("name") 
					+ "\"; :type <"
					+ entityData.get("type") 
					+ "> } }";
			break;
			
			
		case FRAME_TYPE:
			result = "SELECT ?s WHERE { GRAPH "
					+ INPUT_GRAPH
					+ " { ?s :name \""
					+ entityData.get("name") 
					+ "\"; :type <"
					+ entityData.get("type") 
					+ ">; :index \""
					+ entityData.get("index")
					+ "\" } }";
			break;
			
		case HIGHLIGHT_ANNOTATION_TYPE:
			result = "SELECT ?s WHERE { GRAPH "
					+ INPUT_GRAPH
					+ " { ?s :type <"
					+ entityData.get("type") 
					+ ">; :dateCreated \""
					+ entityData.get("created")
					+ "\"; :dateModified \""
					+ entityData.get("modified")
					+ "\"; :annotated <"
					+ entityData.get("annotated")
					+ ">; :selectionStart \""
					+ entityData.get("selelctionStart")
					+ "\" :selectionEnd \""
					+ entityData.get("selectionEnd")
					+ "\" :selectionText \""
					+ entityData.get("selectionText")
					+ "\" } }";
			break;
			
		case BOOKMARK_ANNOTATION_TYPE:
			result = "SELECT ?s WHERE { GRAPH "
					+ INPUT_GRAPH
					+ " { ?s :type <"
					+ entityData.get("type") 
					+ ">; :dateCreated \""
					+ entityData.get("created")
					+ "\"; :dateModified \""
					+ entityData.get("modified")
					+ "\"; :annotated <"
					+ entityData.get("annotated")
					+ ">; :selectionText \""
					+ entityData.get("selectionText")
					+ "\" } }";
			break;
			
		default:
			break;
		}

		return result;
	}
}
