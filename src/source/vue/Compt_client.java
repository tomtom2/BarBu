package source.vue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import bdd.utilisateur.Utilisateur;

import main.vue.Fenetre_principale;
import source.Client;
import source.CreditListener;
import source.fenetre_type.Fenetre_chainee;
import source.fenetres.interfaces.ObservateurChainage;
import vue.Facture_IHM;
import droit.model.Liste_Utilisateurs;


public class Compt_client extends Fenetre_chainee implements ActionListener, WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nom;
	private String prenom;
	private float solde;
	private Client cli;
	
	private static Compt_client compt;
	private Fenetre_principale mere = Fenetre_principale.getFenetre_principale();
	
	private Compt_client(Client client){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 200);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("promo "+client.getPromo());
		
		cli = client;
		nom = client.getNom();
		prenom = client.getPrenom();
		solde = client.getSolde();
		client.getConso();
		
		mere.addObservateurChainage(new ObservateurChainage(){

			@Override
			public void update() {
				// TODO Auto-generated method stub
				fermer();
			}
			
		});
		
		getContentPane().setLayout(new BorderLayout(5, 5));
		
		JMenuBar menuBar = new JMenuBar();
		getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mnNewMenu = new JMenu("compte");
		menuBar.add(mnNewMenu);
		/*if(!Liste_Utilisateurs.supperieurA(Utilisateur.droit, "utilisateur_admin")){
			mnNewMenu.setEnabled(false);
		}*/
		
		JMenuItem modif = new JMenuItem("modifier");
		modif.addActionListener(new ModifListener());
		mnNewMenu.add(modif);
		
		JMenuItem suppr = new JMenuItem("supprimer");
		suppr.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Fen_suppr(cli);
			}
			
		});
		mnNewMenu.add(suppr);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Nom :");
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(nom);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Prenom :");
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(prenom);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Solde :");
		panel.add(lblNewLabel_4);
		
		String s = ""+solde;
		JLabel lblNewLabel_5 = new JLabel(s);
		panel.add(lblNewLabel_5);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new GridLayout(0, 1, 5, 5));
		
		JButton credit = new JButton("crediter");
		//credit.addActionListener(new CreditListener(cli));
		credit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CreditListener.credit(cli);
			}
			
		});
		panel_1.add(credit);
		
		
		JButton consomation = new JButton("conso");
		consomation.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Cherche_ingredient.affiche_ard(cli);
				new Facture_IHM(cli);
			}
			
		});
		panel_1.add(consomation);
		
		JButton histo = new JButton("historique");
		histo.setBackground(Color.WHITE);
		histo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					new Historique(cli);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		panel_1.add(histo);
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static Compt_client cp_client(Client client){
		//ne doit pas se declancher si client a deja sa fenetre compt ouverte !!!
		if(getCompt() != null){getCompt().dispose();}
			setCompt(new Compt_client(client));
			compt.updateObservateur();
		return getCompt();
	}
	
	public void fermer(){
		dispose();
		updateObservateur();
	}
	
	@Override
	public void updateObservateur() {
		// TODO Auto-generated method stub
		for(ObservateurChainage obs : this.getListObservateur() ){
			obs.update();
		}
	}
	
	public Compt_client getCurrentObject(){
		return this;
	}
	
	public static void setCompt(Compt_client compt) {
		Compt_client.compt = compt;
	}

	public static Compt_client getCompt() {
		return compt;
	}

	class ModifListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Fen_client.fen_client(getCompt(), cli);
		}
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		Fenetre_principale.getTextField_nom().requestFocus();
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		dispose();
		updateObservateur();
		Fenetre_principale.getTextField_nom().requestFocus();
		System.out.println("fermeture");
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
