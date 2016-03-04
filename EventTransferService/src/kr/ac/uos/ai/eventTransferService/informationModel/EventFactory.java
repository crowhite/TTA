package kr.ac.uos.ai.eventTransferService.informationModel;

import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.AssessmentItem;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.Person;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.Session;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.SoftwareApplication;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.event.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class EventFactory {
	private static EventFactory factory;
	private static int sessionEventcount = 1;
	private static int viewEventcount = 1;
	private static int navigationEventcount = 1;
	private static int annotationEventcount = 1;
	private static int count = 1;

	private EntityFactory entityFactory;
	
	private EventFactory() {
		entityFactory = EntityFactory.getInstance();
	}

	
	public static synchronized EventFactory getInstance(){
		if(factory == null){
			factory = new EventFactory();
		}
		
		return factory;
	}
	
	public Event makeEvent(String jsonFile) throws CaliperEventParseException{
		JSONParser parser = new JSONParser();
		Event event = null;

		
		if(((int)jsonFile.charAt(0)) == 65279){
			jsonFile = jsonFile.substring(1);
		}
		
		try {
			JSONObject object = (JSONObject)parser.parse(jsonFile);
	
			String type = object.get("@type").toString();
			String action = object.get("action").toString();
	
			
			if(type.equals(EventType.AssessmentEvent.getType())){
				event = createAssessmentEvent(object);
			}else if(type.equals(EventType.AssessmentItemEvent.getType())){
				event = createAssessmentItemEvent(object);
			}else if(type.equals(EventType.NavigationEvent.getType())){
				event = createNavigationEvent(object);
			}else if(type.equals(EventType.OutcomeEvent.getType())){
				event = createOutcomeEvent(object);
			}else if(type.equals(EventType.SessionLoginEvent.getType()) && action.equals(EventType.SessionLoginEvent.getAction())){
				event = createSessionLoginEvent(object);
				//event = null;
			}else if(type.equals(EventType.SessionLogoutEvent.getType()) && action.equals(EventType.SessionLogoutEvent.getAction())){
				event = createSessionLogoutEvent(object);
				//event = null;
			}else if(type.equals(EventType.ViewEvent.getType())){
				event = createViewEvent(object);
			}else if(type.equals(EventType.AnnotationEventHighlighted.getType()) && action.equals(EventType.AnnotationEventHighlighted.getAction())){
				event = createAnnotationEventHighlighted(object);
			}else if(type.equals(EventType.AnnotationEventBookmarked.getType())  && action.equals(EventType.AnnotationEventBookmarked.getAction())){
				event = createAnnotationEventBookmarked(object);
			}
			
		} catch (ParseException e) {
		
			e.printStackTrace();
		}
		
		return event;
	}


	private Event createAnnotationEventBookmarked(JSONObject object) throws CaliperEventParseException{
		AnnotationEventBookmarked event = new AnnotationEventBookmarked("AnnotationEvent" + annotationEventcount++);
		
		event.setEventTime(Long.parseLong(object.get("eventTime").toString()));
		event.setActor(entityFactory.createPerson(object));
		event.setEdApp(entityFactory.createSoftwareApplication(object, 1));
		event.setObject(entityFactory.createFrame(object, 2));
		event.setGenerated(entityFactory.createBookmarkAnnotation(object));
		
		return event;
	}


	public Event createAssessmentEvent(JSONObject object)throws CaliperEventParseException{
		AssessmentEvent event = new AssessmentEvent(object.get("@context").toString() + "/" + count++);
		event.setEventTime(Long.parseLong(object.get("eventTime").toString()));
		
		event.setActor(entityFactory.createPerson(object));
		event.setObject(entityFactory.createAssessment(object));
		event.setGenerated(entityFactory.createAttempt(object));
		event.setEdApp(entityFactory.createSoftwareApplication(object,1));
		return event;
	}
	
	public Event createAssessmentItemEvent(JSONObject object) throws CaliperEventParseException{
		AssessmentItemEvent event = new AssessmentItemEvent(object.get("@context").toString() + "/" + count++);
		event.setEventTime(Long.parseLong(object.get("eventTime").toString()));
		
		Person person = null; 
		entityFactory.createPerson(object);
		AssessmentItem item = entityFactory.createAssessmentItem(object);
		SoftwareApplication softwareApplication = entityFactory.createSoftwareApplication(object,1);
		
		if(person != null)
			event.setActor(person);
		else
			System.out.println("NULL Person");
		
		event.setObject(item);
		event.setEdApp(softwareApplication);
		
		return event;
	}
	
	public Event createSessionLoginEvent(JSONObject object)throws CaliperEventParseException{
		
		SessionLoginEvent event = new SessionLoginEvent("SessionEvent" + sessionEventcount++);
		
		Object eventTimeObject = object.get("eventTime");
		
	
		event.setEventTime(Long.parseLong(eventTimeObject.toString()));
		
	
		
		
		Person person = entityFactory.createPerson(object);
		SoftwareApplication softwareApplication = entityFactory.createSoftwareApplication(object,0);
		Session session = entityFactory.createSession(object);
		
		event.setActor(person);
		event.setObject(softwareApplication);
		event.setGenerated(session);
		
		return event;
	}
	
	public Event createSessionLogoutEvent(JSONObject object)throws CaliperEventParseException{
		SessionLogoutEvent event = new SessionLogoutEvent("SessionEvent" +  sessionEventcount++);
		event.setEventTime(Long.parseLong(object.get("eventTime").toString()));
		
		event.setActor(entityFactory.createPerson(object));
		event.setObject(entityFactory.createSoftwareApplication(object,0));
		event.setGenerated(entityFactory.createSession(object));
		
		return event;
	}
	
	public Event createViewEvent(JSONObject object)throws CaliperEventParseException{
		ViewEvent event = new ViewEvent("ViewEvent"  + viewEventcount++);
		event.setEventTime(Long.parseLong(object.get("eventTime").toString()));
		
		event.setActor(entityFactory.createPerson(object));
		event.setObject(entityFactory.createFrame(object,2));
		return event;
	}
	
	public Event createReadingEvent(JSONObject object)throws CaliperEventParseException{
		ReadingEvent event = new ReadingEvent(object.get("@context").toString() +  "/" + count++);
		event.setEventTime(Long.parseLong(object.get("eventTime").toString()));
		
		event.setActor(entityFactory.createPerson(object));
		event.setObject(entityFactory.createReading(object));
		event.setEdApp(entityFactory.createSoftwareApplication(object,1));
		
		return event;
	}
	
	public Event createNavigationEvent(JSONObject object)throws CaliperEventParseException{
		NavigationEvent event = new NavigationEvent("NavigationEvent" + navigationEventcount++);
		event.setEventTime(Long.parseLong(object.get("eventTime").toString()));
		
		event.setActor(entityFactory.createPerson(object));
		event.setTarget(entityFactory.createFrame(object,0));
		event.setObject(entityFactory.createVolume(object));
		event.setNavigatedFrom(entityFactory.createFrame(object,1));
		event.setEdApp(entityFactory.createSoftwareApplication(object,1));
		return event;
	}
	
	public Event createOutcomeEvent(JSONObject object)throws CaliperEventParseException{
		OutcomeEvent event = new OutcomeEvent(object.get("@context").toString() +  "/" + count++);
		event.setEventTime(Long.parseLong(object.get("eventTime").toString()));
		
		event.setActor(entityFactory.createSoftwareApplication(object,2));
		event.setObject(entityFactory.createAttempt(object));
		event.setGenerated(entityFactory.createResult(object));
		return event;
	}
	
	public Event createAnnotationEventHighlighted(JSONObject object) throws CaliperEventParseException{
		AnnotationEventHighlighted event = new AnnotationEventHighlighted("AnnotationEvent" + annotationEventcount++);
		
		event.setEventTime(Long.parseLong(object.get("eventTime").toString()));
		event.setActor(entityFactory.createPerson(object));
		event.setEdApp(entityFactory.createSoftwareApplication(object, 1));
		event.setObject(entityFactory.createFrame(object, 2));
		event.setGenerated(entityFactory.createHighlightAnnotation(object));
		
		return event;
		
	}
}
