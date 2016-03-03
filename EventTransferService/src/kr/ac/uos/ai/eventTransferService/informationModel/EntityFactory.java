package kr.ac.uos.ai.eventTransferService.informationModel;


import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.*;
import kr.ac.uos.ai.eventTransferService.informationModel.translateContext.JSONContext;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class EntityFactory {
	
	private static EntityFactory factory;
	
	private JSONParser parser;
	private EntityFactory(){
		parser = new JSONParser();
	}
	
	public static synchronized EntityFactory getInstance(){
		if(factory == null){
			factory = new EntityFactory();
		}
		
		return factory;
	}
	
	public Volume createVolume(JSONObject inputObject) throws CaliperEventParseException{
		JSONObject object = null;
		try {
			object = (JSONObject)parser.parse(inputObject.get("object").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object idObject = object.get(JSONContext.ID);
		Object nameObject = object.get(JSONContext.NAME);
		Object keywordsObject = object.get("keywords");
		Object contextObject = object.get(JSONContext.CONTEXT);
		if(idObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "AssessmentEntity must has ID property");
		
		if(nameObject == null){
			//throw new CaliperEventParseException(inputObject.toString(), "AssessmentEntity must has Name property");
		}
		Volume volume = new Volume(idObject.toString());
		volume.setName("null");
		volume.setKeywords(keywordsObject.toString());
		volume.setContext(contextObject.toString());
		return volume;
	}

	public Assessment createAssessment(JSONObject inputObject) throws CaliperEventParseException{
		JSONObject object = null;
		try {
			object = (JSONObject)parser.parse(inputObject.get("object").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object idObject = object.get(JSONContext.ID);
		Object nameObject = object.get(JSONContext.NAME);
		Object contextObject = object.get(JSONContext.CONTEXT);
		
		if(idObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "AssessmentEntity must has ID property");
		if(nameObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "AssessmentEntity must has Name property");
		
		Assessment assessment = new Assessment(idObject.toString());
		assessment.setName(nameObject.toString());
		assessment.setContext(contextObject.toString());
		return assessment;
	}

	public AssessmentItem createAssessmentItem(JSONObject inputObject) throws CaliperEventParseException{
		JSONObject object = null;
		try {
			object = (JSONObject)parser.parse(inputObject.get("object").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object idObject = object.get(JSONContext.ID);
		Object nameObject = object.get(JSONContext.NAME);
		Object partOfObject = object.get("partOf");
		Object maxAttemptsObject = object.get("maxAttempts");
		Object maxSubmitObject = object.get("maxSubmits");
		Object maxScoreObject = object.get("maxScore");
		Object contextObject = object.get(JSONContext.CONTEXT);
		
		if(idObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "AssessmentItemEntity must has ID property");
		if(nameObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "AssessmentItemEntity must has Name property");
		if(partOfObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "AssessmentItemEntity must has partOf property");
		if(maxAttemptsObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "AssessmentItemEntity must has maxAttemptsObject property");
		if(maxSubmitObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "AssessmentItemEntity must has maxSubmitObject property");
		if(maxScoreObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "AssessmentItemEntity must has maxScoreObject property");
		
		
		AssessmentItem assessmentItem = new AssessmentItem(idObject.toString());
		assessmentItem.setName(nameObject.toString());
		assessmentItem.setPartOf(partOfObject.toString());
		assessmentItem.setMaxAttempts(Integer.parseInt(maxAttemptsObject.toString()));
		assessmentItem.setMaxSubmits(Integer.parseInt(maxSubmitObject.toString()));
		assessmentItem.setMaxScore(Float.parseFloat(maxScoreObject.toString()));
		assessmentItem.setContext(contextObject.toString());
		return assessmentItem;
	}

	public CourseSection createCourseSection(JSONObject inputObject)throws CaliperEventParseException {
		JSONObject object = null;
		try {
			object = (JSONObject)parser.parse(inputObject.get("courseSection").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object idObject = object.get(JSONContext.ID);
		Object nameObject = object.get(JSONContext.NAME);
		Object contextObject = object.get(JSONContext.CONTEXT);
		
		if(idObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "CourseSectionEntity must has ID property");
		if(nameObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "CourseSectionEntity must has Name property");
		
		CourseSection courseSection = new CourseSection(idObject.toString());
		courseSection.setName(nameObject.toString());
		courseSection.setContext(contextObject.toString());
		return courseSection;
	}

	public Group createGroup(JSONObject inputObject) throws CaliperEventParseException {
		JSONObject object = null;
		try {
			object = (JSONObject)parser.parse(inputObject.get("group").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object idObject = object.get(JSONContext.ID);
		Object nameObject = object.get(JSONContext.NAME);
		Object contextObject = object.get(JSONContext.CONTEXT);
		
		if(idObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "GroupEntity must has ID property");
		if(nameObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "GroupEntity must has Name property");
		
		
		Group group = new Group(object.get(JSONContext.ID).toString());
		group.setName(object.get(JSONContext.NAME).toString());
		group.setOrganization(createOrganization(object));
		group.setCourseSection(createCourseSection(object));
		group.setContext(contextObject.toString());
		return group;
	}

	public Organization createOrganization(JSONObject inputObject) throws CaliperEventParseException{
		JSONObject object = null;
		try {
			object = (JSONObject)parser.parse(inputObject.get("organization").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object idObject = object.get(JSONContext.ID);
		Object nameObject = object.get(JSONContext.NAME);
		Object contextObject = object.get(JSONContext.CONTEXT);
		
		if(idObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "OrganizationEntity must has ID property");
		if(nameObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "OrganizationEntity must has Name property");
		
		
		Organization organization = new Organization(idObject.toString());
		organization.setName(nameObject.toString());
		organization.setContext(contextObject.toString());
		return organization;
	}

	public Person createPerson(JSONObject inputObject) throws CaliperEventParseException {
		JSONObject object = null;
		try {
			object = (JSONObject)parser.parse(inputObject.get("actor").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object idObject = object.get(JSONContext.ID);
		Object nameObject = object.get(JSONContext.NAME);
		Object dateCreatedObject = object.get(JSONContext.DATE_CREATED);
		Object dateModifiedObject = object.get(JSONContext.DATE_MODIFIED);
		Object contextObject = object.get(JSONContext.CONTEXT);
		
		if(idObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "PersonEntity must has ID property");
		if(nameObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "PersonEntity must has Name property");
		if(dateCreatedObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "PersonEntity must has dateCreated property");
		if(dateModifiedObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "PersonEntity must has dateModified property");
		
		
		
		Person person = new Person(idObject.toString());
		person.setName(nameObject.toString());
		person.setDateCreated(Long.parseLong(dateCreatedObject.toString()));
		person.setDateModified(Long.parseLong(dateModifiedObject.toString()));
		person.setContext(contextObject.toString());
		return person;
	}

	public Reading createReading(JSONObject inputObject) throws CaliperEventParseException  {
		JSONObject object = null;
		
		try {
			object = (JSONObject)parser.parse(inputObject.get("object").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object idObject = object.get(JSONContext.ID);
		Object nameObject = object.get(JSONContext.NAME);
		Object dateCreatedObject = object.get(JSONContext.DATE_CREATED);
		Object dateModifiedObject = object.get(JSONContext.DATE_MODIFIED);
		Object contextObject = object.get(JSONContext.CONTEXT);
		
		if(idObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "ReadingEntity must has ID property");
		if(nameObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "ReadingEntity must has Name property");
		if(dateCreatedObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "ReadingEntity must has dateCreated property");
		if(dateModifiedObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "ReadingEntity must has dateModified property");
		
		Reading reading = new Reading(idObject.toString());
		reading.setDateCreated(Long.parseLong(dateCreatedObject.toString()));
		reading.setDateModified(Long.parseLong(dateModifiedObject.toString()));
		reading.setName(nameObject.toString());
		reading.setContext(contextObject.toString());
		return reading;
	}
	public BookmarkAnnotation createBookmarkAnnotation(JSONObject inputObject)throws CaliperEventParseException{
		JSONObject object = null;

		try {
			object = (JSONObject)parser.parse(inputObject.get("generated").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object dateCreatedObject = object.get(JSONContext.DATE_CREATED);
		Object dateModifiedObject = object.get(JSONContext.DATE_MODIFIED);
		Object annotatedObject = object.get("annotated");
		Object selectionTextObject = object.get("bookmarkNotes");
		Object contextObject = object.get(JSONContext.CONTEXT);
		
		if(dateCreatedObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "BookmarkAnnotation must has dateCreated property");
		if(dateModifiedObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "BookmarkAnnotation must has dateModified property");
		if(annotatedObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "BookmarkAnnotation must has annotated property");
		if(selectionTextObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "BookmarkAnnotation must has bookmarkNotes property");
		if(contextObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "BookmarkAnnotation must has context property");
		
		BookmarkAnnotation bookmarkAnnotation = new BookmarkAnnotation("generated02");
		bookmarkAnnotation.setAnnotated(annotatedObject.toString());
		bookmarkAnnotation.setContext(contextObject.toString());
		bookmarkAnnotation.setDateCreated(Long.parseLong(dateCreatedObject.toString()));
		bookmarkAnnotation.setDateModified(Long.parseLong(dateModifiedObject.toString()));
		bookmarkAnnotation.setBookmarkNotes(selectionTextObject.toString());
		
		return bookmarkAnnotation;
	}
	public Frame createFrame(JSONObject inputObject,int type)throws CaliperEventParseException {
		JSONObject object = null;
		try {
			if(type == 0)
				object = (JSONObject)parser.parse(inputObject.get("target").toString());
			else if(type == 1)
				object = (JSONObject)parser.parse(inputObject.get("navigatedFrom").toString());
			else if(type == 2){
				object = (JSONObject)parser.parse(inputObject.get("object").toString());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object idObject  = object.get(JSONContext.ID);
		Object nameObject = object.get(JSONContext.NAME);
		Object indexObject = object.get(JSONContext.INDEX);
		Object contextObject = object.get(JSONContext.CONTEXT);
		
		if(idObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "ReadingEntity must has ID property");
		if(nameObject == null){
			//throw new CaliperEventParseException(inputObject.toString(), "ReadingEntity must has Name property");
		}
		if(indexObject == null){
			//throw new CaliperEventParseException(inputObject.toString(), "ReadingEntity must has dateCreated property");
		}
		
		Frame frame = new Frame(idObject.toString());
		frame.setName("null");
		//frame.setName(nameObject.toString());
		frame.setIndex(1);
		//frame.setIndex(Integer.parseInt(indexObject.toString()));
		frame.setContext(contextObject.toString());
		return frame;
	}

	public Session createSession(JSONObject inputObject) throws CaliperEventParseException{
		JSONObject object = null;
		
		try {
			object = (JSONObject)parser.parse(inputObject.get("generated").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object idObject = object.get(JSONContext.ID);
		Object dateCreatedObject = object.get(JSONContext.DATE_CREATED);
		Object dateModifiedObject = object.get(JSONContext.DATE_MODIFIED);
		Object contextObject = object.get(JSONContext.CONTEXT);
		
		if(idObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "SessionEntity must has ID property");
		if(dateCreatedObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "SessionEntity must has dateCreated property");
		if(dateModifiedObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "SessionEntity must has dateModified property");
		
		
		Session session = new Session(idObject.toString());
		session.setDateCreated(Long.parseLong(dateCreatedObject.toString()));
		session.setDateModified(Long.parseLong(dateModifiedObject.toString()));
		session.setContext(contextObject.toString());
		return session;
	}
	
	public HighlightAnnotation createHighlightAnnotation(JSONObject inputObject)throws CaliperEventParseException{
		JSONObject object = null;
		
		try {
			object = (JSONObject)parser.parse(inputObject.get("generated").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Object dateCreatedObject = object.get(JSONContext.DATE_CREATED);
		Object dateModifiedObject = object.get(JSONContext.DATE_MODIFIED);
		Object annotatedObject = object.get("annotated");
		Object selectionTextObject = object.get("selectionText");
		Object selectionObject = object.get("selection");
		Object contextObject = object.get(JSONContext.CONTEXT);
		
		if(dateCreatedObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "HighlightAnnotation must has dateCreated property");
		if(dateModifiedObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "HighlightAnnotation must has dateModified property");
		if(annotatedObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "HighlightAnnotation must has annotated property");
		if(selectionTextObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "HighlightAnnotation must has selectionText property");
		if(selectionObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "HighlightAnnotation must has selection property");
		if(contextObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "HighlightAnnotation must has context property");
		
		HighlightAnnotation highlightAnnotation = new HighlightAnnotation("generated01");
		highlightAnnotation.setAnnotated(annotatedObject.toString());
		highlightAnnotation.setContext(contextObject.toString());
		highlightAnnotation.setDateCreated(Long.parseLong(dateCreatedObject.toString()));
		highlightAnnotation.setDateModified(Long.parseLong(dateModifiedObject.toString()));
		highlightAnnotation.setSelection(selectionObject.toString());
		highlightAnnotation.setSelectionText(selectionTextObject.toString());
		return highlightAnnotation;
	}
	
	public SoftwareApplication createSoftwareApplication(JSONObject inputObject, int type) throws CaliperEventParseException {
		JSONObject object = null;

		try {
			if(type == 0)
				object = (JSONObject)parser.parse(inputObject.get("object").toString());
			else if(type == 1){
				object = (JSONObject)parser.parse(inputObject.get("edApp").toString());
			}else if(type == 2){
				object = (JSONObject)parser.parse(inputObject.get("actor").toString());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object idObject = object.get(JSONContext.ID);
		Object nameObject = object.get(JSONContext.NAME);
		Object dateCreatedObject = object.get(JSONContext.DATE_CREATED);
		Object dateModifiedObject = object.get(JSONContext.DATE_MODIFIED);
		Object contextObject = object.get(JSONContext.CONTEXT);
		
		if(idObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "SoftwareApplicationEntity must has ID property");
		if(nameObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "SoftwareApplicationEntity must has Name property");
		if(dateCreatedObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "SoftwareApplicationEntity must has dateCreated property");
		if(dateModifiedObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "SoftwareApplicationEntity must has dateModified property");
		
		
		SoftwareApplication softwareApplication = new SoftwareApplication(idObject.toString());
		softwareApplication.setDateCreated(Long.parseLong(dateCreatedObject.toString()));
		softwareApplication.setDateModified(Long.parseLong(dateModifiedObject.toString()));
		softwareApplication.setName(nameObject.toString());
		softwareApplication.setContext(contextObject.toString());
		return softwareApplication;
	}
	/*
	public DigitalResource createDigitalResource(JSONObject inputObject) {
		JSONObject object = null;
		
		DigitalResource digitalResource = new DigitalResource(id);
		digitalResource.setName(name);

		return digitalResource;
	}

	public EdupubChapter createEdupubChapter(JSONObject inputObject) {
		JSONObject object = null;
		
		EdupubChapter edupubChapter = new EdupubChapter(id);
		edupubChapter.setName(name);

		return edupubChapter;
	}*/

	public Attempt createAttempt(JSONObject inputObject)throws CaliperEventParseException {
		JSONObject object = null;
		try {
			object = (JSONObject)parser.parse(inputObject.get("object").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object idObject = object.get(JSONContext.ID);
		Object actorObject = object.get("actor");
		Object assignableObject = object.get("assignable");
		Object countObject = object.get("count");
		Object contextObject = object.get(JSONContext.CONTEXT);
		
		if(idObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "AttemptEntity must has ID property");
		if(actorObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "AttemptEntity must has actor property");
		if(assignableObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "AttemptEntity must has assignable property");
		if(countObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "AttemptEntity must has count property");
		

		
		Attempt attempt = new Attempt(idObject.toString());
		attempt.setActor(actorObject.toString());
		attempt.setAssignable(assignableObject.toString());
		attempt.setCount(countObject.toString());
		attempt.setContext(contextObject.toString());
		return attempt;
	}

	public Result createResult(JSONObject inputObject) throws CaliperEventParseException{
		JSONObject object = null;
		try {
			object = (JSONObject)parser.parse(inputObject.get("generated").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object idObject = object.get(JSONContext.ID);
		Object totalScoreObject = object.get("totalScore");
		Object contextObject = object.get(JSONContext.CONTEXT);
		
		if(idObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "ResultEntity must has ID property");
		if(totalScoreObject == null)
			throw new CaliperEventParseException(inputObject.toString(), "ResultEntity must has totalScore property");
		
		Result result = new Result(idObject.toString());
		result.setTotalScore(totalScoreObject.toString());
		result.setContext(contextObject.toString());
		return result;

	}
	
}
