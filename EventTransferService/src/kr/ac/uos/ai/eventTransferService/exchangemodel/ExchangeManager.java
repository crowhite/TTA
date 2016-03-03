package kr.ac.uos.ai.eventTransferService.exchangemodel;

import java.util.LinkedList;

import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity.*;
import kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.event.*;
import kr.ac.uos.ai.eventTransferService.transferCore.Configuration;

public class ExchangeManager {

	private int eventCount;
	
	public ExchangeManager(){

		eventCount = 1;
	}
	
	public RDFMessage createRDFMessage(LinkedList<Event> inputEventList){
		
		RDFMessage msg;
		msg = alignEvent(inputEventList);
		msg.setSender(Configuration.SERVICE_NAME);
		msg.setReciever(Configuration.EVENT_STORE_NAME);
		msg.setTransferInitiateTime(System.currentTimeMillis());
		msg.setEventNumber(msg.getEventList().size());
		System.out.println("msg : " + msg.toRDFString());
		
		return msg;
		
	}
	public RDFMessage createRDFMessage(Event inputEvent){
		LinkedList<Event> eventList = new LinkedList<Event>();
		eventList.add(inputEvent);
		RDFMessage msg;
		msg = alignEvent(eventList);
		msg.setSender(Configuration.SERVICE_NAME);
		msg.setReciever(Configuration.EVENT_STORE_NAME);
		msg.setTransferInitiateTime(System.currentTimeMillis());
		msg.setEventNumber(msg.getEventList().size());
		
		return msg;
		
	}
	
	
	
	public RDFMessage alignEvent(LinkedList<Event> inputEventList){
		Event currentEvent;
		boolean isSameEntity;
		RDFMessage msg = new RDFMessage();
		
		for(int i = 0; i < inputEventList.size();i++){
			currentEvent = inputEventList.get(i);
			msg.getEventList().add(currentEvent);
			
			for(int j = 0; j < currentEvent.getEntityList().size();j++){
				isSameEntity = false;
				for(int k = 0; k < msg.getEntityList().size();k++){
					if(compareEntity(currentEvent.getEntityList().get(j), msg.getEntityList().get(k)) == true){
						isSameEntity = true;
					}
				}
				if(isSameEntity == false){
					msg.getEntityList().add(currentEvent.getEntityList().get(j));
				}
			}
		}

		for(int i = 0; i < msg.getEventList().size();i++){
			System.out.println(msg.getEventList().get(i));
			System.out.println();
		}
		
		for(int i = 0; i < msg.getEntityList().size();i++){
			System.out.println(msg.getEntityList().get(i));
			System.out.println();
		}
		
		
		return msg;
	}
	
	
	public boolean compareEntity(Entity e1, Entity e2){
		if(e1.getType().equals(e2.getType()) == false)
			return false;
		
		if(e1.getId().equals(e2.getId()) == false)
			return false;
					
		return true;
	}
}
