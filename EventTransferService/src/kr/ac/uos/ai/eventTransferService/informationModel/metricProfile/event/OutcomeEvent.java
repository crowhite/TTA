package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.event;

import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.Attempt;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.Result;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.SoftwareApplication;
import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;



public class OutcomeEvent extends Event{
	private Attempt object;
	private SoftwareApplication actor;
	private Result generated;
	
	
	public OutcomeEvent(String id) {
		super(id, EventType.OutcomeEvent.getContext(), EventType.OutcomeEvent.getType(), EventType.OutcomeEvent.getAction());
	}
	
	public String toString(){
		RDFStringBuilder rb = super.buildRDFString();
		rb.buildRDF("actor",actor.getId());
		rb.buildRDF("object",object.getId());
		rb.buildRDF("generated",generated.getId());
		return rb.toString();
	}
	
	public void setObject(Attempt object){
		this.object = object;
		this.getEntityList().add(object);
		object.setTypeInEvent("object");
	}	

	public void setActor(SoftwareApplication actor){
		this.actor = actor;
		this.getEntityList().add(actor);
		actor.setTypeInEvent("actor");
	}
	
	public void setGenerated(Result generated){
		this.generated = generated;
		this.getEntityList().add(generated);
		generated.setTypeInEvent("generated");
		
	}

	public Attempt getObject() {
		return object;
	}

	public SoftwareApplication getActor() {
		return actor;
	}

	public Result getGenerated() {
		return generated;
	}
	
	

}
