package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;

public class Result extends Entity{

	private String								totalScore;

	public Result(String id){
		super(id, EntityType.Result.getType());
	}
	public Model toRDFModel(){
		Model model = super.toRDFModel();
		Property pTotalScore = model.createProperty("totalScore");
		Resource resource = model.getResource(this.getId());
		
		resource.addProperty(pTotalScore, totalScore);
		
		return model;
	}
	public String toString(){
		RDFStringBuilder rb = super.buildRDFString();
		rb.buildRDF("totalScore",totalScore);

		return rb.toString();
	}

	public String getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}
}
