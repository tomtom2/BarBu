package bdd.utilisateur;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import bdd.model.Connect;

import au.com.bytecode.opencsv.CSVReader;

public class Connection_set_adress extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldurl;
	private JTextField textFielduser;
	private JTextField textFieldpass;
	
	private String url = "";
	private String user = "";
	private String passwd = "";
	

	/**
	 * Create the frame.
	 */
	public Connection_set_adress() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		setVisible(true);
		readBdd();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{116, 312, 0};
		gbl_panel.rowHeights = new int[]{40, 40, 40, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblurl = new JLabel("url : ");
		lblurl.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblurl = new GridBagConstraints();
		gbc_lblurl.fill = GridBagConstraints.BOTH;
		gbc_lblurl.insets = new Insets(0, 0, 5, 5);
		gbc_lblurl.gridx = 0;
		gbc_lblurl.gridy = 0;
		panel.add(lblurl, gbc_lblurl);
		
		textFieldurl = new JTextField(url);
		GridBagConstraints gbc_textFieldurl = new GridBagConstraints();
		gbc_textFieldurl.fill = GridBagConstraints.BOTH;
		gbc_textFieldurl.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldurl.gridx = 1;
		gbc_textFieldurl.gridy = 0;
		panel.add(textFieldurl, gbc_textFieldurl);
		textFieldurl.setColumns(10);
		
		JLabel lblUser = new JLabel("user : ");
		lblUser.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.fill = GridBagConstraints.BOTH;
		gbc_lblUser.insets = new Insets(0, 0, 5, 5);
		gbc_lblUser.gridx = 0;
		gbc_lblUser.gridy = 1;
		panel.add(lblUser, gbc_lblUser);
		
		textFielduser = new JTextField(user);
		GridBagConstraints gbc_textFielduser = new GridBagConstraints();
		gbc_textFielduser.fill = GridBagConstraints.BOTH;
		gbc_textFielduser.insets = new Insets(0, 0, 5, 0);
		gbc_textFielduser.gridx = 1;
		gbc_textFielduser.gridy = 1;
		panel.add(textFielduser, gbc_textFielduser);
		textFielduser.setColumns(10);
		
		JLabel lblPassword = new JLabel("password : ");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.BOTH;
		gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 2;
		panel.add(lblPassword, gbc_lblPassword);
		
		textFieldpass = new JTextField(passwd);
		GridBagConstraints gbc_textFieldpass = new GridBagConstraints();
		gbc_textFieldpass.fill = GridBagConstraints.BOTH;
		gbc_textFieldpass.gridx = 1;
		gbc_textFieldpass.gridy = 2;
		panel.add(textFieldpass, gbc_textFieldpass);
		textFieldpass.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(Connect.simpleConnection(textFieldurl.getText(), textFielduser.getText(), textFieldpass.getText())){
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							
							Connect.getInstance();
							new Inscription_vue();
							//new Utilisateur();//on lance la session
						}
					});
					dispose();
				}
			}
			
		});
		panel_1.add(btnOk);
	}
	
	public void readBdd(){
			if(new File(Connect.filepath).exists()){
				CSVReader reader;
				try {
					reader = new CSVReader(new FileReader(Connect.filepath));
			    String [] nextLine;
			    try {
					while ((nextLine = reader.readNext()) != null) {
					    // nextLine[] is an array of values from the line
						if(nextLine.length == 1){
							url = nextLine[0]; user = ""; passwd = "";
						}
						else if(nextLine.length == 2){
							url = nextLine[0]; user = nextLine[1]; passwd = "";
						}
						else if(nextLine.length == 3){
							url = nextLine[0]; user = nextLine[1]; passwd = nextLine[2];
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
