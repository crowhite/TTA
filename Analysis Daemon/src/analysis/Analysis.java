package analysis;

import java.util.HashMap;
import java.util.LinkedList;

import query.Constant;
import query.VirtuosoQueryUtility;
import rcaller.RCaller;
import rcaller.RCode;

public class Analysis {
	private final String RSCRIPT_PATH = "/usr/bin/Rscript";
	private final String RCODE_PATH = "/home/user/tta/jshome/rcode/Demo2/";
	private HashMap<String,LinkedList<String>> eventTypeMap;
	private RCaller caller;
	private RCode rCode;
	
	public Analysis() {
		caller = new RCaller();
		caller.setRscriptExecutable(RSCRIPT_PATH);
		eventTypeMap = new HashMap<String,LinkedList<String>>();
		rCode = new RCode();
		rCode.clear();
		
		
		init();		
	}
	
	public void init(){
		LinkedList<String> sessionEventList = new LinkedList<String>();
		
		sessionEventList.add("SessionAnalysisResult");
		sessionEventList.add("SessionEngagementResult");
		sessionEventList.add("SessionEventAnalysis.R");
		
		eventTypeMap.put(Constant.SESSION_EVENT_TYPE, sessionEventList);
		
		LinkedList<String> navigationEventList = new LinkedList<String>();
		
		navigationEventList.add("ViewEventAnalysisResult");
		navigationEventList.add("ViewEngagementResult");
		navigationEventList.add("NavigationEngagementResult");
		navigationEventList.add("ReadingEventAnalysis.R");
		
		eventTypeMap.put(Constant.NAVIGATION_EVENT_TYPE, navigationEventList);
		
		LinkedList<String> annotationEventList = new LinkedList<String>();
		
		annotationEventList.add("AnnotationEngagementResult");
		annotationEventList.add("AnnotationEventAnalysis.R");
		
		eventTypeMap.put(Constant.ANNOTATION_EVENT_TYPE, navigationEventList);
	}
	
	public void addEventType(String newKey, LinkedList<String> newType){
		
		eventTypeMap.put(newKey, newType);
		
	}
	
	public void runAnalysisCode(String event) {
		LinkedList<String> list = eventTypeMap.get(event);
		
		for(int i = 0; i < list.size() - 1;i++){
			VirtuosoQueryUtility.drop(list.get(i));
		}
		
		rCode.R_source(RCODE_PATH + list.get(list.size()));
		System.out.println("Analysis Complete : " + event);
		
		/*
		switch (event) {
		case Constant.SESSION_EVENT_TYPE:
			VirtuosoQueryUtility.drop("SessionAnalysisResult");
			VirtuosoQueryUtility.drop("SessionEngagementResult");
			rCode.R_source(RCODE_PATH + "SessionEventAnalysis.R");
			System.out.println("SessionEventAnalysis Complete\n");
			break;

		case Constant.VIEW_EVENT_TYPE:
		case Constant.NAVIGATION_EVENT_TYPE:
			VirtuosoQueryUtility.drop("ViewEventAnalysisResult");
			VirtuosoQueryUtility.drop("ViewEngagementResult");
			VirtuosoQueryUtility.drop("NavigationEngagementResult");
			rCode.R_source(RCODE_PATH + "ReadingEventAnalysis.R");
			System.out.println("ReadingEventAnalysis Complete\n");
			break;
			
		case Constant.ANNOTATION_EVENT_TYPE:
			VirtuosoQueryUtility.drop("AnnotationEngagementResult");
			rCode.R_source(RCODE_PATH + "AnnotationEventAnalysis.R");
			System.out.println("AnnotationEventAnalysis Complete\n");
			
//		case Constant.OUTCOME_EVENT_TYPE:
//			rCode.R_source(RCODE_PATH + "OutcomeEventAnalysis.R");
//			System.out.println("OutcomeEventAnalysis Complete\n");
//			break;
			
			

		default:
			break;
		}
		*/
		
		caller.setRCode(rCode);
		caller.runOnly();
		
		caller.cleanRCode();
		rCode.clear();
	}

}
