package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;

public class DigitalResource extends Entity{
	
	private String							name;
	
	public DigitalResource(String id) {
		super(id, EntityType.DigitalResource.getType());
	}
	
	public Model toRDFModel(){
		Model model = super.toRDFModel();
		Property pName = model.createProperty("name");
		Resource resource = model.getResource(this.getId());
		
		resource.addProperty(pName, name);
		return model;
	}
	
	public String toString(){
		RDFStringBuilder rb = super.buildRDFString();
		rb.buildRDF("name",name);
		
		return rb.toString();
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
