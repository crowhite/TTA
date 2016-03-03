package kr.ac.uos.ai.eventTransferService.debug.ui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RdfPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6979787211343900246L;
	private JTextArea						textArea;
	private JScrollPane						scrollPanel;
	
	public RdfPanel() {
		textArea = new JTextArea();
		
		textArea.setColumns(33);
		textArea.setLineWrap(true);
		textArea.setRows(33);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		
		scrollPanel = new JScrollPane(textArea);
		this.add(scrollPanel, BorderLayout.CENTER);
		this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Message Logs"),
                BorderFactory.createEmptyBorder(1,1,1,1)));
	}
	
	public void addTextToPanel(String text){
		if(textArea.getText()==null){
			textArea.setText(text);						
		}
		else {
			String currentText = textArea.getText();
			textArea.setText(currentText + text);
		}
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	
	public void clearPanel() {
		textArea.setText("");
	}
	
}
