package view;

import control.ButtonListener;

public class RDFMoniterManager {
	private REventFileAdder eventFileAdder;
	private ServerLogger serverLogger;
	
	public RDFMoniterManager(){
		eventFileAdder = new REventFileAdder();
		serverLogger = new ServerLogger();
	}
	
	public void setButtonListener(ButtonListener listener){
		eventFileAdder.setButtonListener(listener);
		serverLogger.setButtonListener(listener);
	}

	public REventFileAdder getEventFileAdder() {
		return eventFileAdder;
	}

	public void setEventFileAdder(REventFileAdder eventFileAdder) {
		this.eventFileAdder = eventFileAdder;
	}

	public ServerLogger getServerLogger() {
		return serverLogger;
	}

	public void setServerLogger(ServerLogger serverLogger) {
		this.serverLogger = serverLogger;
	}
}
