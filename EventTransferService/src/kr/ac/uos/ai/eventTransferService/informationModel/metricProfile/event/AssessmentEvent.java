package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.event;

import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.Assessment;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.Attempt;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.Person;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.SoftwareApplication;
import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;


public class AssessmentEvent extends Event {
		private Person actor;
		private Assessment object;
		private Attempt generated;
		private SoftwareApplication edApp;
	
		public AssessmentEvent(String id) {
			super(id, EventType.AssessmentEvent.getContext(),
					EventType.AssessmentEvent.getType(), EventType.AssessmentEvent
							.getAction());
		}
		
		public String toString(){
			RDFStringBuilder rb = super.buildRDFString();
			rb.buildRDF("actor",actor.getId());
			rb.buildRDF("object",object.getId());
			rb.buildRDF("generated",generated.getId());
			rb.buildRDF("edApp",edApp.getId());
			return rb.toString();
		}
		
		public void setActor(Person actor){
			this.actor = actor;
			this.getEntityList().add(actor);
			actor.setTypeInEvent("actor");
		}
	
		public void setObject(Assessment object) {
			this.object = object;
			this.getEntityList().add(object);
			object.setTypeInEvent("object");
		}
	
		public void setGenerated(Attempt generated){
			this.generated = generated;
			this.getEntityList().add(generated);
			generated.setTypeInEvent("generated");
		}
		
		public void setEdApp(SoftwareApplication edApp){
			this.edApp = edApp;
			this.getEntityList().add(edApp);
			edApp.setTypeInEvent("edApp");
		}

		public Person getActor() {
			return actor;
		}

		public Assessment getObject() {
			return object;
		}

		public Attempt getGenerated() {
			return generated;
		}

		public SoftwareApplication getEdApp() {
			return edApp;
		}
		
		
	
	
}
