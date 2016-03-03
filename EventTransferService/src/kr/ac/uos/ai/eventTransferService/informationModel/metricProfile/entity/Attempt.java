package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;

public class Attempt extends Entity{

	private String							assignable;
	private String							actor;
	private String							count;


	public Attempt(String id){
		super(id, EntityType.Attempt.getType());
	}
	public Model toRDFModel(){
		Model model = super.toRDFModel();
		Property pAssignable = model.createProperty("assignable");
		Property pActor = model.createProperty("actor");
		Property pCount = model.createProperty("count");
		Resource resource = model.getResource(this.getId());
		
		resource.addProperty(pAssignable, assignable);
		resource.addProperty(pActor, actor);
		resource.addProperty(pCount, count);
		return model;

	}
	public String toString(){
		RDFStringBuilder rb = super.buildRDFString();
		rb.buildRDF("assignable",assignable);
		rb.buildRDF("actor", actor);
		rb.buildRDF("count",count);
		
		return rb.toString();
	}
	
	public String getAssignable() {
		return assignable;
	}

	public void setAssignable(String assignable) {
		this.assignable = assignable;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
		
}
