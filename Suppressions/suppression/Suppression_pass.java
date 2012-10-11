package suppression;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import source.Client;
import source.MotDePass;


public class Suppression_pass extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<Client> L = new ArrayList<Client>();
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public Suppression_pass(ArrayList<Client> l) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("supprimer "+l.size()+" comptes ?");
		
		L = l;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblPassword = new JLabel("password : ");
		panel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(100, 20));
		panel.add(passwordField);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String input = new String(passwordField.getPassword());
				
				if(input.equals(MotDePass.getMot_de_pass())){
					int option = JOptionPane.showConfirmDialog(null, "supprimer les "+L.size()+" comptes sélectionnés?", "Suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
								
					if(option == JOptionPane.OK_OPTION)
					{
						for(int i=0; i<L.size(); i++){
							L.get(i).supprimer();
						}
						
					}
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "mot de pass incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
		panel_1.add(btnSupprimer);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		panel_1.add(btnAnnuler);
		
		pack();
	}

}
