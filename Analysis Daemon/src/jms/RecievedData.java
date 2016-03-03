package jms;

import java.util.LinkedList;

public class RecievedData {
	String actor;
	String usingDataType;
	
	String key;
	String type;
	LinkedList<String> graphList;
	
	public RecievedData(){
		graphList = new LinkedList<String>();
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public LinkedList<String> getGraphList() {
		return graphList;
	}
	
	public void addGraph(String data) {
		graphList.add(data);
	}
	
	
}
