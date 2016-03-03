package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.event;

import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.*;
import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;


public class NavigationEvent extends Event{
	private Volume object;
	private Person actor;
	private Frame target;
	private Frame navigatedFrom;
	private SoftwareApplication edApp;
	
	public NavigationEvent(String id) {
		super(id, EventType.NavigationEvent.getContext(), EventType.NavigationEvent.getType(), EventType.NavigationEvent.getAction());
	}
	
	public String toString(){
		RDFStringBuilder rb = super.buildRDFString();
		rb.buildRDF("actor",actor.getId());
		rb.buildRDF("object",object.getId());
		rb.buildRDF("target", target.getId());
		rb.buildRDF("navigatedFrom", navigatedFrom.getId());
		rb.buildRDF("edApp", edApp.getId());
		return rb.toString();
	}
	
	public Frame getNavigatedFrom() {
		return navigatedFrom;
	}

	public void setNavigatedFrom(Frame navigatedFrom) {
		this.navigatedFrom = navigatedFrom;
		this.getEntityList().add(navigatedFrom);
		navigatedFrom.setTypeInEvent("navigatedFrom");
	}

	public SoftwareApplication getEdApp() {
		return edApp;
	}

	public void setEdApp(SoftwareApplication edApp) {
		this.edApp = edApp;
		this.getEntityList().add(edApp);
		edApp.setTypeInEvent("edApp");
	}

	public void setObject(Volume object){
		this.object = object;
		this.getEntityList().add(object);
		object.setTypeInEvent("object");
	}	

	public void setActor(Person actor){
		this.actor = actor;
		this.getEntityList().add(actor);
		actor.setTypeInEvent("actor");
	}

	public void setTarget(Frame target) {
		this.target = target;		
		this.getEntityList().add(target);
		target.setTypeInEvent("target");
	}

	public Volume getObject() {
		return object;
	}

	public Person getActor() {
		return actor;
	}

	public Frame getTarget() {
		return target;
	}

	

}
