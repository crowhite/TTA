package kr.ac.uos.ai.eventTransferService.debug.ui;



import java.awt.BorderLayout;

import javax.swing.JFrame;

public class DebugUIManager extends JFrame {
	/**
	 * 
	 */
	private static final long 						serialVersionUID = -9173701023413100464L;
	private static JsonPanel 								jsonPanel;
	private static RdfPanel 								rdfPanel;

	public DebugUIManager() {
		jsonPanel = new JsonPanel();
		rdfPanel = new RdfPanel();
		
		showFrame();
		initComponents();
	}

	public void showFrame() {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Message Log");
		this.setResizable(false);
		this.setVisible(true);
	}

	public void initComponents() {
		this.add(jsonPanel, BorderLayout.LINE_START);
		this.add(rdfPanel, BorderLayout.LINE_END);
	}

	public static void printJson(String json) {
		jsonPanel.addTextToPanel(json);
	}

	public static void printRdf(String rdf) {
		rdfPanel.addTextToPanel(rdf);
	}

}