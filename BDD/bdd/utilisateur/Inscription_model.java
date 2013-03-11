package bdd.utilisateur;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import main.main.Main;
import au.com.bytecode.opencsv.CSVWriter;
import bdd.model.Connect;
import bdd.model.Connection_valid;

public class Inscription_model implements Observable{
	//Notre collection d'observateurs !
	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();

	private ArrayList<String> listeUtilisateurs;
	private ArrayList<Integer> liste_ID_Utilisateurs;
	
	public Inscription_model(){
		//on vérifie que notre BDD contient une table 'utilisateurs' valide, sinon on créer se qui manque...
		String nom_de_la_table = "utilisateurs";
		this.table_utilisateurIsValide(nom_de_la_table);
		//this.id_utilisateurIsValide(nom_de_la_table, "id");
		this.nom_utilisateurIsValide(nom_de_la_table, "nom");
		this.code_utilisateurIsValide(nom_de_la_table, "code");
		this.droit_utilisateurIsValide(nom_de_la_table, "droit");
		
		init();
	}
	
	public void init(){
		
		listeUtilisateurs = new ArrayList<String>();
		liste_ID_Utilisateurs = new ArrayList<Integer>();
		
		Statement state;
		try {
			state = Connect.getInstance().createStatement();
		    ResultSet res = state.executeQuery("SELECT * FROM utilisateurs");
	          while(res.next()){
	        	  listeUtilisateurs.add(res.getString("nom"));
	        	  liste_ID_Utilisateurs.add(res.getInt("id"));
	          }
	          res.close();
	          state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.updateObservateur();
	}
	
	public boolean addUtilisateur(String nom, String code){
		boolean doublon = false;
		boolean done = false;
		for(String str : listeUtilisateurs){
			if(nom.equals(str)){
				doublon = true;
			}
		}
		if(!doublon){
			Statement state;
			try {
				state = Connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			    ResultSet uprs = state.executeQuery("SELECT * FROM utilisateurs");
			    	//on insert l'utilisateur avec les droits minimums
		          uprs.moveToInsertRow();
		          uprs.updateString("nom", nom);
		          uprs.updateString("code", code);
		          uprs.updateString("droit", "observateur");
		          uprs.insertRow();
		          
		          uprs.close();
		          state.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Utilisateur.dir_root = nom+"/";//on modifie le nom du répertoir de stockage
			
			Statement state2;//on prend la clef primaire créée par la BDD qu'on enregistre
			try {
				state2 = Connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			    ResultSet uprs = state2.executeQuery("SELECT * FROM utilisateurs");
			    	
			      uprs.last();
		          int id = (uprs.getInt("id"));
		          try {
					save(id);
					done = true;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		          uprs.close();
		          state2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		return done;
	}
	
	public void save(int id) throws IOException{
		CSVWriter writer = new CSVWriter(new FileWriter(Utilisateur.filepath));
	     // feed in your array (or convert your data to an array)
	     String[] entries = (""+id).split("#");
	     writer.writeNext(entries);
		writer.close();
		System.out.println(id);
	}
	
	public boolean table_utilisateurIsValide(String table){
		Connection conn = Connect.getInstance();
		boolean ok = Connection_valid.tabTransactionValide(conn, table);
		if(!ok){
			Statement state;
			try {
				state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			    state.executeUpdate("CREATE TABLE "+table+" (id INT NOT NULL AUTO_INCREMENT, PRIMARY KEY (id))");
		          state.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ok;
	}
	public boolean nom_utilisateurIsValide(String table, String column){
		Connection conn = Connect.getInstance();
		boolean ok = Connection_valid.columnInTab(conn, "utilisateurs", "nom");
		if(!ok){
			Statement state;
			try {
				state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			    state.executeUpdate("ALTER TABLE "+table+" ADD COLUMN "+column+" VARCHAR(64)");
		          state.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ok;
	}
	public boolean code_utilisateurIsValide(String table, String column){
		Connection conn = Connect.getInstance();
		boolean ok = Connection_valid.columnInTab(conn, "utilisateurs", "code");
		if(!ok){
			Statement state;
			try {
				state = conn.createStatement();
			    state.executeUpdate("ALTER TABLE "+table+" ADD COLUMN "+column+" VARCHAR(64)");
		          state.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ok;
	}
	public boolean droit_utilisateurIsValide(String table, String column){
		Connection conn = Connect.getInstance();
		boolean ok = Connection_valid.columnInTab(conn, "utilisateurs", "droit");
		if(!ok){
			Statement state;
			try {
				state = conn.createStatement();
			    state.executeUpdate("ALTER TABLE "+table+" ADD COLUMN "+column+" VARCHAR(64)");
		          state.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ok;
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
			obs.update(listeUtilisateurs);
		}
	}

	@Override
	public void addObservateur(Observateur obs) {
		// TODO Auto-generated method stub
		this.listObservateur.add(obs);
	}

	public void saveIDofSelectedItem(int selectedIndex) {
		// TODO Auto-generated method stub
		if(selectedIndex>=0 && selectedIndex < liste_ID_Utilisateurs.size()){
			try {
				save(liste_ID_Utilisateurs.get(selectedIndex));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(this.listeUtilisateurs.get(selectedIndex));
		}
		else{
			JOptionPane.showMessageDialog(null, "une erreure s'est produite. Le nom sélèctionné n'a pas été trouvé dans la base.\n", "alert", JOptionPane.ERROR_MESSAGE);
		}
	}

	public ArrayList<String> getListeUtilisateurs() {
		return listeUtilisateurs;
	}

	public void setListeUtilisateurs(ArrayList<String> listeUtilisateurs) {
		this.listeUtilisateurs = listeUtilisateurs;
	}

	public ArrayList<Integer> getListe_ID_Utilisateurs() {
		return liste_ID_Utilisateurs;
	}

	public void setListe_ID_Utilisateurs(ArrayList<Integer> listeIDUtilisateurs) {
		liste_ID_Utilisateurs = listeIDUtilisateurs;
	}
	
}
