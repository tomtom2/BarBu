package source.vue;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import source.Client;


public class Fen_client extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField jtf = new JTextField("");
	private SpinnerNumberModel model;
	
	private static Fen_client fen_client;
	private Compt_client compt_cli;
	private static int nb_fen = 0;
	private JButton valider;
	private JButton modifier;
	private JButton annuler;
	
	private Client client;
	private JLabel lblNewLabel;
	private JSpinner spinner;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	private Fen_client() {
		setAlwaysOnTop(false);
		
		setSize(220, 165);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		setResizable(false);
		setVisible(true);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 2, 5, 5));
		
		JLabel nom = new JLabel("nom");
		panel.add(nom);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("prenom");
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("solde");
		panel.add(lblNewLabel_2);
		
		panel.add(jtf);
		jtf.setColumns(10);
		
		valider = new JButton("valider");
		valider.addActionListener(new Valider());
		
		lblNewLabel = new JLabel("promo");
		panel.add(lblNewLabel);
		
		model = new SpinnerNumberModel(new Integer(2014), null, null, new Integer(1));
		spinner = new JSpinner(model);
		panel.add(spinner);
		panel.add(valider);
		
		annuler = new JButton("annuler");
		annuler.addActionListener(new Annuler());
		panel.add(annuler);
	}
	
	private Fen_client(Compt_client compt_client, Client client) {
		
		this.compt_cli = compt_client;
		this.client = client;
		
		setAlwaysOnTop(false);
		setSize(220, 165);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		setResizable(false);
		setVisible(true);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 2, 5, 5));
		
		JLabel nom = new JLabel("nom");
		panel.add(nom);
		
		textField = new JTextField(client.getNom());
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("prenom");
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField(client.getPrenom());
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("solde");
		panel.add(lblNewLabel_2);
		
		jtf.setText("" + client.getSolde());
		panel.add(jtf);
		jtf.setColumns(10);
		
		lblNewLabel = new JLabel("promo");
		panel.add(lblNewLabel);
		
		int prom = client.getPromo();
		
		model = new SpinnerNumberModel(prom, null, null, new Integer(1));
		spinner = new JSpinner(model);
		panel.add(spinner);
		
		modifier = new JButton("modifier");
		modifier.addActionListener(new Modifier());
		panel.add(modifier);
		
		annuler = new JButton("annuler");
		annuler.addActionListener(new Annuler());
		panel.add(annuler);
	}
	
	public static Fen_client fen_client(){
		if(nb_fen == 0){
			fen_client = new Fen_client();
			nb_fen++;
		}
		return fen_client;
	}
	
	public static Fen_client fen_client(Compt_client compt_client, Client client){
		if(nb_fen == 0){
			fen_client = new Fen_client(compt_client, client);
			nb_fen++;
		}
		return fen_client;
	}
	
	public class Valider extends JFrame implements ActionListener{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String nom;
		private String prenom;
		private int prom;
		private float solde = 0;
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int nb_sep = 0;//nombre de séparateurs
			boolean ok = true;
			prom = model.getNumber().intValue();
			nom = textField.getText();//.replace(' ', '_');
			prenom = textField_1.getText();//.replace(' ', '_');
			if(jtf.getText().equals("")){
				Conf_client.conf(fen_client, nom, prenom, prom, 0);
		        valider.setEnabled(false);
			}
			else{
			    String solde_tmp = "";
			    char[] num = jtf.getText().toCharArray();
			    for(int i=0; i<num.length; i++){
				    if(num[1]=='-'){
					    solde_tmp = "-";
				    }
				    if(num[i]=='0'||num[i]=='1'||num[i]=='2'||num[i]=='3'||num[i]=='4'||num[i]=='5'||num[i]=='6'||num[i]=='7'||num[i]=='8'||num[i]=='9'){
					    solde_tmp = solde_tmp + String.valueOf(num[i]);
				    }
				    else if(num[i]==','||num[i]=='.'){nb_sep++; solde_tmp = solde_tmp + ".";}
				    else{JOptionPane.showMessageDialog(null, "Le solde est un nombre composé de chiffres (0,1,2,3,4,5,6,7,8,9)\net eventuellement d'un point (.)\nou encore d'une virgule (,)\nmerci !", "Erreur", JOptionPane.ERROR_MESSAGE);
				    ok = false;}
			    }
			
		        if(nb_sep>1){JOptionPane.showMessageDialog(null, "tu y vas un peu fort sur les virgules, non?", "Erreur", JOptionPane.ERROR_MESSAGE);
			    ok = false;}
		    
			    if(ok){
				    solde = Float.valueOf(solde_tmp).floatValue();
			        Conf_client.conf(fen_client, nom, prenom, prom, solde);
			        valider.setEnabled(false);}
			    else{nb_sep = 0;
			        ok = true;
			        nom = "";
			        prenom = "";
			        solde_tmp = "";
			        }
			}
		}
		
	}
	
	public class Modifier extends JFrame implements ActionListener{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String nom;
		private String prenom;
		private int promo;
		private float solde = 0;
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int nb_sep = 0;//nombre de séparateurs
			boolean ok = true;
			//promo = "P" + model.getNumber().intValue();
			nom = textField.getText();
			prenom = textField_1.getText();
			promo = model.getNumber().intValue();
			String solde_tmp = "";
			char[] num = jtf.getText().toCharArray();
			for(int i=0; i<num.length; i++){
				
				if(num[i]=='0'||num[i]=='1'||num[i]=='2'||num[i]=='3'||num[i]=='4'||num[i]=='5'||num[i]=='6'||num[i]=='7'||num[i]=='8'||num[i]=='9'){
					solde_tmp = solde_tmp + String.valueOf(num[i]);
				}
				else if(i == 0 && num[i] == '-'){
					solde_tmp = solde_tmp + String.valueOf(num[i]);
				}
				else if(num[i]==','||num[i]=='.'){nb_sep++; solde_tmp = solde_tmp + ".";}
				else{JOptionPane.showMessageDialog(null, "Le solde est un nombre composé de chiffres (0,1,2,3,4,5,6,7,8,9)\net eventuellement d'un point (.)\nou encore d'une virgule (,)\nmerci !", "Erreur", JOptionPane.ERROR_MESSAGE);
				ok = false;}
			}
			
		    if(nb_sep>1){JOptionPane.showMessageDialog(null, "tu y vas un peu fort sur les virgules, non?", "Erreur", JOptionPane.ERROR_MESSAGE);
			ok = false;}
		    
			if(ok){
				solde = Float.valueOf(solde_tmp).floatValue();
			    Conf_client.conf(fen_client, client, nom, prenom, promo, solde);
			    compt_cli.dispose();
			    //valider.setEnabled(false);
			    }
			else{nb_sep = 0;
			    ok = true;
			    nom = "";
			    prenom = "";
			    solde_tmp = "";
			    }
		}
		
	}
	
	public class Annuler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			fen_client.dispose();
			nb_fen--;
		}
		
	}
	public static int getNb_fen() {
		return nb_fen;
	}

	public static void setNb_fen(int nbFen) {
		nb_fen = nbFen;
	}
	
	public JButton getValider(){
		return valider;
	}
	
}
