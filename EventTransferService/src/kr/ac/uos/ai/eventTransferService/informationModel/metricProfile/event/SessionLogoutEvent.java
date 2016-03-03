package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.event;

import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.*;
import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;


public class SessionLogoutEvent extends Event{
	private SoftwareApplication object;
	private Session generated;
	private Person actor;
	
	public SessionLogoutEvent(String id) {
		super(id, EventType.SessionLogoutEvent.getContext(),
				EventType.SessionLogoutEvent.getType(), EventType.SessionLogoutEvent
						.getAction());
	}
	
	public String toString(){
		RDFStringBuilder rb = super.buildRDFString();
		rb.buildRDF("actor",actor.getId());
		rb.buildRDF("object",object.getId());
		rb.buildRDF("generated",generated.getId());
		return rb.toString();
	}
	
	public void setObject(SoftwareApplication object){
		this.object = object;
		this.getEntityList().add(object);
		object.setTypeInEvent("object");
	}
	
	public void setGenerated(Session generated){
		this.generated = generated;
		this.getEntityList().add(generated);
		generated.setTypeInEvent("generated");
	}
	
	public void setActor(Person actor){
		this.actor = actor;
		this.getEntityList().add(actor);
		actor.setTypeInEvent("actor");
	}

	public SoftwareApplication getObject() {
		return object;
	}

	public Session getGenerated() {
		return generated;
	}

	public Person getActor() {
		return actor;
	}
	
	
}
