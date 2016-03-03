package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity;

import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;

public class Session extends Entity{
	private long			dateCreated;
	private long			dateModified;
	
	public Session(String id) {
		super(id, EntityType.Session.getType());
	}
	
	public String toString(){
		RDFStringBuilder rb = super.buildRDFString();
		rb.buildRDF("dateCreated",dateCreated);
		rb.buildRDF("dateModified",dateModified);

		return rb.toString();
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
