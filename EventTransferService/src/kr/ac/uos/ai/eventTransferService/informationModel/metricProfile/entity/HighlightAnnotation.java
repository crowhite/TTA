package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity;

import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;

public class HighlightAnnotation extends Entity{
	private long dateCreated;
	private long dateModified;
	private String annotated;
	private String selectionText;
	private String selectionStart;
	private String selectionEnd;
	
	
	public HighlightAnnotation(String id) {
		super(id, EntityType.HighlightAnnotation.getType());

		// TODO Auto-generated constructor stub
	}
	
	public String toString(){
		RDFStringBuilder rb = super.buildRDFString();
		rb.buildRDF("dateCreated",dateCreated);
		rb.buildRDF("dateModified",dateModified);
		rb.buildRDF("annotated",annotated);
		rb.buildRDF("selectionText",selectionText);
		rb.buildRDF("selectionStart", selectionStart);
		rb.buildRDF("selectionEnd", selectionEnd);
		
		
		
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


	public String getSelectionText() {
		return selectionText;
	}


	public void setSelectionText(String selectionText) {
		selectionText = selectionText.replace("\n", "");
		this.selectionText = selectionText;
	}

	

	public String getSelectionStart() {
		return selectionStart;
	}

	public String getSelectionEnd() {
		return selectionEnd;
	}

	public void setSelection(String selection) {
		String tempSelection;
		String[] tempSelectionList, temp;
		tempSelection = selection.substring(1, selection.length() - 1);
		tempSelectionList = tempSelection.split(",");

		temp = tempSelectionList[0].split(":");

		if(temp.length == 2)
			selectionStart = temp[1].substring(1, temp[1].length() - 1);
		else if(temp.length == 3){
			
			selectionStart = temp[1] + ":" + temp[2];
			selectionStart = selectionStart.substring(1, selectionStart.length() - 1);
			selectionStart = selectionStart.replace("\\", "");
		}
		

		temp = tempSelectionList[1].split(":");

		if(temp.length == 2)
			selectionEnd = temp[1].substring(1, temp[1].length() - 1);
		else if(temp.length == 3){
			selectionEnd = temp[1] + ":" + temp[2];
			selectionEnd = selectionEnd.substring(1, selectionEnd.length() - 1);
			selectionEnd= selectionEnd.replace("\\", "");
		}
		
	}
	
	

}
