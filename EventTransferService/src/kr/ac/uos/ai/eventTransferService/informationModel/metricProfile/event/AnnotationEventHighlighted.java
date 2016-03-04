package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.event;

import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.Frame;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.HighlightAnnotation;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.Person;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.SoftwareApplication;
import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;

public class AnnotationEventHighlighted extends Event {
	private Person actor;
	private Frame object;
	private SoftwareApplication edApp;
	private HighlightAnnotation generated;
	
	public AnnotationEventHighlighted(String id) {
		super(id, EventType.AnnotationEventHighlighted.getContext(),
				EventType.AnnotationEventHighlighted.getType(), EventType.AnnotationEventHighlighted
						.getAction());
	}
	
	public String toString(){
		RDFStringBuilder rb = super.buildRDFString();
		rb.buildRDF("actor",actor.getId());
		rb.buildRDF("object",object.getId());
		rb.buildRDF("edApp",edApp.getId());
		rb.buildRDF("generated", generated.getId());
		return rb.toString();
	}
	
	public HighlightAnnotation getGenerated() {
		return generated;
	}

	public void setGenerated(HighlightAnnotation generated) {
		this.generated = generated;
		this.getEntityList().add(generated);
		generated.setTypeInEvent("generated");
	}

	public Person getActor() {
		return actor;
	}

	public void setActor(Person actor) {
		this.actor = actor;
		this.getEntityList().add(actor);
		actor.setTypeInEvent("actor");
	}

	public Frame getObject() {
		return object;
	}

	public void setObject(Frame object) {
		this.object = object;
		this.getEntityList().add(object);
		object.setTypeInEvent("object");
	}

	public SoftwareApplication getEdApp() {
		return edApp;
	}

	public void setEdApp(SoftwareApplication edApp) {
		this.edApp = edApp;
		this.getEntityList().add(edApp);
		edApp.setTypeInEvent("edApp");
	}

	
}
