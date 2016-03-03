package kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage;

public class RDFStringBuilder {
	private StringBuilder rdfString;
	
	public RDFStringBuilder(String id){
		rdfString = new StringBuilder();
		if(id.startsWith("http://"))
			rdfString.append("<" + id + ">" + "\n");
		else{
			rdfString.append(id + "\n");
		}
	}
	
	public void append(String input){
		rdfString.append(input);
	}
	
	public void buildRDF(String predicate, String object){
		rdfString.append(":" + predicate + " ");
		if(object.startsWith("http://"))
			rdfString.append("<" + object + ">" + ";" + "\n");
		else{
			rdfString.append("\"" + object + "\"" + ";" + "\n");
		}
	}
	
	public void buildRDF(String predicate, int content){
		this.buildRDF(predicate, String.valueOf(content));
	}
	
	public void buildRDF(String predicate, float content){
		this.buildRDF(predicate, String.valueOf(content));
	}
	
	public void buildRDF(String predicate, long content){
		this.buildRDF(predicate, String.valueOf(content));
	}
	
	public String toString(){
		rdfString.delete(rdfString.length() - 2, rdfString.length());
		rdfString.append(".");
		return rdfString.toString();
	}
}
