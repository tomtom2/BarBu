package source;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import bdd.model.Connect;


public class ModifierIntFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;
	private JTextField textField;
	private ModifierIntFrame mod;
	/**
	 * Create the frame.
	 */
	public ModifierIntFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 250);
		setVisible(true);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setResizable(false);
		setVisible(true);
		
		mod = this;
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(panel);
		panel.setLayout(new GridLayout(3, 1, 5, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("ancien mot de pass : ");
		panel_1.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		panel_1.add(passwordField);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("nouveau mot de pass : ");
		panel_2.add(lblNewLabel_1);
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		FlowLayout fl_panel_3 = new FlowLayout(FlowLayout.CENTER, 5, 5);
		fl_panel_3.setAlignOnBaseline(true);
		panel_3.setLayout(fl_panel_3);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(new Valider());
		panel_3.add(btnNewButton);
		getContentPane().add(scrollPane);
		pack();
		
		
		

	}
	
	public class Valider implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			mod.dispose();
			String mdp = new String(passwordField.getPassword());
			System.out.println(mdp+" => "+textField.getText());
			if(mdp.equals(Connect.getUserPassWord())){
			    //MotDePass.setMotDePass_BDD(textField.getText());
			    Connect.setPassWd(textField.getText());
			    try {
					MotDePass.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else{
				
				JOptionPane.showMessageDialog(null, "L'ancien mot de pass est incorrect!\n", "erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
}
