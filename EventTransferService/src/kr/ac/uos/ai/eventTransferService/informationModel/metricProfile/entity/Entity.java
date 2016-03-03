package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;
import kr.ac.uos.ai.eventTransferService.transferCore.Configuration;

public abstract class Entity {

	private String												id;
	private String												type;
//	private long												lastModifiedTime;
	private String 												context;
	private String												typeInEvent;

	public Entity(String id, String type){
		this.id = Configuration.TRANSFER_CONTEXT + id;
		this.type = type;
		
	}
	
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Model toRDFModel(){
		Model model = ModelFactory.createDefaultModel();
		Resource resource = model.createResource(id);
		Property pType = model.createProperty("type");

		resource.addProperty(pType, type);

		
		return model;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public RDFStringBuilder buildRDFString(){
		RDFStringBuilder rb = new RDFStringBuilder(this.id);
		rb.buildRDF("context",this.context );
		rb.buildRDF("type", this.type);

		
		return rb;
	}

	public String getTypeInEvent() {
		return typeInEvent;
	}

	public void setTypeInEvent(String typeInEvent) {
		this.typeInEvent = typeInEvent;
	}
	
	
//	public long getLastModifiedTime() {
//		return lastModifiedTime;
//	}
//
//	public void setLastModifiedTime(long lastModifiedTime) {
//		this.lastModifiedTime = lastModifiedTime;
//	}

	
	
}
