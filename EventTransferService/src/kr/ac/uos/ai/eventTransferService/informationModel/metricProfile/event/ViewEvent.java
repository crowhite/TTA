package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.event;

import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.*;
import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;


public class ViewEvent extends Event{
	private Person actor;
	private Frame object;

	
	
	public ViewEvent(String id) {
		super(id, EventType.ViewEvent.getContext(),
				EventType.ViewEvent.getType(), EventType.ViewEvent
						.getAction());
	}
	
	public String toString(){
		RDFStringBuilder rb = super.buildRDFString();
		rb.buildRDF("actor",actor.getId());
		rb.buildRDF("object", object.getId());
		return rb.toString();
	}
	
	public void setActor(Person actor){
		this.actor = actor;
		this.getEntityList().add(actor);
		actor.setTypeInEvent("actor");
	}
	
	public void setObject(Frame object){
		this.object = object;
		this.getEntityList().add(object);
		object.setTypeInEvent("object");
	}

	public Person getActor() {
		return actor;
	}

	public Frame getObject() {
		return object;
	}
	
	

}
