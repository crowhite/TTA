package kr.ac.uos.ai.eventTransferService.debug.ui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JsonPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2189114797638621183L;
	private JTextArea						textArea;
	private JScrollPane						scrollPanel;
			
	public JsonPanel() {
		textArea = new JTextArea();

		textArea.setColumns(33);
		textArea.setLineWrap(true);
		textArea.setRows(33);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		
		scrollPanel = new JScrollPane(textArea);
		this.add(scrollPanel, BorderLayout.CENTER);
		this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Input JSON"),
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
