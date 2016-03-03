package model;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class TransferMassage {
	private TransferContext transferContext;
	private Model eventList;
	
	public TransferMassage(TransferContext context) {
		transferContext = context;
		eventList = ModelFactory.createDefaultModel();
	}

	public void addEventList(Model eventList) {
		this.eventList.add(eventList);
	}

	public int getEventNumber() {
		return transferContext.getEventNumber();
	}

	public Model getEventList() {
		return eventList;
	}
	
	
}
