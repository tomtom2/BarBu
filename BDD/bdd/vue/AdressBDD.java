package bdd.vue;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bdd.model.Connect;
import bdd.model.Connection_valid;


public class AdressBDD extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	private TableInfo tableInfo = new TableInfo();
	
	/**
	 * Create the frame.
	 */
	public AdressBDD() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setBounds(0, 0, 600, 400);
		setVisible(true);
		setTitle("connection");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(new GridLayout(3, 1, 10, 10));
		
		JPanel panel = new JPanel();
		panel_4.add(panel);
		panel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel lblUrl = new JLabel("url :");
		panel.add(lblUrl);
		
		textField = new JTextField(Connect.getUrl());
		textField.setText(""+Connect.getUrl());
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_4.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel lblUser = new JLabel("user :");
		panel_1.add(lblUser);
		
		textField_1 = new JTextField(Connect.getUser());
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_4.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("password :");
		panel_2.add(lblNewLabel);
		
		textField_2 = new JTextField(Connect.getPasswd());
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		splitPane.setLeftComponent(panel_4);
		
		JPanel panel_5 = new JPanel();
		splitPane.setRightComponent(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTables = new JLabel("Tables");
		panel_5.add(lblTables, BorderLayout.NORTH);
		
		panel_5.add(tableInfo, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(Connect.connectMaJ(textField.getText(), textField_1.getText(), textField_2.getText(), tableInfo)){
					
					tableInfo.getConn().updateObservateur();
					Connect.init();
					
					if(Connection_valid.connectionIsValide()){
						dispose();
					}
					else{
						new JOptionPane();
						JOptionPane.showMessageDialog(null, "Vous êtes bien connectez\nmais certaines tables ou colonnes sont introuvables.", "Erreur", JOptionPane.WARNING_MESSAGE);
					}
				}
				else{
					new JOptionPane();
					JOptionPane.showMessageDialog(null, "la connection a echoue", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		ArrayList<JTextField> liste_champs_de_text = tableInfo.getTxtFieldsList();
		for(JTextField champ : liste_champs_de_text){
			champ.addKeyListener(new KeyListener(){

				@Override
				public void keyPressed(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyReleased(KeyEvent arg0) {
					// TODO Auto-generated method stub
					if(Connect.connectMaJ(textField.getText(), textField_1.getText(), textField_2.getText(), tableInfo)){
						tableInfo.getConn().updateObservateur();
					}
				}

				@Override
				public void keyTyped(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}
		panel_3.add(btnNewButton);
		
		
	}

}
