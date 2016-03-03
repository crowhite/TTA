package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.event;

public enum EventType {

	HighlightAnnotation 			("http://purl.imsglobal.org/ctx/caliper/v1/Context",
			      		 			"http://purl.imsglobal.org/caliper/v1/HighligntAnnotation",
									"highlighted"),
	AssignableEvent					("http://purl.imsglobal.org/ctx/caliper/v1/Context",
			      		 			"http://purl.imsglobal.org/caliper/v1/AssignableEvent",
			      					""),
	AssessmentEvent					("http://purl.imsglobal.org/ctx/caliper/v1/Context",
									"http://purl.imsglobal.org/caliper/v1/AssessmentEvent",
					     			"http://purl.imsglobal.org/vocab/caliper/v1/action#Started"),
	AssessmentItemEvent 			("http://purl.imsglobal.org/ctx/caliper/v1/Context",
					     			"http://purl.imsglobal.org/caliper/v1/AssessmentItemEvent",
					     			"completed"),
	ViewEvent						("http://purl.imsglobal.org/ctx/caliper/v1/Context",
									"http://purl.imsglobal.org/caliper/v1/ViewEvent",
					     			"http://purl.imsglobal.org/vocab/caliper/v1/action#Viewed"),
	SessionLoginEvent				  ("http://purl.imsglobal.org/ctx/caliper/v1/Context",
									"http://purl.imsglobal.org/caliper/v1/SessionEvent",
									"http://purl.imsglobal.org/vocab/caliper/v1/action#LoggedIn"),
	SessionLogoutEvent  			("http://purl.imsglobal.org/ctx/caliper/v1/Context",
									"http://purl.imsglobal.org/caliper/v1/SessionEvent",
									"http://purl.imsglobal.org/vocab/caliper/v1/action#LoggedOut"),
	NavigationEvent	    			("http://purl.imsglobal.org/ctx/caliper/v1/Context",
									"http://purl.imsglobal.org/caliper/v1/NavigationEvent",
									"http://purl.imsglobal.org/vocab/caliper/v1/action#NavigatedTo"),
	ReadingEvent	   				 ("http://purl.imsglobal.org/ctx/caliper/v1/Context",
									"http://purl.imsglobal.org/caliper/v1/ReadingEvent",
									"http://purl.imsglobal.org/vocab/caliper/v1/action#Viewed"),
	OutcomeEvent	    			("http://purl.imsglobal.org/ctx/caliper/v1/Context",
									"http://purl.imsglobal.org/caliper/v1/OutcomeEvent",
									"http://purl.imsglobal.org/vocab/caliper/v1/action#Graded"),
	AnnotationEventHighlighted		("http://purl.imsglobal.org/ctx/caliper/v1/Context",
									"http://purl.imsglobal.org/caliper/v1/AnnotationEvent",
									"http://purl.imsglobal.org/vocab/caliper/v1/action#Highlighted"	),
	AnnotationEventBookmarked		("http://purl.imsglobal.org/ctx/caliper/v1/Context",
									"http://purl.imsglobal.org/caliper/v1/AnnotationEvent",
									"http://purl.imsglobal.org/vocab/caliper/v1/action#Bookmarked");
	
	private final String						context;
	private final String						type;
	private final String						action;
	
	EventType(String context, String type, String action){
		this.context = context;
		this.type = type;
		this.action = action;
	}

	public String getContext() {
		return context;
	}

	public String getType() {
		return type;
	}
	
	public String getAction(){
		return action;
	}

}
