package kr.ac.uos.ai.eventTransferService.transferCore;

public class Configuration {
	public static final boolean					DEBUG_MODE=false;
	public static final String					TEST_FILE_PATH = "res\\sample_data\\";
	public static final String					DATA_LOCATION = "res\\sample_data";
	public static final String					MONGO_DB_LOCATION = "mongodb://172.16.165.30:50015";
	
	public static final String					TRANSFER_CONTEXT = "http://demo.uos.ac.kr/";
	public static final String 					SERVICE_NAME = "DummyService";
	public static final String					EVENT_STORE_NAME = "EventStore";
	
	public static final String					EVENT_URL_STRING = "http://127.0.0.1:50011/event";
	public static final String					ENTITY_URL_STRING = "http://127.0.0.1:50011/entity";
	
	public static final String					PREFIX_NAME = "";
	public static final String					PREFIX_URL = "http://demo.uos.ac.kr/";	
}
