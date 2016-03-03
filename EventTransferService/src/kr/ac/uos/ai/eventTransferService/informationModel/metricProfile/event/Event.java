package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.event;

import java.util.ArrayList;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.Entity;
import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;
import kr.ac.uos.ai.eventTransferService.transferCore.Configuration;



public abstract class Event {
	private String								id;
	private String								context;
	private String								type;
	private String								action;
	private long								eventTime;
	private ArrayList<Entity>					entityList;
	
	public Event(String id, String context, String type, String action){
		this.id = Configuration.TRANSFER_CONTEXT + id;
		this.context = context;
		this.type = type;
		this.action = action;
		entityList = new ArrayList<Entity>();

	}
	public Model toRDFModel(){
		Model model = ModelFactory.createDefaultModel();
		Resource resource = model.createResource(id);
		Property pContext = model.createProperty("context");
		Property pType = model.createProperty("type");
		Property pAction = model.createProperty("action");
		Property pEventTime = model.createProperty("eventTime");
		
		resource.addProperty(pType, type);
		resource.addProperty(pContext, context);
		resource.addProperty(pAction,action);
		resource.addProperty(pEventTime, String.valueOf(eventTime));
		
		for(int i = 0; i < entityList.size();i++){
			Property entity = model.createProperty(entityList.get(i).getTypeInEvent());
			resource.addProperty(entity, entityList.get(i).getId());
		}
		
		return model;
		
		
	}
	
	
	
	public RDFStringBuilder buildRDFString(){
		RDFStringBuilder rb = new RDFStringBuilder(id);
		rb.buildRDF("context", context);
		rb.buildRDF("type", type);
		rb.buildRDF("action", action);
		if(eventTime != 0)
			rb.buildRDF("eventTime", eventTime);		
		return rb;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public long getEventTime() {
		return eventTime;
	}

	public void setEventTime(long eventTime) {
		this.eventTime = eventTime;
	}

	public ArrayList<Entity> getEntityList() {
		return entityList;
	}


	
	
}
