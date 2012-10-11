package source.vue;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import source.Ardoise;
import source.Ardoise_client;
import source.Boisson;
import source.Bout_client;
import source.Client;
import source.SelectJTF;
import source.fenetre_type.Fenetre_chainee;


public class Cherche_ingredient extends Fenetre_chainee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private static JPanel affichePane = new JPanel();
	private static JTextField textField_nom;
	private static JComboBox comboBox;
	private static int nb_fen = 0;
	
	private Client client;
	private static Cherche_ingredient rech;

	/**
	 * Create the frame.
	 */
	public Cherche_ingredient(Client cli) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 310, 450);
		setVisible(true);
		setLocationRelativeTo(null);
		client = cli; rech = this;
		setTitle(client.getPrenom()+" "+client.getNom());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		/*JMenuItem mntmFermer = new JMenuItem("Fermer");
		mntmFermer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nb_fen--;
				dispose();
				
			}
			
		});
		menuBar.add(mntmFermer);*/
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Ardoise");
		mntmNewMenuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Ardoise_client.affiche_ard(client);
			}
			
		});
		menuBar.add(mntmNewMenuItem);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_recherche = new JPanel();
		panel.add(panel_recherche, BorderLayout.NORTH);
		panel_recherche.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel lblNewLabel = new JLabel("conso : ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel_recherche.add(lblNewLabel);
		
		textField_nom = new JTextField();
		panel_recherche.add(textField_nom);
		textField_nom.setColumns(10);
		textField_nom.addKeyListener(new Recherche());
		textField_nom.addFocusListener(new SelectJTF(textField_nom));
		
		JLabel lblNewLabel_1 = new JLabel("type : ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_recherche.add(lblNewLabel_1);
		
		comboBox = new JComboBox();
		comboBox.addItem("All");
		comboBox.addItem("blanche");
		comboBox.addItem("blonde");
		comboBox.addItem("ambree");
		comboBox.addItem("brune");
		comboBox.addItem("autre");
		comboBox.setSelectedItem("All");
		comboBox.addActionListener(new Recherche());
		panel_recherche.add(comboBox);
		
		scrollPane = new JScrollPane(affichePane);
		affichePane.setLayout(new GridLayout(0, 1, 2, 2));
		//pour ne rien conserver dans le panneau d'affichage entre 2 commandes...
		affichePane.removeAll();
		affichePane.revalidate();
		affichePane.repaint();
		//************************************************************************
		panel.add(scrollPane, BorderLayout.CENTER);
	}
	
	public static Cherche_ingredient affiche_ard(Client cli){
		if(nb_fen !=0){rech.dispose(); nb_fen--;}
		rech = new Cherche_ingredient(cli);
		nb_fen++;
		return rech;
	}
	
	public class Recherche implements KeyListener, ActionListener {

		private ArrayList<Bout_client> bout_liste;
		private String seq_nom;
		
		public Recherche(){
			
		}
		
		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
			bout_liste = new ArrayList<Bout_client>();
			seq_nom = textField_nom.getText();
			
			if(comboBox.getSelectedItem().toString().equals("All")){
				for(int i =0; i<Ardoise.getTab().size(); i++){
					
					Boisson boi = Ardoise.getTab().get(i);
					if(boi.getNom().startsWith(seq_nom)){
						Bout_client bout = new Bout_client(client, Ardoise.getTab().get(i));
						bout_liste.add(bout);
					}
				}
			}
			else if(comboBox.getSelectedItem().toString().equals("blanche")){
				for(int i =0; i<Ardoise.getBlanches().size(); i++){
					Boisson boi = Ardoise.getBlanches().get(i);
					if(boi.getNom().startsWith(seq_nom)){
						System.out.println("blanches : "+boi.getNom());
						Bout_client bout = new Bout_client(client, boi);
						System.out.println("bouton : "+bout.getText());
						bout_liste.add(bout);
					}
				}
			}else if(comboBox.getSelectedItem().toString().equals("blonde")){
				for(int i =0; i<Ardoise.getBlondes().size(); i++){
					Boisson boi = Ardoise.getBlondes().get(i);
					if(boi.getNom().startsWith(seq_nom)){
						Bout_client bout = new Bout_client(client, boi);
						bout_liste.add(bout);
					}
				}
			}else if(comboBox.getSelectedItem().toString().equals("ambree")){
				for(int i =0; i<Ardoise.getAmbrees().size(); i++){
					Boisson boi = Ardoise.getAmbrees().get(i);
					if(boi.getNom().startsWith(seq_nom)){
						Bout_client bout = new Bout_client(client, boi);
						bout_liste.add(bout);
					}
				}
			}else if(comboBox.getSelectedItem().toString().equals("brune")){
				for(int i =0; i<Ardoise.getBrunes().size(); i++){
					Boisson boi = Ardoise.getBrunes().get(i);
					if(boi.getNom().startsWith(seq_nom)){
						Bout_client bout = new Bout_client(client, boi);
						bout_liste.add(bout);
					}
				}
			}else if(comboBox.getSelectedItem().toString().equals("autre")){
				for(int i =0; i<Ardoise.getAutre().size(); i++){
					Boisson boi = Ardoise.getAutre().get(i);
					if(boi.getNom().indexOf(seq_nom)==0){
						Bout_client bout = new Bout_client(client, boi);
						bout_liste.add(bout);
					}
				}
			}
			System.out.println("bout_liste.size() : "+bout_liste.size());
			affichePane.removeAll();
			affichePane.revalidate();
			affichePane.repaint();
			
			for(int i = 0; i<bout_liste.size(); i++){
				bout_liste.get(i).setHorizontalAlignment(JButton.LEFT);
				affichePane.add(bout_liste.get(i));
			}
			affichePane.revalidate();
			affichePane.repaint();
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			bout_liste = new ArrayList<Bout_client>();
			seq_nom = textField_nom.getText();
			
			if(comboBox.getSelectedItem().toString().equals("All")){
				for(int i =0; i<Ardoise.getTab().size(); i++){
					
					Boisson boi = Ardoise.getTab().get(i);
					if(boi.getNom().startsWith(seq_nom)){
						Bout_client bout = new Bout_client(client, Ardoise.getTab().get(i));
						bout_liste.add(bout);
					}
				}
			}
			else if(comboBox.getSelectedItem().toString().equals("blanche")){
				for(int i =0; i<Ardoise.getBlanches().size(); i++){
					Boisson boi = Ardoise.getBlanches().get(i);
					if(boi.getNom().startsWith(seq_nom)){
						System.out.println("blanches : "+boi.getNom());
						Bout_client bout = new Bout_client(client, boi);
						System.out.println("bouton : "+bout.getText());
						bout_liste.add(bout);
					}
				}
			}else if(comboBox.getSelectedItem().toString().equals("blonde")){
				for(int i =0; i<Ardoise.getBlondes().size(); i++){
					Boisson boi = Ardoise.getBlondes().get(i);
					if(boi.getNom().startsWith(seq_nom)){
						Bout_client bout = new Bout_client(client, boi);
						bout_liste.add(bout);
					}
				}
			}else if(comboBox.getSelectedItem().toString().equals("ambree")){
				for(int i =0; i<Ardoise.getAmbrees().size(); i++){
					Boisson boi = Ardoise.getAmbrees().get(i);
					if(boi.getNom().startsWith(seq_nom)){
						Bout_client bout = new Bout_client(client, boi);
						bout_liste.add(bout);
					}
				}
			}else if(comboBox.getSelectedItem().toString().equals("brune")){
				for(int i =0; i<Ardoise.getBrunes().size(); i++){
					Boisson boi = Ardoise.getBrunes().get(i);
					if(boi.getNom().startsWith(seq_nom)){
						Bout_client bout = new Bout_client(client, boi);
						bout_liste.add(bout);
					}
				}
			}else if(comboBox.getSelectedItem().toString().equals("autre")){
				for(int i =0; i<Ardoise.getAutre().size(); i++){
					Boisson boi = Ardoise.getAutre().get(i);
					if(boi.getNom().indexOf(seq_nom)==0){
						Bout_client bout = new Bout_client(client, boi);
						bout_liste.add(bout);
					}
				}
			}
			System.out.println("bout_liste.size() : "+bout_liste.size());
			affichePane.removeAll();
			affichePane.revalidate();
			affichePane.repaint();
			
			for(int i = 0; i<bout_liste.size(); i++){
				bout_liste.get(i).setHorizontalAlignment(JButton.LEFT);
				affichePane.add(bout_liste.get(i));
			}
			affichePane.revalidate();
			affichePane.repaint();
		}
	}

	public static void fermer() {
		// TODO Auto-generated method stub
		rech.dispose();
	}
}
