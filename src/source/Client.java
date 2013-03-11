package source;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;

import main.vue.Fenetre_principale;
import model.Facture;
import source.vue.Compt_client;
import au.com.bytecode.opencsv.CSVReader;
import bdd.model.Connect;
import bdd.utilisateur.Utilisateur;


public class Client implements Supprimable {
	
	private int cle_id;
	private String nom;
	private String prenom;
	private int promo;
	private float solde;
	private Facture commande;
	private JButton b;
	private ArrayList<Boisson> conso = new ArrayList<Boisson>();
	private String file_histo;
	private Client client = this;
	
	public Client(int key, String n, String p, float c, int prom){
		prenom = p.toLowerCase();
		nom = n.toLowerCase();
		commande = new Facture(this);
		cle_id = key;
		
		file_histo = Utilisateur.dir_historiques+prenom+"#"+nom+".csv";
		
		NumberFormat formatter = new DecimalFormat("#0.00");
		String s = formatter.format(c);
		float sol = verif_form_float(s);
		
		solde = sol;
		promo = prom;
		/*
		try {
			Suivi_client.init(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		b = new JButton(prenom + " " + nom);
		b.setHorizontalAlignment(JButton.LEFT);
		b.setBackground(Color.white);
		boutMaJ();
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Compt_client.cp_client(client);
			}
			
		});
	}
	
	public void ajouterConso(Boisson ing){
		conso.add(ing);
		Connect.debiter_conso(this, ing);
		float cmp = solde - ing.getPrix();
		NumberFormat formatter = new DecimalFormat("#0.00");
		String s = formatter.format(cmp);
		float k = verif_form_float(s);
		solde = k;
		try {
			Suivi_client.enreg_transaction(this, ing);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public JButton bouton(){
		return b;
	}
	
	public String toString(){
		String s = nom + " " + prenom + " " + solde;
		return s;
	}
	
	public void ajouter(Boisson c){
		conso.add(c);
		
	}

	
	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public float getSolde() {
		return solde;
	}


	public void setSolde(float solde) {
		this.solde = solde;
	}


	public ArrayList<Boisson> getConso() {
		return conso;
	}

	public int getPromo() {
		return promo;
	}


	public void setPromo(int promo) {
		this.promo = promo;
	}


	public void setConso(ArrayList<Boisson> conso) {
		this.conso = conso;
	}
	
	public int getCle_id() {
		return cle_id;
	}

	public void setCle_id(int cleId) {
		cle_id = cleId;
	}
	
	public boolean equalsKey(Client c){
		return cle_id == c.getCle_id();
	}

	public Client clone(){
		Client cl = new Client(cle_id, nom, prenom, solde, promo);
		cl.setConso(this.conso);
		return cl;
	}
	
	public void boutMaJ(){
		if(this.solde < 0){
			b.setBackground(Color.RED);
		}
		else{b.setBackground(Color.white);}
	}
	
	public static float verif_form_float(String txt){
		
		float nb = 0;
		String deg_tmp = "";
		NumberFormat formatter = new DecimalFormat("#0.00");
		String txt2 = suppr_virgule(txt);
		String s = formatter.format(Float.valueOf(txt2).floatValue());
		char[] num = s.toCharArray();
		for(int i=0; i<num.length; i++){
			if(i == 0 && num[0]=='-'){
				deg_tmp = "-";
			}
			else if(num[i]=='0'||num[i]=='1'||num[i]=='2'||num[i]=='3'||num[i]=='4'||num[i]=='5'||num[i]=='6'||num[i]=='7'||num[i]=='8'||num[i]=='9'){
				deg_tmp = deg_tmp + String.valueOf(num[i]);
			}
			else if(num[i]==','||num[i]=='.'){deg_tmp = deg_tmp + ".";}
			
		}
			
			nb = Float.valueOf(deg_tmp).floatValue();

		return nb;
	}
	
	public static String suppr_virgule(String txt){
		String nb = "";
		
		String deg_tmp = "";
		
		char[] num = txt.toCharArray();
		for(int i=0; i<num.length; i++){
			if(num[i]==','||num[i]=='.'){deg_tmp = deg_tmp + ".";}
			else{deg_tmp = deg_tmp + String.valueOf(num[i]);}
			}
		
		nb = deg_tmp;
		
		return nb;
	}
	
	public float prix(){
		float pri = 0;
		for(int i = 0; i<conso.size(); i++){
			pri = pri + conso.get(i).getPrix();
		}
		return pri;
	}
	
	public float volum(){
		float vol = 0;
		for(int i = 0; i<conso.size(); i++){
			vol = vol + conso.get(i).getVolum();
		}
		return vol;//volume en cL
	}
	
	public float alcool(){
		float alc = 0;
		for(int i = 0; i<this.getConso().size(); i++){
			alc = alc + this.getConso().get(i).getVolum()*this.getConso().get(i).getDeg()/125;
		}
		return alc;//retourne le nombre d'unites d'alcool
	}


	@Override
	public void supprimer() {
		// TODO Auto-generated method stub
		Fenetre_principale.getPanel_west().removeAll();
		Fenetre_principale.getPanel_west().repaint();
		
		new File(file_histo).delete();
		
		Connect.supprimer(this);
		Abonnes.supprimer(this);
		if(Compt_client.getCompt()!=null){
			Compt_client.getCompt().fermer();
		}
	}
	
	public float getDepenseTotal() throws NumberFormatException, IOException{
		CSVReader reader = new CSVReader(new FileReader(file_histo));
	    String [] nextLine;
	    float depense = 0;
	    while ((nextLine = reader.readNext()) != null) {
	        // nextLine[] is an array of values from the line
	    	if(nextLine[0].equals("credit :") && Float.valueOf(nextLine[1]).floatValue()<0){
	    		depense += -Float.valueOf(nextLine[1]).floatValue();
	    	}
	    	else if(nextLine[0].equals("conso")){
	    		depense = depense + Float.valueOf(nextLine[2]).floatValue();
	    	}
	    }
	    return depense;//depense totale en euro
	}
	public float getVolumTotal() throws NumberFormatException, IOException{
		CSVReader reader = new CSVReader(new FileReader(file_histo));
	    String [] nextLine;
	    float bu = 0;
	    while ((nextLine = reader.readNext()) != null) {
	        // nextLine[] is an array of values from the line
	    	if(nextLine[0].equals("conso")){
	    		bu = bu + Float.valueOf(nextLine[4]).floatValue()/100;
	    	}
	    }
	    return bu;//volume total en Litres (nextLine[2] est en cL)
	}
	public float getMoyDegTotal() throws NumberFormatException, IOException{
		CSVReader reader = new CSVReader(new FileReader(file_histo));
	    String [] nextLine;
	    float deg = 0;
	    while ((nextLine = reader.readNext()) != null) {
	        // nextLine[] is an array of values from the line
	    	if(nextLine[0].equals("conso")){
	    		deg = deg + Float.valueOf(nextLine[3]).floatValue()*Float.valueOf(nextLine[4]).floatValue()/100;
	    	}
	    }
	    float moy = deg/getVolumTotal();
	    return moy;
	}

	public Facture getCommande() {
		return commande;
	}

	public void setCommande(Facture commande) {
		this.commande = commande;
	}
	
}
