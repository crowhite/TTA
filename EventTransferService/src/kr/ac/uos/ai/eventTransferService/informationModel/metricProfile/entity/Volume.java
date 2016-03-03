package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity;

import java.util.LinkedList;

import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;

public class Volume extends Entity{

	private LinkedList<String> keywords;
	private String name;
	
	public Volume(String id) {
		super(id, EntityType.Volume.getType());
		keywords = new LinkedList<String>();
	}
	
	public String toString(){
		RDFStringBuilder rb = super.buildRDFString();
		rb.buildRDF("name",name);
		
		for(int i = 0; i < keywords.size();i++){
			rb.buildRDF("keywords", keywords.get(i));
		}
		
		
		return rb.toString();
	}
	
	public LinkedList<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		String tempKeywords;
		String[] tempKeyLists;
		tempKeywords = keywords.substring(1, keywords.length() - 1);
		tempKeyLists = tempKeywords.split(",");
		for(int i = 0; i < tempKeyLists.length ;i++){
			this.keywords.add(tempKeyLists[i].substring(1, tempKeyLists[i].length() - 1));
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
