package bdd.utilisateur;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Inscription_vue extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JComboBox comboBox;
	private JRadioButton rdbtnSidentifier;
	private JRadioButton rdbtnSinscrir;
	
	private Inscription_model inscrits;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public Inscription_vue() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		ButtonGroup group = new ButtonGroup();
		
		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel_5.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblVousDevezVous = new JLabel("Vous devez vous identifier, ou vous inscrir, afin de");
		panel.add(lblVousDevezVous);
		
		JLabel lblNewLabel = new JLabel("définir un nom et un mot de pass pour l'utilisateur.");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_5.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(2, 1, 5, 5));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		rdbtnSidentifier = new JRadioButton("s'identifier :");
		group.add(rdbtnSidentifier);
		rdbtnSidentifier.setSelected(true);
		rdbtnSidentifier.setBounds(19, 21, 170, 30);
		rdbtnSidentifier.addActionListener(new RadioListener());
		panel_3.add(rdbtnSidentifier);
		
		comboBox = new JComboBox();
		comboBox.setBounds(222, 16, 150, 40);
		panel_3.add(comboBox);
		
		inscrits = new Inscription_model();
		for(String str : inscrits.getListeUtilisateurs()){
			comboBox.addItem(str);
		}
		inscrits.addObservateur(new Observateur(){

			@Override
			public void update(ArrayList<String> listeUtilisateurs) {
				// TODO Auto-generated method stub
				comboBox = new JComboBox();
				for(int i=0; i<listeUtilisateurs.size(); i++){
					comboBox.addItem(listeUtilisateurs.get(i));
				}
			}
			
		});
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		rdbtnSinscrir = new JRadioButton("s'inscrir : ");
		group.add(rdbtnSinscrir);
		rdbtnSinscrir.setBounds(21, 26, 120, 28);
		rdbtnSinscrir.addActionListener(new RadioListener());
		panel_4.add(rdbtnSinscrir);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(200, 0, 230, 60);
		panel_4.add(panel_6);
		panel_6.setLayout(new GridLayout(2, 2, 5, 5));
		
		JLabel lblNom = new JLabel("nom :");
		panel_6.add(lblNom);
		
		textField = new JTextField("");
		panel_6.add(textField);
		textField.setEnabled(false);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("password :");
		panel_6.add(lblPassword);
		
		textField_1 = new JTextField("");
		panel_6.add(textField_1);
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_5.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new Valider());
		panel_2.add(btnValider);
		
		JMenuBar menuBar = new JMenuBar();
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		JMenuItem mntmConnexion = new JMenuItem("connexion");
		mntmConnexion.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Connection_set_adress();
				dispose();
			}
			
		});
		menuBar.add(mntmConnexion);
		
	}
	
	public class RadioListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(rdbtnSinscrir.isSelected()){
				comboBox.setEnabled(false);
				textField.setEnabled(true);
				textField_1.setEnabled(true);
			}
			else if(rdbtnSidentifier.isSelected()){
				comboBox.setEnabled(true);
				textField.setEnabled(false);
				textField_1.setEnabled(false);
			}
		}
		
	}
	
	public class Valider implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(rdbtnSidentifier.isSelected()){
				inscrits.saveIDofSelectedItem(comboBox.getSelectedIndex());
				String nom = comboBox.getSelectedItem().toString();
				Utilisateur.dir_root = nom+"/";
				Utilisateur.first_time = true;
				new Utilisateur();
				dispose();
			}
			else if(rdbtnSinscrir.isSelected() && !textField.getText().equals("") && !textField_1.getText().equals("")){
				String nom = textField.getText();
				String code = textField_1.getText();
				File f = new File(nom);
				if(!f.exists()){
					boolean ajoutOk = inscrits.addUtilisateur(nom, code);
					if(ajoutOk){
						Utilisateur.dir_root = nom+"/";
						Utilisateur.first_time = true;
						new Utilisateur();
						dispose();
					}
					else{
						JOptionPane.showMessageDialog(null, "une erreure s'est produite. \nVérifiez qu'il n'y a pas de doublons parmis les utilisateurs.", "alert", JOptionPane.ERROR_MESSAGE); 
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "le dossier "+f.getAbsolutePath()+"existe déjà !", "alert", JOptionPane.ERROR_MESSAGE); 
					
				}
				
			}
			
		}
		
	}
}
