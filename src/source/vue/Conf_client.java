package source.vue;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JButton;

import source.Abonnes;
import source.Client;
import source.Suivi_client;

import main.vue.Fenetre_principale;

import bdd.model.Connect;


public class Conf_client extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Conf_client conf;
	private Fen_client client;
	private String nom;
	private String prenom;
	private int promo;
	private float solde;
	private Client client_a_modifier;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	private Conf_client(Fen_client fenClient, String n, String p, int pr, float s) {
		nom = n;
		prenom = p;
		solde = s;
		promo = pr;
		
		setAlwaysOnTop(true);
		this.client = fenClient;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(220, 165);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setVisible(true);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 2, 10, 10));
		
		JLabel lblNewLabel = new JLabel("nom : ");
		panel.add(lblNewLabel);
		
		JLabel lblnom = new JLabel(n);
		panel.add(lblnom);
		
		JLabel lblNewLabel_2 = new JLabel("prenom : ");
		panel.add(lblNewLabel_2);
		
		JLabel lblprenom = new JLabel(p);
		panel.add(lblprenom);
		
		JLabel lblNewLabel_4 = new JLabel("solde : ");
		panel.add(lblNewLabel_4);
		
		String sol = s + " €";
		JLabel lblsolde = new JLabel(sol);
		panel.add(lblsolde);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(1, 2, 20, 10));
		
		JButton valider = new JButton("valider");
		valider.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				conf.dispose();
				client.dispose();
				Fen_client.setNb_fen(Fen_client.getNb_fen()-1);
				
				Client c = new Client(-1, nom, prenom, solde, promo);
				if(Abonnes.ajouter(c)){
					Connect.ajouter(c);
					Fenetre_principale.affichage_MaJ();
				}
				
			}
		});
		panel_1.add(valider);
		
		JButton annuler = new JButton("annuler");
		annuler.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				conf.dispose();
				client.getValider().setEnabled(true);
			}
		});
		panel_1.add(annuler);
	}
	
	private Conf_client(final Fen_client fenClient, Client clientAModifier, String n, String p, int pr, float s) {
		nom = n;
		prenom = p;
		solde = s;
		promo = pr;
		client_a_modifier = clientAModifier;
		
		setAlwaysOnTop(true);
		this.client = fenClient;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(220, 165);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setVisible(true);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 2, 10, 10));
		
		JLabel lblNewLabel = new JLabel("nom : ");
		panel.add(lblNewLabel);
		
		JLabel lblnom = new JLabel(clientAModifier.getNom()+" --> "+n);
		panel.add(lblnom);
		
		JLabel lblNewLabel_2 = new JLabel("prenom : ");
		panel.add(lblNewLabel_2);
		
		JLabel lblprenom = new JLabel(clientAModifier.getPrenom()+" --> "+p);
		panel.add(lblprenom);
		
		JLabel lblNewLabel_4 = new JLabel("solde : ");
		panel.add(lblNewLabel_4);
		
		String sol = s + " €";
		JLabel lblsolde = new JLabel(clientAModifier.getSolde()+" € --> "+sol);
		panel.add(lblsolde);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(1, 2, 20, 10));
		
		JButton valider = new JButton("valider");
		valider.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				boolean ok = true;
				conf.dispose();
				client.dispose();
				Fen_client.setNb_fen(Fen_client.getNb_fen()-1);
				Client clMod = client_a_modifier.clone();
				clMod.setNom(nom); clMod.setPrenom(prenom); clMod.setSolde(solde); clMod.setPromo(promo);
				clMod.bouton().setText(prenom + " " + nom);
				for(int i = 0; i<Abonnes.getTab().size(); i++){
					boolean idem = client_a_modifier.getPrenom().equals(prenom) && client_a_modifier.getNom().equals(nom);
					if(!idem && nom.equals(Abonnes.getTab().get(i).getNom()) && prenom.equals(Abonnes.getTab().get(i).getPrenom())){
						ok = false;
					}
				}
				if(ok){
					Abonnes.getTab().remove(client_a_modifier);
					Abonnes.ajouter(clMod);
					Connect.modifier(client_a_modifier.getPrenom(), client_a_modifier.getNom(), prenom, nom, promo, solde);
					try {
						Suivi_client.replace(client_a_modifier, clMod);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					fenClient.dispose();
					
					//***********
					Fenetre_principale.getPanel_west().removeAll();
					for(int i = 0; i<Abonnes.getTab().size(); i++){
						Fenetre_principale.getPanel_west().add(Abonnes.getTab().get(i).bouton());
					}
					Fenetre_principale.getPanel_west().revalidate();
					Fenetre_principale.getPanel_west().repaint();
				}
				else{
					JOptionPane.showMessageDialog(null, "La modification n'a pas ete effectuee.\nLe compte : "+prenom+" "+nom+" existe probablement deja.", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				
				//Abonnes.save();
			}
		});
		panel_1.add(valider);
		
		JButton annuler = new JButton("annuler");
		annuler.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				conf.dispose();
				client.getValider().setEnabled(true);
			}
		});
		panel_1.add(annuler);
	}
	
	public static Conf_client conf(Fen_client fenClient, String n, String p, int pr, float s){
			conf = new Conf_client(fenClient, n, p, pr, s);
		return conf;
		}
	
	public static Conf_client conf(Fen_client fenClient,Client clientM, String n, String p, int pr, float s){
		conf = new Conf_client(fenClient, clientM, n, p, pr, s);
	return conf;
	}
}


