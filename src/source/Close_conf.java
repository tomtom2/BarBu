package source;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.vue.Fenetre_principale;
import bdd.model.Connect;
import bdd.utilisateur.Utilisateur;


public class Close_conf extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JCheckBox chckbxNewCheckBox;

	/**
	 * Create the dialog.
	 */
	public Close_conf() {
		setBounds(100, 100, 260, 140);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblQuitterLeProgramme = new JLabel("Quitter le programme réinitialisera");
			contentPanel.add(lblQuitterLeProgramme);
			JLabel lblQuitterLeProgramme1 = new JLabel("toutes les données temporaires.");
			contentPanel.add(lblQuitterLeProgramme1);
			JLabel lblQuitterLeProgramme2 = new JLabel("On y va ?");
			contentPanel.add(lblQuitterLeProgramme2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if(chckbxNewCheckBox.isSelected()){
							Fenetre_principale.Exit();
						}
						else{
							DebiteurListener.init();
						
							DebiteurListener.save();
							try {
								Connect.generCSV(Utilisateur.dir_root+"comptes.csv");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								Connect.getInstance().close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Fenetre_principale.Exit();
						}
					}
					
				});
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dispose();
					}
					
				});
				buttonPane.add(cancelButton);
				{
					chckbxNewCheckBox = new JCheckBox("fermeture forcée");
					getContentPane().add(chckbxNewCheckBox, BorderLayout.NORTH);
				}
				setVisible(true);
			}
		}
	}

}
