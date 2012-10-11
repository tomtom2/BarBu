package main.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import source.Abonnes;
import source.Ardoise;
import source.Client;
import source.Close_conf;
import source.DebiteurListener;
import source.MotDePass;
import source.ResultsNB;
import source.SelectJTF;
import source.fenetre_type.Fenetre_chainee;
import source.vue.Fen_client;
import source.vue.Fen_conso;
import suppression.Fen_select_suppr;
import bdd.utilisateur.Utilisateur;
import bdd.vue.AdressBDD;
import bdd.vue.Fen_BDD;
import droit.model.Liste_Utilisateurs;
import droits.vue.Fen_Liste_utilisateurs;



public class Fenetre_principale extends Fenetre_chainee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private static Fenetre_principale fenP;
	private JPanel contentPane;
	private static JTextField textField_nom;
	
	private static JCheckBox chckOpt, chckProm;
	private static JRadioButton rdbt_prenom, rdbt_nom;
	private static JSpinner spinner;
	private static SpinnerNumberModel model = new SpinnerNumberModel(new Integer(2014), null, null, new Integer(1));
	
	private JScrollPane scroll_west;
	private static JPanel panel_west = new JPanel();
	private static JPanel recherchePane;
	private static ArrayList<JButton> bout_liste = new ArrayList<JButton>();
	
	
	
	private static Utilisateur asso;
	
	/**
	 * Create the frame.
	 */
	public Fenetre_principale() {
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		asso = new Utilisateur();
		
		setTitle(asso.getNom());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		
		//j'ai un doute sur ce coup là...  :s
		/*try {
			Ardoise.init(Utilisateur.fichier_types, Utilisateur.fichier_conso);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//***********************************
		
		for(int i = 0; i < Abonnes.getTab().size(); i++){
			bout_liste.add(Abonnes.getTab().get(i).bouton());
		}
		
		JMenuBar menuBar = new JMenuBar();
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		JMenu Compt = new JMenu("Compte");
		Compt.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(Compt);
		if(!Liste_Utilisateurs.supperieurA(Utilisateur.droit, "utilisateur_admin")){
			Compt.setEnabled(false);
		}
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Ajouter");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fen_client.fen_client();
			}
		});
		Compt.add(mntmNewMenuItem);
		
		JMenuItem mntmSuppr = new JMenuItem("Supprimer");
		mntmSuppr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Fen_select_suppr();
			}
		});
		//Compt.add(mntmSuppr);
		
		JMenuItem mntmLister = new JMenuItem("Lister");
		mntmLister.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				panel_west.removeAll();
				for(int i = 0; i < Abonnes.getTab().size(); i++){
					Client cl = Abonnes.getTab().get(i);
					cl.bouton().setHorizontalAlignment(JButton.LEFT);
					JButton b = cl.bouton();
					panel_west.add(b);
					panel_west.revalidate();
					panel_west.repaint();
			}
			}
			
		});
		Compt.add(mntmLister);
		
		JMenu mnBoissons = new JMenu("Ingrediants");
		mnBoissons.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnBoissons);
		
		JMenuItem mntmAjouter = new JMenuItem("Ajouter");
		mntmAjouter.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Fen_conso.fen_conso();
			}
			
		});
		mnBoissons.add(mntmAjouter);
		
		JMenuItem mntmLister_1 = new JMenuItem("Lister");
		mntmLister_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Ardoise();
			}
			
		});
		mnBoissons.add(mntmLister_1);
		
		JMenu mnAPropo = new JMenu("infos");
		mnAPropo.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnAPropo);
		
		JMenuItem mntmFoy = new JMenuItem(asso.getNom());
		mnAPropo.add(mntmFoy);
		
		JMenuItem mntmDebiteurs = new JMenuItem("debiteurs");
		mnAPropo.add(mntmDebiteurs);
		
		JMenu mnNewMenu = new JMenu("BDD");
		//mnAPropo.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("gestion du contenu");
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Fen_BDD();
			}
			
		});
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("gestion de la connexion");
		mnNewMenu.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AdressBDD();
			}
			
		});
		
		mntmDebiteurs.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new DebiteurListener();
			}
			
		});
		
		JMenuItem mntmMaj = new JMenuItem("MaJ");
		mntmMaj.setHorizontalAlignment(SwingConstants.CENTER);
		//contentPane.addContainerListener(panel_west);
		mntmMaj.addActionListener(new MiseAJour());
		menuBar.add(mntmMaj);
		
		JMenuItem lock = new JMenuItem("lock");
		lock.setHorizontalAlignment(SwingConstants.RIGHT);
		lock.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//setEnabled(false);
				new MotDePass(asso.getNom());
			}
			
		});
		menuBar.add(lock);
		
		JMenu mnAdmin = new JMenu("administrer");
		menuBar.add(mnAdmin);
		
		if(!Liste_Utilisateurs.supperieurA(Utilisateur.droit, "admin")){
			mnAdmin.setEnabled(false);
		}
		
		JMenuItem mntmUtil = new JMenuItem("Utilisateurs");
		mntmUtil.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Fen_Liste_utilisateurs();
			}
			
		});
		
		mnAdmin.add(mntmUtil);
		mnAdmin.add(mnNewMenu);
		mnAdmin.add(mntmSuppr);
		
		JPanel recherche_client = new JPanel();
		//recherche_client.setPreferredSize(new Dimension(300, getHeight()));
		//contentPane.add(recherche_client, BorderLayout.CENTER);
		recherche_client.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel cherche = new JPanel();
		recherche_client.add(cherche);
		cherche.setLayout(new GridLayout(2, 2, 5, 5));
		
		JPanel recherche_pan = new JPanel();
		cherche.add(recherche_pan);
		recherche_pan.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNom = new JLabel("Recherche :");
		recherche_pan.add(lblNom);
		
		textField_nom = new JTextField();
		recherche_pan.add(textField_nom);
		textField_nom.setColumns(10);
		textField_nom.addKeyListener(new Recherche());
		textField_nom.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				int code = arg0.getKeyCode();
				
				if(arg0.isControlDown() && code == KeyEvent.VK_B){
					new Fen_BDD();
					new AdressBDD();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		textField_nom.addFocusListener(new SelectJTF(textField_nom));
		
		JPanel option_pan = new JPanel();
		cherche.add(option_pan);
		option_pan.setLayout(new GridLayout(0, 3, 0, 0));
		
		chckOpt = new JCheckBox("Options :");
		option_pan.add(chckOpt);
		chckOpt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(chckOpt.isSelected()){
					rdbt_prenom.setEnabled(true);
					rdbt_nom.setEnabled(true);
					chckProm.setEnabled(true);
					//spinner.setEnabled(true);
					
				}
				else{
					rdbt_prenom.setEnabled(false);
					rdbt_nom.setEnabled(false);
					rdbt_prenom.setSelected(true);
					rdbt_nom.setSelected(true);
					chckProm.setEnabled(false);
					chckProm.setSelected(false);
					spinner.setEnabled(false);
				}
			}
			
		});
		
		JPanel pan_nom_prenom = new JPanel();
		option_pan.add(pan_nom_prenom);
		pan_nom_prenom.setLayout(new GridLayout(2, 1, 0, 0));
		
		rdbt_prenom = new JRadioButton("prenom");
		pan_nom_prenom.add(rdbt_prenom);
		rdbt_prenom.setSelected(true);
		rdbt_prenom.setEnabled(false);
		
		rdbt_nom = new JRadioButton("nom");
		pan_nom_prenom.add(rdbt_nom);
		rdbt_nom.setSelected(true);
		rdbt_nom.setEnabled(false);
		
		JPanel pan_promo = new JPanel();
		option_pan.add(pan_promo);
		pan_promo.setLayout(new GridLayout(2, 1, 0, 0));
		
		chckProm = new JCheckBox("promo :");
		pan_promo.add(chckProm);
		chckProm.setSelected(false);
		chckProm.setEnabled(false);
		chckProm.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(chckProm.isSelected()){
					spinner.setEnabled(true);
				}
				else{
					spinner.setEnabled(false);
				}
			}
			
		});
		
		spinner = new JSpinner();
		spinner.setModel(model);
		pan_promo.add(spinner);
		spinner.setEnabled(false);
		
		recherchePane = new JPanel();
		JScrollPane resultat_liste = new JScrollPane(recherchePane);
		recherchePane.setLayout(new GridLayout(0, 1, 5, 5));
		recherche_client.add(resultat_liste);
		
		scroll_west = new JScrollPane(panel_west);
		//panel_west.setPreferredSize(new Dimension(300, getHeight()));
		panel_west.setLayout(new GridLayout(0, 1, 5, 5));
		scroll_west.setPreferredSize(new Dimension(120, getHeight()));
		//liste_west.add(panel_west);
		//contentPane.add(scroll_west, BorderLayout.WEST);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll_west, recherche_client);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		this.addWindowListener(new WinClose());
		
	}
	

	public static Fenetre_principale getFenetre_principale(){
		
		if(fenP == null){
			fenP = new Fenetre_principale();
		}
		
		System.out.println(Utilisateur.dir_private);
		return fenP;
	}
	
	public static void affichage_MaJ(){
		recherchePane.removeAll();
		recherchePane.revalidate();
		recherchePane.repaint();
		
		panel_west.removeAll();
		for(int i = 0; i < Abonnes.getTab().size(); i++){
			Client cl = Abonnes.getTab().get(i);
			cl.bouton().setHorizontalAlignment(JButton.LEFT);
			JButton b = cl.bouton();
			panel_west.add(b);
			panel_west.revalidate();
			panel_west.repaint();
			}
	}
	
	public static JTextField getTextField_nom() {
		return textField_nom;
	}

	public static JPanel getPanel_west() {
		return panel_west;
	}

	public void lister_tout(){
		for(int i = 0; i < Abonnes.getTab().size(); i++){
			Client cl = Abonnes.getTab().get(i);
			String str = cl.getPrenom() + " " + cl.getNom();
			JButton bouton = new JButton(str);
			panel_west.add(bouton);
		}
	}
	
	public static class MiseAJour implements ActionListener{

		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			/*
			panel_west.add(new JButton("bouton !!!"));
			panel_west.revalidate();
			*/
			
			clear();
			
			for(int i = 0; i < Abonnes.getTab().size(); i++){
				Client cl = Abonnes.getTab().get(i);
				cl.bouton().setHorizontalAlignment(JButton.LEFT);
				JButton b = cl.bouton();
				panel_west.add(b);
				panel_west.revalidate();
				panel_west.repaint();
				}
			
			
	}
		
		public static void clear(){
			recherchePane.removeAll();
			recherchePane.revalidate();
			recherchePane.repaint();
			
			textField_nom.setText("");
			
			panel_west.removeAll();
			panel_west.revalidate();
			panel_west.repaint();
		}

	}
	
	public static class Recherche implements KeyListener {

		private static ArrayList<JButton> bout_liste;
		private String seq_nom = "";
		private boolean prom = true;
		
		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			bout_liste = new ArrayList<JButton>();
			seq_nom = textField_nom.getText();
			
			
			for(int i =0; i<Abonnes.getTab().size(); i++){
				Client cl = Abonnes.getTab().get(i);
				String id = "";
				if(rdbt_prenom.isSelected() && rdbt_nom.isSelected()){
					id = cl.getPrenom()+" "+cl.getNom();
				}
				else if(rdbt_prenom.isSelected() && !rdbt_nom.isSelected()){
					id = cl.getPrenom();
				}
				else if(!rdbt_prenom.isSelected() && rdbt_nom.isSelected()){
					id = cl.getNom();
				}
				if(chckProm.isSelected()){
					prom = cl.getPromo()==(model.getNumber().intValue());
				}
				else{
					prom = true;
				}
				if(id.indexOf(seq_nom)>=0 && prom){
					bout_liste.add(cl.bouton());
					recherchePane.removeAll();
					recherchePane.revalidate();
					recherchePane.repaint();
				}
			}
			if(!bout_liste.isEmpty()){
				for(int i = 0; i<bout_liste.size(); i++){
					bout_liste.get(i).setHorizontalAlignment(JButton.CENTER);
					recherchePane.add(bout_liste.get(i));
					recherchePane.revalidate();
					recherchePane.repaint();
				}
			}
			else{
				recherchePane.removeAll();
				recherchePane.revalidate();
				recherchePane.repaint();
			}
			int code = arg0.getKeyCode();
			switch (code){
			case KeyEvent.VK_F1 :
				ResultsNB.getNB(bout_liste.size());
				break;
			}
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		public static ArrayList<JButton> getBout_liste() {
			return bout_liste;
		}

		public static void setBout_liste(ArrayList<JButton> boutListe) {
			bout_liste = boutListe;
		}
		
		
		
	}
	
	public static void Exit(){
		System.exit(0);
	}
	
	public class WinClose implements WindowListener {

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub
			updateObservateur();
			new Close_conf();
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

}
