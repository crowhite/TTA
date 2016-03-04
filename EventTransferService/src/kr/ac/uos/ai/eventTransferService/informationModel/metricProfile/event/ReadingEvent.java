package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.event;

import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.Person;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.Reading;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.SoftwareApplication;
import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;



public class ReadingEvent extends Event{
	private Reading object;
	private Person actor;
	private SoftwareApplication edApp;
	
	
	public ReadingEvent(String id) {
		super(id, EventType.ReadingEvent.getContext(), EventType.ReadingEvent.getType(), EventType.ReadingEvent.getAction());
	}
	
	public String toString(){
		RDFStringBuilder rb = super.buildRDFString();
		rb.buildRDF("actor",actor.getId());
		rb.buildRDF("object",object.getId());
		rb.buildRDF("edApp",edApp.getId());
		return rb.toString();
	}
	
	public void setObject(Reading object){
		this.object = object;
		this.getEntityList().add(object);
		object.setTypeInEvent("object");
	}	

	public void setActor(Person actor){
		this.actor = actor;
		this.getEntityList().add(actor);
		actor.setTypeInEvent("actor");
	}

	public void setEdApp(SoftwareApplication edApp) {
		this.edApp = edApp;
		this.getEntityList().add(edApp);	
		edApp.setTypeInEvent("edApp");
	}

	public Reading getObject() {
		return object;
	}

	public Person getActor() {
		return actor;
	}

	public SoftwareApplication getEdApp() {
		return edApp;
	}
}
