package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;

public class Person extends Entity{

	private String							name;
	private long 							dateCreated;
	private long							dateModified;
	
	
	public Person(String id) {
		super(id, EntityType.Person.getType());
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
		rb.buildRDF("dateCreated", dateCreated);
		rb.buildRDF("dateModified", dateModified);
		return rb.toString();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public long getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}
	public long getDateModified() {
		return dateModified;
	}
	public void setDateModified(long dateModified) {
		this.dateModified = dateModified;
	}

	
}
