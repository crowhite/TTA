package daemon;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import jms.JMSReceiver;
import jms.RecievedData;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;

import analysis.Analysis;

public class AnalysisDaemon implements Daemon, Runnable {
	private final String URL_STRING = "http://aistore.uos.ac.kr:50012/complete";

	private String status = "";
	private Thread thread = null;
	private boolean flag = true;
	private JMSReceiver receiver = new JMSReceiver();
	private Analysis analysis = new Analysis();

	@Override
	public void run() {
		while (flag) {
			try {
				System.out.println("waiting....");
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			RecievedData event = receiver.recieveMessage();
			if (event != null) {
				if (event.getType().equals("commonData")) {
					analysis.runAnalysisCode(event.getKey());
					// System.out.println(event + "analysis Complete.");
					send(event.getKey());
				}else if(event.getType().equals("newType")){
					analysis.addEventType(event.getKey(), event.getGraphList());
				}
			}

			// if(receiver.recieveMessage()) {
			// VirtuosoQueryUtility.drop();
			// analysis.runAnalysisCode();
			// System.out.println("\n Analysis Complete.\n");
			// send();
			// }
		}
	}

	@Override
	public void init(DaemonContext arg0) throws DaemonInitException, Exception {
		System.out.println("Init Analysis Daemon...");
		status = "INITED";
		this.thread = new Thread(this);
		System.out.println("Init OK.");
		System.out.println();
	}

	@Override
	public void start() throws Exception {
		System.out.println("Status: " + status);
		System.out.println("Start Analysis Daemon...");
		status = "STARTED";
		this.thread.start();
		System.out.println("Start OK.");
		System.out.println();
	}

	@Override
	public void stop() throws Exception {
		System.out.println("Status: " + status);
		System.out.println("Stop Analysis Daemon...");
		status = "STOPED";
		this.thread.join(10);
		System.out.println("Stop OK");
		System.out.println();
	}

	@Override
	public void destroy() {
		System.out.println("Status: " + status);
		System.out.println("Destroy Analysis Daemon...");
		status = "DESTROIED";
		System.out.println("Destory OK");
		System.out.println();
	}

	private void send(String event) {
		try {
			URL url = new URL(URL_STRING);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("charset", "utf-8");
			conn.setRequestProperty("Content-Type", "text/plain");
			conn.setRequestProperty("event", event);
			conn.setUseCaches(false);
			DataOutputStream writer = new DataOutputStream(
					conn.getOutputStream());

			// String param = "event=" + event;
			// byte[] postData = param.getBytes(StandardCharsets.UTF_8);
			writer.write(event.getBytes());

			String line;
			String result = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			while ((line = reader.readLine()) != null) {
				result += line;
			}

			System.out.println(result);

			writer.close();
			reader.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
