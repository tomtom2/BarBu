package droits.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import droit.model.Liste_Utilisateurs;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class Fen_ajout extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_pass;
	private JTextField textField_nom;
	private JComboBox comboBox;
	
	private Liste_Utilisateurs liste;

	/**
	 * Create the frame.
	 */
	public Fen_ajout(Liste_Utilisateurs listeUtil) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		setTitle("Ajout utilisateur");
		setVisible(true);
		setLocationRelativeTo(null);
		
		liste = listeUtil;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		comboBox = new JComboBox();
		for(int i=0; i<Liste_Utilisateurs.droits.length; i++){
			comboBox.addItem(Liste_Utilisateurs.droits[i]);
		}
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblNom = new JLabel("nom");
		lblNom.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNom);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblPassword);
		
		JLabel lblDroit = new JLabel("droit");
		lblDroit.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblDroit);
		
		textField_nom = new JTextField();
		panel.add(textField_nom);
		textField_nom.setColumns(10);
		
		textField_nom.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(!textField_nom.getText().equals("")){
					textField_nom.setBackground(Color.WHITE);
				}
				else{
					textField_nom.setBackground(Color.RED);
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		textField_pass = new JTextField();
		panel.add(textField_pass);
		textField_pass.setColumns(10);
		panel.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String nom = textField_nom.getText().replace("#", "");
				String code = textField_pass.getText().replace("#", "");
				String droit = comboBox.getSelectedItem().toString();
				if(!nom.equals("")){
					liste.addUtil(nom, code, droit);
					dispose();
				}
				else{
					textField_nom.setBackground(Color.RED);
				}
				
			}
			
		});
		panel_1.add(btnValider);
	}
}
