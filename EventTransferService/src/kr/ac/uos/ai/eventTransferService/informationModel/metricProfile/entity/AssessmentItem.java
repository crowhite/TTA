package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;

public class AssessmentItem extends Entity {

	private String							name;
	private String							partOf;
	private int								maxAttempts;
	private int								maxSubmits;
	private float							maxScore;
	
	public AssessmentItem(String id) {
		super(id, EntityType.AssessmentItem.getType());
	}
	public Model toRDFModel(){
		Model model = super.toRDFModel();
		Property pName = model.createProperty("name");
		Property pPartOf = model.createProperty("partOf");
		Property pMaxAttempts = model.createProperty("maxAttempts");
		Property pMaxSubmits = model.createProperty("maxSubmits");
		Property pMaxScore = model.createProperty("maxScore");
		Resource resource = model.getResource(this.getId());
		
		resource.addProperty(pName, name);
		resource.addProperty(pPartOf, partOf);
		resource.addProperty(pMaxAttempts, String.valueOf(maxAttempts));
		resource.addProperty(pMaxSubmits, String.valueOf(maxSubmits));
		resource.addProperty(pMaxScore, String.valueOf(maxScore));
		
		return model;
	}
	public String toString(){
		RDFStringBuilder rb = super.buildRDFString();
		rb.buildRDF("name",name);
		rb.buildRDF("partOf", partOf);
		rb.buildRDF("maxAttempts",maxAttempts);
		rb.buildRDF("mxSubmit",maxSubmits);
		rb.buildRDF("maxScore",maxScore);
		
		return rb.toString();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPartOf() {
		return partOf;
	}

	public void setPartOf(String partOf) {
		this.partOf = partOf;
	}

	public int getMaxAttempts() {
		return maxAttempts;
	}

	public void setMaxAttempts(int maxAttempts) {
		this.maxAttempts = maxAttempts;
	}

	public int getMaxSubmits() {
		return maxSubmits;
	}

	public void setMaxSubmits(int maxSubmits) {
		this.maxSubmits = maxSubmits;
	}

	public float getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(float maxScore) {
		this.maxScore = maxScore;
	}
	
}
