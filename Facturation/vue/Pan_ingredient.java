package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Facture;
import source.Boisson;
import source.Client;
import facturation.observer.Observable;
import facturation.observer.Observateur;

public class Pan_ingredient extends JPanel implements Observable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boisson ingrediant;
	private int qtt = 0;
	private JButton supprButton;
	private Facture facture;
	private Client client;
	private int index_pane;
	//Notre collection d'observateurs !
	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();

	/**
	 * Create the panel.
	 */
	public Pan_ingredient(int i, Client cli, Boisson ing, int nb) {
		setLayout(new GridLayout(1, 4, 0, 0));
		this.setBackground(Color.WHITE);
		index_pane = i;
		client = cli;
		facture = client.getCommande();
		ingrediant = ing;
		qtt = nb;
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel);
		
		JLabel lblNewLabel = new JLabel(ingrediant.getNom());
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel(""+qtt);
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setPreferredSize(new Dimension(30, 50));
		add(panel_2);
		
		JLabel lblNewLabel_2 = new JLabel(qtt*ingrediant.getPrix()+" €");
		panel_2.add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setPreferredSize(new Dimension(30, 30));
		add(panel_3);
		
		supprButton = new JButton("");
		supprButton.setContentAreaFilled(false);
		supprButton.setPreferredSize(new Dimension(20, 20));
		
		ImageIcon img_default = new ImageIcon( getClass() .getResource( "icon_suppression.png" ));
		supprButton.setIcon(img_default);
		panel_3.add(supprButton);
		
		supprButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				facture.retirer_item(index_pane);
			}
			
		});

	}

	@Override
	public void addObservateur(Observateur obs) {
		// TODO Auto-generated method stub
		this.listObservateur.add(obs);
	}

	@Override
	public void delObservateur() {
		// TODO Auto-generated method stub
		this.listObservateur = new ArrayList<Observateur>();
	}

	@Override
	public void updateObservateur() {
		// TODO Auto-generated method stub
		for(Observateur obs : this.listObservateur )
			obs.update(facture.getCommande());
	}

}
