package source.vue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import source.Client;
import transaction.vue.Historique_transactions;
import transactions.model.Selection_transaction;
import au.com.bytecode.opencsv.CSVReader;
import bdd.utilisateur.Inscription_model;
import bdd.utilisateur.Utilisateur;
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import javax.swing.JButton;


public class Histo_avance extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane0;
	private String depTot;//en euro
	
	private Client client;
	private Inscription_model info_liste_util;
	private Selection_transaction transactions;
	private JPanel panel_transactions;
	
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JComboBox comboBox_3;
	private JComboBox comboBox_4;
	private JComboBox comboBox_5;
	private JComboBox comboBox_6;
	
	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public Histo_avance(Client cli) throws NumberFormatException, IOException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setVisible(true);
		contentPane0 = new JPanel();
		contentPane0.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane0.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane0);
		
		info_liste_util = new Inscription_model();
		this.client = cli;
		transactions = new Selection_transaction(client, Utilisateur.getId(), "", "");//on initialise le tableau via observeur
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane0.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("historique", null, panel, null);
		//JPanel contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setTitle(client.getPrenom()+" "+client.getNom());
		
		NumberFormat formatter = new DecimalFormat("#0.00");
		String s = formatter.format(client.getDepenseTotal());
		depTot = s;
		
		CSVReader reader = new CSVReader(new FileReader(Utilisateur.dir_historiques + client.getPrenom()+"#"+client.getNom() + ".csv"));
		String [] nextLine;
		
		ArrayList<String> L1 = new ArrayList<String>();
		ArrayList<String> L2 = new ArrayList<String>();
		String date = "";//on stock ici la derniere date lue pour éviter l'affichage redondant
		boolean readEtat = false;//pour autoriser l'affichage de l'état du compte
		//uniquement si il correspond à une nouvelle date
		
	    while ((nextLine = reader.readNext()) != null) {
	        // nextLine[] is an array of values from the line
	    	if(nextLine[0].equals("conso")){
	    		L1.add(nextLine[1]);
	    		L2.add(nextLine[2]);
	    	}
	    	else if(nextLine[0].equals("date")){
	    		if(!nextLine[1].equals(date)){
	    			date = nextLine[1];
		    		readEtat = true;
		    		L1.add(nextLine[0]);
		    		L2.add(nextLine[1]);
	    		}
	    	}
	    	else if(nextLine[0].equals("etat du compte")){
	    		if(readEtat){
	    			readEtat = false;
	    			L1.add(nextLine[0]);
		    		L2.add(nextLine[1]);
	    		}
	    	}
	    	else{
	    		L1.add(nextLine[0]);
	    		L2.add(nextLine[1]);
	    	}
	    }
	    reader.close();
	    
		Object[][] data = new Object[L1.size()][2];
		
		for(int i = 0; i<L1.size(); i++){
			String prem = "", deux = "";
    		
    		prem = L1.get(i);
    		deux = L2.get(i);
    		
    		Object[] action = {prem, deux};
	    	data[i] = action;
		    		
		}
	        //Les titres des colonnes
		String  title[] = {"operation", "montant"};
		panel.setLayout(new BorderLayout(0, 0));
		JTable tableau = new JTable(data, title);
		//On ajoute notre tableau à notre contentPane dans un scroll
		//Sinon les titres des colonnes ne s'afficheront pas ! !	
		JScrollPane scrollPane = new JScrollPane(tableau);
		panel.add(scrollPane);
		//**********************************************************************
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		tabbedPane.addTab("transactions", null, splitPane, null);
		
		JPanel panel_6 = new JPanel();
		splitPane.setLeftComponent(panel_6);
		panel_6.setLayout(new GridLayout(4, 1, 5, 5));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 5, 5));
		
		JLabel label = new JLabel("Lieu de la transaction : ");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(label);
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8);
		
		comboBox = new JComboBox();
		comboBox.addItem("-");
		for(int i=0; i<info_liste_util.getListe_ID_Utilisateurs().size(); i++){
			comboBox.addItem(info_liste_util.getListeUtilisateurs().get(i));
			if(info_liste_util.getListe_ID_Utilisateurs().get(i) == Utilisateur.getId()){
				comboBox.setSelectedItem(info_liste_util.getListeUtilisateurs().get(i));
			}
		}
		panel_8.add(comboBox);
		
		JPanel panel_9 = new JPanel();
		panel_6.add(panel_9);
		panel_9.setLayout(new GridLayout(1, 2, 5, 10));
		
		JLabel label_1 = new JLabel("à partir du : ");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(label_1);
		
		JPanel panel_10 = new JPanel();
		panel_9.add(panel_10);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		panel_10.add(comboBox_1);
		
		JLabel label_2 = new JLabel("/");
		panel_10.add(label_2);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		panel_10.add(comboBox_2);
		
		JLabel label_3 = new JLabel("/");
		panel_10.add(label_3);
		
		comboBox_3 = new JComboBox();
		comboBox_3.addItem("-");//*****************************************************************************
		int year = Calendar.getInstance().get(Calendar.YEAR);
		comboBox_3.addItem(year-1);
		comboBox_3.addItem(year);
		panel_10.add(comboBox_3);
		
		JPanel panel_11 = new JPanel();
		panel_6.add(panel_11);
		panel_11.setLayout(new GridLayout(1, 2, 5, 10));
		
		JLabel label_4 = new JLabel("jusqu'au : ");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_11.add(label_4);
		
		JPanel panel_12 = new JPanel();
		panel_11.add(panel_12);
		
		comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		panel_12.add(comboBox_4);
		
		JLabel label_5 = new JLabel("/");
		panel_12.add(label_5);
		
		comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		panel_12.add(comboBox_5);
		
		JLabel label_6 = new JLabel("/");
		panel_12.add(label_6);
		
		comboBox_6 = new JComboBox();
		comboBox_6.addItem("-");//****************************************************************************************************************************************************
		comboBox_6.addItem(year-1);
		comboBox_6.addItem(year);
		panel_12.add(comboBox_6);
		
		JPanel panel_13 = new JPanel();
		panel_6.add(panel_13);
		
		panel_transactions = new JPanel();
		splitPane.setRightComponent(panel_transactions);
		panel_transactions.setLayout(new BorderLayout(0, 0));
		
		JButton button = new JButton("GO");
		button.addActionListener(new Recherche());
		panel_13.add(button);
		//************************************************************************************
		
		
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setForeground(Color.BLACK);
		tabbedPane.addTab("info", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 2, 5, 5));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		
		JLabel lblNewLabel = new JLabel("dépense totale : ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		JLabel lblDepense = new JLabel(depTot+" €");
		lblDepense.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblDepense);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
	}

	public class Recherche implements ActionListener{

		public Recherche(){
			panel_transactions.removeAll();
			panel_transactions.add(new Historique_transactions(transactions), BorderLayout.CENTER);
			panel_transactions.revalidate();
			panel_transactions.repaint();
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int utilisateur_id = -1;
			String debut = "";
			String fin = "";
			
			if(comboBox.getSelectedIndex()!=0){
				utilisateur_id = info_liste_util.getListe_ID_Utilisateurs().get(comboBox.getSelectedIndex()-1);
			}
			if(comboBox_1.getSelectedIndex()!=0 && comboBox_2.getSelectedIndex()!=0 && comboBox_3.getSelectedIndex()!=0){
				debut = comboBox_3.getSelectedItem().toString()+comboBox_2.getSelectedItem().toString()+comboBox_1.getSelectedItem().toString();
				
			}
			if(comboBox_4.getSelectedIndex()!=0 && comboBox_5.getSelectedIndex()!=0 && comboBox_6.getSelectedIndex()!=0){
				fin = comboBox_6.getSelectedItem().toString()+comboBox_5.getSelectedItem().toString()+comboBox_4.getSelectedItem().toString();
			}
			transactions.recherche(client, utilisateur_id, debut, fin);
			
			panel_transactions.removeAll();
			panel_transactions.add(new Historique_transactions(transactions));
			panel_transactions.revalidate();
			panel_transactions.repaint();
		}
	}
	
	
	
}
