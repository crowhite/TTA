package model;

import com.hp.hpl.jena.rdf.model.Model;

public class TransferContext {
	private String transferID, sender, reciever, transferInitiateTime;
	private int contentSize, eventNumber;
	private Model entityList;
	
	public String getTransferID() {
		return transferID;
	}

	public void setTransferID(String transferID) {
		this.transferID = transferID;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
	
	public void setTransferInitiateTime(String transferInitiateTime) {
		this.transferInitiateTime = transferInitiateTime;
	}
	
	public void setContentSize(int contentSize) {
		this.contentSize = contentSize;
	}
	
	public void setEventNumber(int eventNumber) {
		this.eventNumber = eventNumber;
	}
	
	public void setEntityList(Model entityList) {
		this.entityList = entityList;
	}

	public int getEventNumber() {
		return eventNumber;
	}
}
