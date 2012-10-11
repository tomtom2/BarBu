package doublon;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import source.Client;


public class Doublon_Pane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Client client;
	private JRadioButton rdbtnNewRadioButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	/**
	 * Create the panel.
	 */
	public Doublon_Pane(Client cl) {
		setLayout(new GridLayout(1, 5, 0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		
		client = cl;
		String prenom = client.getPrenom();
		String nom = client.getNom();
		int promo = client.getPromo();
		float solde = client.getSolde();
		
		rdbtnNewRadioButton = new JRadioButton("");
		rdbtnNewRadioButton.setSelected(true);
		panel.add(rdbtnNewRadioButton);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		
		lblNewLabel = new JLabel(prenom);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		
		lblNewLabel_1 = new JLabel(nom);
		panel_2.add(lblNewLabel_1);
		
		JPanel panel_3 = new JPanel();
		add(panel_3);
		
		lblNewLabel_3 = new JLabel(""+promo);
		panel_3.add(lblNewLabel_3);
		
		JPanel panel_4 = new JPanel();
		add(panel_4);
		
		lblNewLabel_2 = new JLabel(solde + " €");
		panel_4.add(lblNewLabel_2);

	}
	public Client getClient() {
		return client;
	}
	public JRadioButton getRdbtnNewRadioButton() {
		return rdbtnNewRadioButton;
	}
	public void setRdbtnNewRadioButton(JRadioButton rdbtnNewRadioButton) {
		this.rdbtnNewRadioButton = rdbtnNewRadioButton;
	}
	
	

}
