package kr.ac.uos.ai.eventTransferService.exchangemodel;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.Entity;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.event.Event;
import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;
import kr.ac.uos.ai.eventTransferService.transferCore.Configuration;

import java.util.LinkedList;

public class RDFMessage {
	private LinkedList<Event> eventList;
	private LinkedList<Entity> entityList;
	private String sender;
	private String receiver;
	private long transferInitiateTime;
	private long contentSize;
	private int eventNumber;
	private String transferId;
	
	public RDFMessage(){
		eventList = new LinkedList<Event>();
		entityList = new LinkedList<Entity>();
		transferId = Configuration.TRANSFER_CONTEXT + "transferContext";
	}
	
	public Model toRDFModel(){
		Model model = ModelFactory.createDefaultModel();
		
		
		Resource resource = model.createResource(transferId);
		Property pSender = model.createProperty("sender");
		Property pReceiver = model.createProperty("receiver");
		Property pTransferInitiateTime = model.createProperty("transferInitiateTime");
		Property pContentSize = model.createProperty("contentSize");
		Property pEventNumber = model.createProperty("eventNumber");
		
		resource.addProperty(pSender, sender);
		resource.addProperty(pReceiver, receiver);
		resource.addProperty(pTransferInitiateTime, String.valueOf(transferInitiateTime));
		resource.addProperty(pContentSize, String.valueOf(contentSize));
		resource.addProperty(pEventNumber, String.valueOf(eventNumber));
		
		return model;
	}
	
	public String toRDFString(){
		RDFStringBuilder rb = new RDFStringBuilder(transferId);
			
		rb.buildRDF("sender", sender);
		rb.buildRDF("receiver", receiver);
		rb.buildRDF("transferInitiateTime", transferInitiateTime);
		rb.buildRDF("contentSize", contentSize);
		rb.buildRDF("eventNumber", eventNumber);
		
		
		for(int i = 0; i < eventList.size();i++){
			eventList.get(i);
		}
		
		return rb.toString();
	}
	
	public LinkedList<Event> getEventList() {
		return eventList;
	}
	
	public LinkedList<Entity> getEntityList() {
		return entityList;
	}

	public void addEvent(Event event){
		eventList.add(event);
	}
	
	public void addEntity(Entity entity){
		entityList.add(entity);
	}
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReciever() {
		return receiver;
	}
	public void setReciever(String reciever) {
		this.receiver = reciever;
	}
	public long getTransferInitiateTime() {
		return transferInitiateTime;
	}
	public void setTransferInitiateTime(long transferInitiateTime) {
		this.transferInitiateTime = transferInitiateTime;
	}
	public long getContentSize() {
		return contentSize;
	}
	public void setContentSize(long contentSize) {
		this.contentSize = contentSize;
	}
	public int getEventNumber() {
		return eventNumber;
	}
	public void setEventNumber(int eventNumber) {
		this.eventNumber = eventNumber;
	}
	
	
	
}
