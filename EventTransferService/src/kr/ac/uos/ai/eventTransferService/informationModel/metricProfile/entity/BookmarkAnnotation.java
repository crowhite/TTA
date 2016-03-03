package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity;

import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;

public class BookmarkAnnotation extends Entity{
	private long dateCreated;
	private long dateModified;
	private String annotated;
	private String bookmarkNotes;
	
	
	public BookmarkAnnotation(String id) {
		super(id, EntityType.HighlightAnnotation.getType());
	
	}
	
	public String toString(){
		RDFStringBuilder rb = super.buildRDFString();
		rb.buildRDF("dateCreated",dateCreated);
		rb.buildRDF("dateModified",dateModified);
		rb.buildRDF("annotated",annotated);
		rb.buildRDF("bookmarkNotes",bookmarkNotes);
		
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


	public String getAnnotated() {
		return annotated;
	}


	public void setAnnotated(String annotated) {
		this.annotated = annotated;
	}


	public String getBookmarkNotes() {
		return bookmarkNotes;
	}


	public void setBookmarkNotes(String selectionText) {
		this.bookmarkNotes = selectionText;
	}


	

	
	

}