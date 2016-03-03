package utility;

import static utility.VirtuosoQueryUtility.INPUT_GRAPH;

public class VirtuosoQuery {
//	// Entity Count Query
//	public static final String PERSON_COUNT = "SELECT COUNT(?s) WHERE { GRAPH "
//			+ GRAPH + " { ?s :type <" + Constant.PERSON_TYPE + "> } }";
//	public static final String ANNOTATION_COUNT = "SELECT COUNT(?s) WHERE { GRAPH "
//			+ GRAPH + " { ?s :type <" + Constant.ANNOTATION_TYPE + "> } }";
//	public static final String GROUP_COUNT = "SELECT COUNT(?s) WHERE { GRAPH "
//			+ GRAPH + " { ?s :type <" + Constant.GROUP_TYPE + "> } }";
//	public static final String DEGITAL_RESOURCE_COUNT = "SELECT COUNT(?s) WHERE { GRAPH "
//			+ GRAPH
//			+ " { ?s :type <"
//			+ Constant.DEGITAL_RESOURCE_TYPE
//			+ "> } }";
//	public static final String ORGANIZATION_COUNT = "SELECT COUNT(?s) WHERE { GRAPH "
//			+ GRAPH + " { ?s :type <" + Constant.ORGANIZATION_TYPE + "> } }";
//
//	public static final String SESSION_COUNT = "SELECT COUNT(?s) WHERE { GRAPH "
//			+ GRAPH + " { ?s :type <" + Constant.SESSION_TYPE + "> } }";
//	public static final String READING_COUNT = "SELECT COUNT(?s) WHERE { GRAPH "
//			+ GRAPH + " { ?s :type <" + Constant.READING_TYPE + "> } }";
//	public static final String FRAME_COUNT = "SELECT COUNT(?s) WHERE { GRAPH "
//			+ GRAPH + " { ?s :type <" + Constant.READING_TYPE + "> } }";

	// Event Count Query
	public static final String EVENT_COUNT = "SELECT COUNT(?s) WHERE { GRAPH "
			+ INPUT_GRAPH + " { ?s :context ?o } }";
	
	public static String generateCountQuery(String type) {
		return "SELECT COUNT(?s) WHERE { GRAPH "
				+ INPUT_GRAPH + " { ?s :type <" + type + "> } }";
	}
	
	public static String genereteInsertQuery(String s, String p, String o){
		return "INSERT { GRAPH " + VirtuosoQueryUtility.META_DATA_GRAPH + " { <" + s + "> " + "<" + p + "> " + "<" + o + ">" + " } }";
	}
	
}
