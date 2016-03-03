package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;

public class Frame extends Entity {
	
	private String							name;
	private int								index;
		
	public Frame(String id) {
		super(id, EntityType.Frame.getType());
	}
	
	public Model toRDFModel(){
		Model model = super.toRDFModel();
		Property pName = model.createProperty("name");
		Property pIndex = model.createProperty("index");
		Resource resource = model.getResource(this.getId());
		
		resource.addProperty(pName, name);
		resource.addProperty(pIndex, String.valueOf(index));
		return model;
	}
	
	public String toString(){
		RDFStringBuilder rb = super.buildRDFString();
		rb.buildRDF("name",name);
		rb.buildRDF("index",index);
		return rb.toString();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
