package droits.vue;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import bdd.utilisateur.Utilisateur;

import droit.model.Liste_Utilisateurs;
import droit.observable.Observable;
import droit.observable.Observateur;

public class Pane_utilisateur extends JPanel implements Observable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int indice;
	private Liste_Utilisateurs liste_utilisateurs;
	
	private JButton supprButton;
	private JComboBox comboBox;
	private JLabel label;

	//Notre collection d'observateurs !
	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
	/**
	 * Create the panel.
	 */
	public Pane_utilisateur(final int indice, Liste_Utilisateurs util) {
		this.indice = indice;
		this.liste_utilisateurs = util;
		setLayout(new GridLayout(1, 3, 5, 5));
		
		JPanel panel = new JPanel();
		add(panel);
		
		label = new JLabel(liste_utilisateurs.getListe_nom().get(indice));
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		
		comboBox = new JComboBox();
		for(int i=0; i<Liste_Utilisateurs.droits.length; i++){
			comboBox.addItem(Liste_Utilisateurs.droits[i]);
		}
		comboBox.setSelectedItem(liste_utilisateurs.getListe_droits().get(indice));
		panel_1.add(comboBox);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		
		supprButton = new JButton("");
		supprButton.setContentAreaFilled(false);
		supprButton.setPreferredSize(new Dimension(20, 20));
		supprButton.setIcon(new ImageIcon("img/icon_suppression.png"));
		supprButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				liste_utilisateurs.removeUtil(indice);
			}
			
		});
		panel_2.add(supprButton);
		
		if(liste_utilisateurs.getListe_id().get(indice) == Utilisateur.getId()){
			comboBox.setEnabled(false);
			supprButton.setEnabled(false);
		}

	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
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
		for(Observateur obs : this.listObservateur ){
			obs.update(liste_utilisateurs);
		}
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

}
