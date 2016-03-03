package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import control.ButtonListener;
import model.RAnalysisData;
import utility.Constant;

public class REventFileAdder extends JFrame implements ActionListener{

	private JButton okButton;
	private JButton cancelButton;
	
	private JLabel developerLabel;
	private JLabel versionLabel;
	private JLabel resultTypeLabel;
	private JLabel resultDataLabel;
	private JLabel requiredTypeLabel;
	private JLabel requiredDataLabel;
	private JLabel fileLocationLabel;	
	private JLabel nameLabel;
	
	
	private JTextField developerTextField;
	private JTextField versionTextField;
	private JTextField resultTypeTextField;
	private JTextField resultDataTextField;
	private JTextField requiredTypeTextField;
	private JTextField requiredDataTextField;
	private JTextField fileLocationTextField;
	private JTextField nameTextField;
	
	public REventFileAdder(){

		okButton = new JButton("ok");
		cancelButton = new JButton("cancel");

		nameLabel = new JLabel("name");
		developerLabel = new JLabel("developer");
		versionLabel = new JLabel("version");
		resultTypeLabel = new JLabel("result type");
		resultDataLabel = new JLabel("result data");
		requiredTypeLabel = new JLabel("required type");
		requiredDataLabel = new JLabel("required data");
		fileLocationLabel = new JLabel("file location");
		
		
		nameTextField = new JTextField();
		developerTextField = new JTextField();
		versionTextField = new JTextField();
		resultTypeTextField = new JTextField();
		resultDataTextField = new JTextField();
		requiredTypeTextField = new JTextField();
		requiredDataTextField = new JTextField();
		fileLocationTextField = new JTextField();
		
		
		
		init();
	}
	
	public void init(){
		this.setSize(300,430);
		this.setLayout(null);
			
		okButton.setBounds(65,350,75,25);
		cancelButton.setBounds(165,350,75,25);
		this.add(okButton);
		this.add(cancelButton);

		nameLabel.setBounds(50,20,100,25);
		developerLabel.setBounds(50, 60, 100, 25);
		versionLabel.setBounds(50, 100, 60, 25);
		resultTypeLabel.setBounds(50, 140, 100, 25);
		resultDataLabel.setBounds(50, 180, 100, 25);
		requiredTypeLabel.setBounds(50, 220, 100, 25);
		requiredDataLabel.setBounds(50, 260, 100, 25);
		fileLocationLabel.setBounds(50, 300, 100, 25);
		this.add(developerLabel);
		this.add(versionLabel);
		this.add(resultTypeLabel);
		this.add(resultDataLabel);
		this.add(requiredTypeLabel);
		this.add(requiredDataLabel);
		this.add(fileLocationLabel);
		this.add(nameLabel);
		
		nameTextField.setBounds(150,20,100,25);
		developerTextField.setBounds(150, 60, 100, 25);
		versionTextField.setBounds(150, 100, 100, 25);
		resultTypeTextField.setBounds(150, 140, 100, 25);
		resultDataTextField.setBounds(150, 180, 100, 25);
		requiredTypeTextField.setBounds(150, 220, 100, 25);
		requiredDataTextField.setBounds(150, 260, 100, 25);
		fileLocationTextField.setBounds(150, 300, 100, 25);
	
		this.add(nameTextField);
		this.add(developerTextField);
		this.add(versionTextField);
		this.add(resultTypeTextField);
		this.add(resultDataTextField);
		this.add(requiredTypeTextField);
		this.add(requiredDataTextField);
		this.add(fileLocationTextField);

		cancelButton.addActionListener(this);
	}

	public void setButtonListener(ButtonListener listener) {
		okButton.addActionListener(listener);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("cancel")){
			clear();
		}
	}
	
	public void clear(){
		developerTextField.setText("");
		versionTextField.setText("");
		resultTypeTextField.setText("");
		resultDataTextField.setText("");
		requiredTypeTextField.setText("");
		requiredDataTextField.setText("");
		fileLocationTextField.setText("");
		this.setVisible(false);
	}
	
	
	public RAnalysisData getRAnalysisData(){
		RAnalysisData data = new RAnalysisData();

		String name = nameTextField.getText();
		
		String developer = developerTextField.getText();
		String version = versionTextField.getText();
		String resultType = resultTypeTextField.getText();
		String resultData = resultDataTextField.getText();
		String requiredType = requiredTypeTextField.getText();
		String requiredData = requiredDataTextField.getText();
		String fileLocation = fileLocationTextField.getText();
		
		
		developer = Constant.ANALYSIS_METADATA_PREFIX + "/" + name + "/" + developer;
		resultData = Constant.ANALYSIS_METADATA_PREFIX + "/" + name + "/" + resultData;
		requiredData = Constant.ANALYSIS_METADATA_PREFIX + "/" + name + "/" + requiredData;
		
		data.setName(Constant.ANALYSIS_METADATA_PREFIX + "/" + name);
		data.setDeveloper(developer);
		data.setVersion(version);
		data.setResultType(resultType);
		data.setResultData(resultData);
		data.setRequiredType(requiredType);
		data.setRequiredData(requiredData);
		data.setFileLocation(fileLocation);
		
		clear();
	
		return data;
	}
	
	
}
