package droit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bdd.model.Connect;
import droit.observable.Observable;
import droit.observable.Observateur;

public class Liste_Utilisateurs implements Observable{

	public static String[] droits = {"observateur", "utilisateur", "utilisateur_admin", "admin"};
	
	private ArrayList<Integer> liste_id;
	private ArrayList<String> liste_nom;
	private ArrayList<String> liste_droits;
	//Notre collection d'observateurs !
	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
	
	
	public Liste_Utilisateurs(){
		init();
	}
	
	public void init(){
		liste_id = new ArrayList<Integer>();
		liste_nom = new ArrayList<String>();
		liste_droits = new ArrayList<String>();
		
		Statement state;
		try {
			state = Connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		    ResultSet res = state.executeQuery("SELECT * FROM utilisateurs");
	          while(res.next()){
	        	  liste_id.add(res.getInt("id"));
	        	  liste_nom.add(res.getString("nom"));
	        	  liste_droits.add(res.getString("droit"));
	          }
	          res.close();
	          state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.updateObservateur();
	}
	
	public void removeUtil(int i){
		Statement state;
		try {
			state = Connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		    state.executeUpdate("DELETE FROM utilisateurs WHERE id = "+liste_id.get(i));
		    	
	          state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		init();
	}
	public void addUtil(String nom, String code, String droit){
		if(!doublon(nom)){
			Statement state;
			try {
				state = Connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			    ResultSet uprs = state.executeQuery("SELECT * FROM utilisateurs");
			    	//on insert l'utilisateur
		          uprs.moveToInsertRow();
		          uprs.updateString("nom", nom);
		          uprs.updateString("code", code);
		          uprs.updateString("droit", droit);
		          uprs.insertRow();
		          
		          uprs.close();
		          state.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			init();
		}
		else{
			JOptionPane.showMessageDialog(null, nom+" existe déjà !\n", "doublon", JOptionPane.ERROR_MESSAGE);
			
		}
	}
	
	public void modifier_droit_utilisateur(int id, String droit){
		Statement state;
		try {
			state = Connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		    state.executeUpdate("UPDATE utilisateurs SET droit = '"+droit+"' WHERE id = "+id);
		    	
	          state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			obs.update(this);
		}
	}

	public ArrayList<Integer> getListe_id() {
		return liste_id;
	}

	public void setListe_id(ArrayList<Integer> listeId) {
		liste_id = listeId;
	}

	public ArrayList<String> getListe_nom() {
		return liste_nom;
	}

	public void setListe_nom(ArrayList<String> listeNom) {
		liste_nom = listeNom;
	}

	public ArrayList<String> getListe_droits() {
		return liste_droits;
	}

	public void setListe_droits(ArrayList<String> listeDroits) {
		liste_droits = listeDroits;
	}
	
	public boolean doublon(String nom){
		boolean doublon = false;
		for(String str : liste_nom)
			if(str.equalsIgnoreCase(nom)){doublon = true;}
		return doublon;
	}
	
	public static boolean supperieurA(String droit_util, String droit_min){
		int droit_utilisateur_actuel = -1;
		int droit_minimal_requi = 0;
		for(int i=0; i<droits.length; i++){
			if(droit_util.equals(droits[i])){
				droit_utilisateur_actuel = i;
			}
			if(droit_min.equals(droits[i])){
				droit_minimal_requi = i;
			}
		}
		System.out.println("droit_utilisateur : "+droit_utilisateur_actuel+"\tdroit_requis : "+droit_minimal_requi);
		return droit_utilisateur_actuel >= droit_minimal_requi;
	}
}
