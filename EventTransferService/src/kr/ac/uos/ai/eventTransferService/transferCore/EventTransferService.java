package kr.ac.uos.ai.eventTransferService.transferCore;

import java.util.LinkedList;

import com.hp.hpl.jena.rdf.model.Model;

import kr.ac.uos.ai.eventTransferService.exchangemodel.ExchangeManager;
import kr.ac.uos.ai.eventTransferService.exchangemodel.RDFMessage;
import kr.ac.uos.ai.eventTransferService.informationModel.CaliperEventParseException;
import kr.ac.uos.ai.eventTransferService.informationModel.EventFactory;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.event.Event;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.event.NavigationEvent;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.event.ReadingEvent;
import kr.ac.uos.ai.eventTransferService.interactionModel.TransferManager;

public class EventTransferService{

	private MongoDBTrigger mongoDBTrigger;
	private EventFactory factory;
	private ExchangeManager exchangeManager;
	private TransferManager transferManager;
	private LinkedList<String> inputList;

	public EventTransferService(String input) {
		
		mongoDBTrigger = new MongoDBTrigger(this,input);
		factory = EventFactory.getInstance();
		exchangeManager = new ExchangeManager();
		transferManager = new TransferManager();
		inputList = new LinkedList<String>();
		
	}
	
	public void runTransferService() {
		int count = 0;
		String eventString = null;
		Event event = null;
		
		if (Configuration.DEBUG_MODE == false) {
			Thread t = new Thread(mongoDBTrigger);
			t.start();
		}
		
		System.out.println("Program On");
		
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			eventString = null;
			if (Configuration.DEBUG_MODE == true) {
				eventString = mongoDBTrigger.triggerEventFromFile(count);

				if (eventString == null) {
					break;
				}
			} else if (Configuration.DEBUG_MODE == false) {
				if (inputList.isEmpty() == false) {
					eventString = inputList.getFirst();
					inputList.removeFirst();
				}
			}

			if (eventString != null) {
				System.out.println(eventString);
				try {
					System.out.println(eventString);
					event = factory.makeEvent(eventString);
				} catch (CaliperEventParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(event != null){
					
					RDFMessage msg = exchangeManager.createRDFMessage(event);
					transferManager.transferMessage(msg);
					count++;
				}
			}
		}
	}

	public void eventInput(LinkedList<String> inputList) {
		this.inputList.addAll(inputList);
	}

	public static void main(String ar[]) {
		String input = "";
		if(ar.length >= 1){
			input = ar[0];
		}else{
			input = "mongodb://172.16.165.30:50015";
		}
		
		EventTransferService eventTransferService = new EventTransferService(input);
		eventTransferService.runTransferService();
		
	}



}
