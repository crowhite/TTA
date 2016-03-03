package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;

//import java.util.ArrayList;

public class Assessment extends Entity{

	private String							name;
//	private int								maxAttempts;
//	private int								maxSubmits;
//	private float							maxScore;
//	private ArrayList<AssessmentItem>		assessmentItems;
	
	public Assessment(String id) {
		super(id, EntityType.Assessment.getType());
//		assessmentItems = new ArrayList<AssessmentItem>();
	}

	public Model toRDFModel(){
		Model model = super.toRDFModel();
		Property pName = model.createProperty("name");
		Resource resource = model.getResource(this.getId());
		
		resource.addProperty(pName, name);
		return model;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String toString(){
		RDFStringBuilder rb = super.buildRDFString();
		rb.buildRDF("name",name);
		
		return rb.toString();
	}
//	public int getMaxAttempts() {
//		return maxAttempts;
//	}
//
//	public void setMaxAttempts(int maxAttempts) {
//		this.maxAttempts = maxAttempts;
//	}
//
//	public int getMaxSubmits() {
//		return maxSubmits;
//	}
//
//	public void setMaxSubmits(int maxSubmits) {
//		this.maxSubmits = maxSubmits;
//	}
//
//	public float getMaxScore() {
//		return maxScore;
//	}
//
//	public void setMaxScore(float maxScore) {
//		this.maxScore = maxScore;
//	}
//
//	public ArrayList<AssessmentItem> getAssessmentItems() {
//		return assessmentItems;
//	}
//
//	public void setAssessmentItems(ArrayList<AssessmentItem> assessmentItems) {
//		this.assessmentItems = assessmentItems;
//	}

}
