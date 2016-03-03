package control;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import entity.EntityHandler;
import event.EventHandler;
import model.RAnalysisData;
import utility.VirtuosoQuery;
import utility.VirtuosoQueryUtility;
import view.RDFMoniterManager;

public class RDFStoreServer {
	private InetSocketAddress addr;
	private HttpServer server;
	private RDFMoniterManager view;
	private ButtonListener listener;
	
	public static void main(String[] ar){
		
		try {
			new RDFStoreServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public RDFStoreServer() throws IOException {
		view = new RDFMoniterManager();
		listener = new ButtonListener(this);
		init();
	}
	
	private void init() throws IOException {
		
		view.setButtonListener(listener);
		addr = new InetSocketAddress(50011);
		server = HttpServer.create(addr, 0);
		server.createContext("/entity", new EntityHandler());
		server.createContext("/event", new EventHandler());
	}

	public void start() {
		server.start();
		view.getServerLogger().startServer();		
	}

	public void stop() throws IOException {
		server.stop(0);
		view.getServerLogger().stopServer();
		init();
	}

	public void setRFileVisible() {
		view.getEventFileAdder().setVisible(true);
	}
	
	public void addRFile(){
		RAnalysisData data = view.getEventFileAdder().getRAnalysisData();
		
		
		String query;
		query = VirtuosoQuery.genereteInsertQuery(data.getName(), "Developer", data.getDeveloper());
		VirtuosoQueryUtility.send(query);
		query = VirtuosoQuery.genereteInsertQuery(data.getName(), "FileLocation", data.getFileLocation());
		VirtuosoQueryUtility.send(query);
		query = VirtuosoQuery.genereteInsertQuery(data.getName(), "RequiredData", data.getRequiredData());
		VirtuosoQueryUtility.send(query);
		query = VirtuosoQuery.genereteInsertQuery(data.getName(), "RequiredType", data.getRequiredType());
		VirtuosoQueryUtility.send(query);
		query = VirtuosoQuery.genereteInsertQuery(data.getName(), "ResultData", data.getResultData());
		VirtuosoQueryUtility.send(query);
		query = VirtuosoQuery.genereteInsertQuery(data.getName(), "ResultType", data.getResultType());
		VirtuosoQueryUtility.send(query);
		query = VirtuosoQuery.genereteInsertQuery(data.getName(), "Version", data.getVersion());
		VirtuosoQueryUtility.send(query);
	}
	
}