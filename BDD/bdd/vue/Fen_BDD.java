package bdd.vue;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import bdd.model.Connect;


public class Fen_BDD extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCheminnomdufichiercsv;
	private ButtonGroup bg = new ButtonGroup();
	private JRadioButton rdbtnNewRadioButton, rdbtnRemplacerParLe, rdbtnSupprimerTousLes;
	private JPanel panel_3;
	private JRadioButton rdbtnRecovery;
	private JPanel panel_4;

	/**
	 * Create the frame.
	 */
	public Fen_BDD() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Base de donnees");
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel_4 = new JPanel();
		contentPane.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_4.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		panel_2.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		rdbtnNewRadioButton = new JRadioButton("ajouter le contenu d'un fichier .csv");
		panel.add(rdbtnNewRadioButton);
		
		rdbtnRemplacerParLe = new JRadioButton("remplacer par le contenu d'un fichier .csv");
		panel.add(rdbtnRemplacerParLe);
		
		JPanel panel_1 = new JPanel();
		panel_2.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		txtCheminnomdufichiercsv = new JTextField();
		txtCheminnomdufichiercsv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCheminnomdufichiercsv.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(txtCheminnomdufichiercsv);
		txtCheminnomdufichiercsv.setText("chemin/nom_du_fichier.csv");
		txtCheminnomdufichiercsv.setColumns(10);
		
		bg.add(rdbtnNewRadioButton);
		bg.add(rdbtnRemplacerParLe);
		
		panel_3 = new JPanel();
		panel_4.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		rdbtnSupprimerTousLes = new JRadioButton("supprimer tous les comptes");
		rdbtnSupprimerTousLes.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(rdbtnSupprimerTousLes);
		bg.add(rdbtnSupprimerTousLes);
		
		rdbtnRecovery = new JRadioButton("recovery");
		rdbtnRecovery.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(rdbtnRecovery);
		bg.add(rdbtnRecovery);
		rdbtnRecovery.setEnabled(false);
		
		JButton btnValider = new JButton("valider");
		panel_4.add(btnValider);
		
		btnValider.addActionListener(new Action());
	}
	
	class Action implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(rdbtnNewRadioButton.isSelected()){
				try {
					Connect.loadFromCSV(txtCheminnomdufichiercsv.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}
			else if(rdbtnRemplacerParLe.isSelected()){
				Connect.remplacerParCSV(txtCheminnomdufichiercsv.getText());
				dispose();
			}
			else if(rdbtnSupprimerTousLes.isSelected()){
				Connect.supprimerTOUT();
				dispose();
			}
			else if(rdbtnRecovery.isSelected()){
				try {
					//Connect.recovery();
					dispose();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}

}
