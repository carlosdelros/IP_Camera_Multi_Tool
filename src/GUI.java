import hostUploader.Uploader;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JProgressBar;
import javax.swing.JButton;

import camReport.Connect;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusAdapter;
import java.io.File;
import java.io.IOException;

import javax.swing.SwingConstants;

import java.awt.Color;


public class GUI implements ActionListener, FocusListener{

	private JFrame frame;
	private JTextField sOctet;
	private JTextField eOctet;
	private JTextField uName;
	private JPasswordField passwordField;
	private JTextField firstOctet;
	private JTextField secondOctet;
	private JTextField thirdOctet;
	private JButton CamReportBtn;
	private JButton hostUploaderBrowseBtn;
	private JTextField filePath;
	JButton hostUploaderRunBtn;
	final JFileChooser fc = new JFileChooser();
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JPasswordField passwordField_1;
	private JProgressBar progressBar;
	private JCheckBox checkBoxPing;
	JComponent CameraReportPanel;
	private File selectedFile=null;

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
		frame.setVisible(true);firstOctet.grabFocus();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 505, 467);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 500, 442);

		/*Camera Report Panel*/
		CameraReportPanel=new JPanel();
		tabbedPane.add("Camera Report",CameraReportPanel);
		tabbedPane.setEnabledAt(0, true);
		CameraReportPanel.setLayout(null);

		JLabel label = new JLabel("Insert First Part of IP (xxx.xxx.xxx):");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(36, 40, 232, 25);
		CameraReportPanel.add(label);

		JLabel label_1 = new JLabel("Insert Starting Host Number:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(36, 72, 181, 17);
		CameraReportPanel.add(label_1);

		JLabel label_2 = new JLabel("Insert Ending Host Number:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(36, 112, 181, 17);
		CameraReportPanel.add(label_2);

		sOctet = new JTextField();
		sOctet.setFont(new Font("Dialog", Font.PLAIN, 20));
		sOctet.addFocusListener(this);
		sOctet.setColumns(10);
		sOctet.setBounds(278, 70, 40, 25);
		CameraReportPanel.add(sOctet);

		eOctet = new JTextField();
		eOctet.setFont(new Font("Dialog", Font.PLAIN, 20));
		eOctet.addFocusListener(this);
		eOctet.setColumns(10);
		eOctet.setBounds(278, 110, 40, 25);
		CameraReportPanel.add(eOctet);

		JLabel label_3 = new JLabel("Enter Username:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBounds(36, 205, 106, 14);
		CameraReportPanel.add(label_3);

		JLabel label_4 = new JLabel("Enter Password:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_4.setBounds(36, 249, 106, 14);
		CameraReportPanel.add(label_4);

		uName = new JTextField();
		uName.setFont(new Font("Tahoma", Font.PLAIN, 19));
		uName.addFocusListener(this);
		uName.setText("root");
		uName.setColumns(10);
		uName.setBounds(143, 195, 149, 32);
		CameraReportPanel.add(uName);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 19));
		passwordField.addFocusListener(this);
		passwordField.setText("p@ssw0rd");
		passwordField.setBounds(143, 239, 149, 32);
		CameraReportPanel.add(passwordField);

		checkBoxPing = new JCheckBox("Ping Address");
		checkBoxPing.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkBoxPing.setSelected(true);
		checkBoxPing.setBounds(36, 144, 125, 23);
		CameraReportPanel.add(checkBoxPing);

		progressBar = new JProgressBar();
		progressBar.setString("");
		progressBar.setStringPainted(true);
		progressBar.setBounds(68, 331, 374, 32);
		CameraReportPanel.add(progressBar);

		CamReportBtn = new JButton("Run");
		CamReportBtn.addActionListener(this);
		CamReportBtn.setBounds(201, 282, 104, 38);
		CameraReportPanel.add(CamReportBtn);

		firstOctet = new JTextField();
		firstOctet.addFocusListener(this);
		firstOctet.setFont(new Font("Tahoma", Font.PLAIN, 20));
		firstOctet.setText("10");
		firstOctet.setColumns(3);
		firstOctet.setBounds(278, 38, 40, 25);
		CameraReportPanel.add(firstOctet);

		secondOctet = new JTextField();
		secondOctet.addFocusListener(this);
		secondOctet.setFont(new Font("Dialog", Font.PLAIN, 20));
		secondOctet.setColumns(3);
		secondOctet.setBounds(328, 37, 40, 25);
		CameraReportPanel.add(secondOctet);

		thirdOctet = new JTextField();
		thirdOctet.addFocusListener(this);
		thirdOctet.setFont(new Font("Dialog", Font.PLAIN, 20));
		thirdOctet.setColumns(3);
		thirdOctet.setBounds(378, 37, 40, 25);
		CameraReportPanel.add(thirdOctet);

		JLabel label_5 = new JLabel(".");
		label_5.setFont(new Font("Dialog", Font.BOLD, 17));
		label_5.setBounds(320, 50, 4, 14);
		CameraReportPanel.add(label_5);

		JLabel label_6 = new JLabel(".");
		label_6.setFont(new Font("Dialog", Font.BOLD, 17));
		label_6.setBounds(370, 50, 4, 14);
		CameraReportPanel.add(label_6);

		/*Host Uploader Panel*/
		frame.getContentPane().add(tabbedPane);
		JComponent HostUploaderPanel = new JPanel();
		tabbedPane.add("Host Uploader",HostUploaderPanel);
		tabbedPane.setEnabledAt(1, true);
		HostUploaderPanel.setLayout(null);

		hostUploaderBrowseBtn = new JButton("Browse");
		hostUploaderBrowseBtn.addActionListener(this);
		hostUploaderBrowseBtn.setBounds(23, 82, 117, 29);
		HostUploaderPanel.add(hostUploaderBrowseBtn);

		filePath = new JTextField();
		filePath.addFocusListener(this);
		filePath.setFont(new Font("Tahoma", Font.PLAIN, 10));
		filePath.setBounds(150, 86, 318, 20);
		HostUploaderPanel.add(filePath);
		filePath.setColumns(10);

		hostUploaderRunBtn = new JButton("Run");
		hostUploaderRunBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		hostUploaderRunBtn.addActionListener(this);
		hostUploaderRunBtn.setBounds(157, 334, 186, 60);
		hostUploaderRunBtn.setEnabled(false);
		HostUploaderPanel.add(hostUploaderRunBtn);

		JLabel label_7 = new JLabel("Insert First Part of IP (xxx.xxx.xxx):");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_7.setBounds(23, 125, 232, 25);
		HostUploaderPanel.add(label_7);

		textField_7 = new JTextField();
		textField_7.addFocusListener(this);
		textField_7.setText("10");
		textField_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_7.setColumns(3);
		textField_7.setBounds(265, 123, 40, 25);
		HostUploaderPanel.add(textField_7);

		textField_8 = new JTextField();
		textField_8.addFocusListener(this);
		textField_8.setFont(new Font("Dialog", Font.PLAIN, 20));
		textField_8.setColumns(3);
		textField_8.setBounds(315, 122, 40, 25);
		HostUploaderPanel.add(textField_8);

		textField_9 = new JTextField();
		textField_9.addFocusListener(this);
		textField_9.setFont(new Font("Dialog", Font.PLAIN, 20));
		textField_9.setColumns(3);
		textField_9.setBounds(365, 122, 40, 25);
		HostUploaderPanel.add(textField_9);

		JLabel label_8 = new JLabel("Insert Starting Host Number:");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_8.setBounds(23, 157, 181, 17);
		HostUploaderPanel.add(label_8);

		textField_10 = new JTextField();
		textField_10.addFocusListener(this);
		textField_10.setFont(new Font("Dialog", Font.PLAIN, 20));
		textField_10.setColumns(10);
		textField_10.setBounds(265, 155, 40, 25);
		HostUploaderPanel.add(textField_10);

		JLabel label_9 = new JLabel("Insert Ending Host Number:");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_9.setBounds(23, 197, 181, 17);
		HostUploaderPanel.add(label_9);

		textField_11 = new JTextField();
		textField_11.addFocusListener(this);
		textField_11.setFont(new Font("Dialog", Font.PLAIN, 20));
		textField_11.setColumns(10);
		textField_11.setBounds(265, 195, 40, 25);
		HostUploaderPanel.add(textField_11);

		JLabel label_10 = new JLabel("Enter Username:");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_10.setBounds(23, 265, 106, 14);
		HostUploaderPanel.add(label_10);

		textField_12 = new JTextField();
		textField_12.addFocusListener(this);
		textField_12.setText("root");
		textField_12.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_12.setColumns(10);
		textField_12.setBounds(130, 255, 149, 32);
		HostUploaderPanel.add(textField_12);

		JLabel label_11 = new JLabel("Enter Password:");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_11.setBounds(23, 309, 106, 14);
		HostUploaderPanel.add(label_11);

		passwordField_1 = new JPasswordField();
		passwordField_1.addFocusListener(this);
		passwordField_1.setText("p@ssw0rd");
		passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		passwordField_1.setBounds(130, 291, 149, 32);
		HostUploaderPanel.add(passwordField_1);

		JLabel lblNewLabel = new JLabel("NOTE: Running this program REBOOTS the Camera(s)");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(23, 17, 445, 54);
		HostUploaderPanel.add(lblNewLabel);

		JLabel label_12 = new JLabel(".");
		label_12.setFont(new Font("Dialog", Font.BOLD, 17));
		label_12.setBounds(308, 132, 4, 14);
		HostUploaderPanel.add(label_12);

		JLabel label_13 = new JLabel(".");
		label_13.setFont(new Font("Dialog", Font.BOLD, 17));
		label_13.setBounds(358, 132, 4, 14);
		HostUploaderPanel.add(label_13);
		JComponent snmpReportPanel = new JPanel();
		tabbedPane.add("SNMP Report",snmpReportPanel);
		tabbedPane.setEnabledAt(2, true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {


		if(e.getSource()==CamReportBtn){
			progressBar.setVisible(true);
			progressBar.setMinimum(Integer.parseInt(sOctet.getText()));
			progressBar.setMaximum(Integer.parseInt(eOctet.getText()));
			String ip=firstOctet.getText()+ "." + secondOctet.getText()+ "." +thirdOctet.getText();
			Connect con= new Connect(ip, sOctet.getText(),eOctet.getText(), uName.getText(), passwordField.getPassword(),progressBar,CamReportBtn,checkBoxPing.isSelected());
			con.execute();
		}

		else if(e.getSource()==hostUploaderBrowseBtn){

			fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int returnVal = fc.showOpenDialog(frame);
			if(returnVal==JFileChooser.APPROVE_OPTION){
				selectedFile = fc.getSelectedFile();
				filePath.setText(selectedFile.getAbsolutePath());
				hostUploaderRunBtn.setEnabled(true);
			}	
		}
		else if(e.getSource()==hostUploaderRunBtn){
			if(!selectedFile.getAbsolutePath().equals(filePath)&&(filePath.getText().trim().length() > 0))
			try {Uploader up=new Uploader(filePath.getText(),textField_12.getText(),passwordField_1.getPassword(), hostUploaderRunBtn);}
			catch (IOException e1) {e1.printStackTrace();}
			else
				JOptionPane.showMessageDialog(frame, "Please Select a File");
		}
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		JTextField jtf=(JTextField) e.getSource();
		jtf.selectAll();
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void enableComponent(String e){

	}
}
