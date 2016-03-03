package kr.ac.uos.ai.eventTransferService.informationModel;

public class CaliperEventParseException extends Exception {
	private String						causeData;

	public CaliperEventParseException(String data, String message) {
		super(message); 
		this.causeData = data;
	}
	
	public String getData(){
		return causeData;
	}
}
