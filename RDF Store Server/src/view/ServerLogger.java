package view;

import control.ButtonListener;
import control.RDFStoreServer;

public class ServerLogger extends JFrame{
	private JButton start, stop,addRFile;
	private RDFStoreServer server;
	private static JTextArea log;
	
	public ServerLogger() {
		super();
		
		start = new JButton("Start");
		stop = new JButton("Stop");
		addRFile = new JButton("AddRFile");
		
		start.setActionCommand("start");
		stop.setActionCommand("stop");
		addRFile.setActionCommand("addR");
		
		init();
		
	}
	
	public void init(){
		stop.setEnabled(false);
		System.out.println("aaaa");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(start);
		buttonPanel.add(stop);
		buttonPanel.add(addRFile);
		
		log = new JTextArea("");
		log.setEditable(false);
		log.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(log);
//		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.add(buttonPanel, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
		
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	public void startServer(){
		printLog("Server is start\n\n");
		start.setEnabled(false);
		stop.setEnabled(true);
	}
	
	public void stopServer(){
		printLog("Server is stopped\n\n");
		start.setEnabled(true);
		stop.setEnabled(false);
	}
		
	public static void printLog(String str) {
		log.append(str);
		log.setCaretPosition(log.getDocument().getLength());
	}

	public void setButtonListener(ButtonListener listener) {
		start.addActionListener(listener);
		stop.addActionListener(listener);
		addRFile.addActionListener(listener);
	}
}