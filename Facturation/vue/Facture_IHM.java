package vue;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.Facture;
import model.ItemCommande;
import source.Abonnes;
import source.Client;
import source.vue.Cherche_ingredient;
import source.vue.Compt_client;
import bdd.utilisateur.Utilisateur;
import facturation.observer.Observateur;


public class Facture_IHM extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Facture commande;
	private JPanel contentPane;
	private JPanel mainPane = new JPanel();
	private JPanel panel_2;
	private Client client;

	/**
	 * Create the frame.
	 * @param recherche 
	 */
	public Facture_IHM(Client cli) {
		setTitle("Commande");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 250, 300);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		client = cli;
		commande = cli.getCommande();
		//On place un écouteur sur notre Facture
		commande.addObservateur(new Observateur(){
			@Override
			public void update(ArrayList<ItemCommande> item) {
				// TODO Auto-generated method stub
				affichage_miseAjour(item);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(mainPane);
		mainPane.setLayout(new GridLayout(0, 1, 5, 5));
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 2, 0, 0));
		
		panel_2 = new JPanel();
		panel.add(panel_2);
		
		JLabel lblTotal = new JLabel("Total : "+getTotal(commande.getCommande())+" €");
		panel_2.add(lblTotal);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		affichage_miseAjour(commande.getCommande());
		
		JButton btnValider = new JButton("Valider");
		panel_3.add(btnValider);
		btnValider.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				commande.valider();
				fermer();
				Compt_client.cp_client(client);
				Abonnes.save(Utilisateur.fichier_comptes);
			}
			
		});
	}
	
	public void fermer() {
		// TODO Auto-generated method stub
		Cherche_ingredient.fermer();
		dispose();
	}

	public float getTotal(ArrayList<ItemCommande> item){
		float res = 0;
		for(int i=0; i<item.size(); i++){
			float prix = item.get(i).getIngredient().getPrix();
			int q = item.get(i).getQtt();
			res += q*prix;
		}
		return res;
	}
	
	public void affichage_miseAjour(ArrayList<ItemCommande> item){
		mainPane.removeAll();
		for(int i=0; i<item.size(); i++){
			Pan_ingredient ing_panel = new Pan_ingredient(i, client, item.get(i).getIngredient(), item.get(i).getQtt());
			ing_panel.addObservateur(new Observateur(){
					@Override
					public void update(ArrayList<ItemCommande> item) {
						// TODO Auto-generated method stub
						affichage_miseAjour(item);
					}
				});
			mainPane.add(ing_panel);
		}
		panel_2.removeAll();
		panel_2.add(new JLabel("Total : "+getTotal(item)+" €"));
		panel_2.revalidate();
		panel_2.repaint();
		mainPane.revalidate();
		mainPane.repaint();
	}

}
