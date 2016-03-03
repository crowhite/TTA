package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.event;

import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.BookmarkAnnotation;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.Frame;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.HighlightAnnotation;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.Person;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.SoftwareApplication;
import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;

public class AnnotationEventBookmarked extends Event{
	private Person actor;
	private Frame object;
	private SoftwareApplication edApp;
	private BookmarkAnnotation generated;
	
	public AnnotationEventBookmarked(String id) {
		super(id, EventType.AnnotationEventBookmarked.getContext(),
				EventType.AnnotationEventBookmarked.getType(), EventType.AnnotationEventBookmarked
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
	
	public BookmarkAnnotation getGenerated() {
		return generated;
	}

	public void setGenerated(BookmarkAnnotation generated) {
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
