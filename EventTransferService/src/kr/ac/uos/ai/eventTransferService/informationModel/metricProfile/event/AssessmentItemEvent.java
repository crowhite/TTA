package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.event;

import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.*;
import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;



public class AssessmentItemEvent extends Event{
	private Person actor;
	private AssessmentItem object;
	private SoftwareApplication edApp;
	
	
	public AssessmentItemEvent(String id) {
		super(id, EventType.AssessmentItemEvent.getContext(),
				EventType.AssessmentItemEvent.getType(), EventType.AssessmentItemEvent
						.getAction());
	}
	
	public String toString(){
		RDFStringBuilder rb = super.buildRDFString();
		rb.buildRDF("actor",actor.getId());
		rb.buildRDF("object",object.getId());
		rb.buildRDF("edApp",edApp.getId());
		return rb.toString();
	}
	
	
	public void setActor(Person actor){
		this.actor = actor;
		this.getEntityList().add(actor);
		
	}
	
	public void setObject(AssessmentItem object) {
		this.object = object;
		this.getEntityList().add(object);
	}

	public void setEdApp(SoftwareApplication edApp) {
		this.edApp = edApp;
		this.getEntityList().add(edApp);
	}

	public Person getActor() {
		return actor;
	}

	public AssessmentItem getObject() {
		return object;
	}

	public SoftwareApplication getEdApp() {
		return edApp;
	}

	
	
}
