package transactions.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bdd.model.Connect;
import bdd.utilisateur.Utilisateur;

import source.Client;
import transactions.observable.Observable;
import transactions.observable.Observateur;

public class Selection_transaction implements Observable {

	private ArrayList<String> liste_operation;
	private ArrayList<Float> liste_montant;
	private ArrayList<String> liste_date;
	private ArrayList<String> liste_utilisateur;
	
	private static int utilisateur_id = Utilisateur.getId();
	private static String date_debut = "";
	private static String date_fin = "";
	
	//Notre collection d'observateurs !
		private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
	
	public Selection_transaction(Client client, int utilisateur_id, String debut, String fin){
		recherche(client, utilisateur_id, debut, fin);
	}
	
	public void recherche(Client client, int utilisateur_id, String debut, String fin){
		
		liste_operation = new ArrayList<String>();
		liste_montant = new ArrayList<Float>();
		liste_date = new ArrayList<String>();
		liste_utilisateur = new ArrayList<String>(); ArrayList<Integer> liste_IDutilisateur = new ArrayList<Integer>();
		
		String query = "SELECT * FROM "+Connect.getTableTransactions()+" WHERE "+Connect.getBdd_schem().get(8)+" = "+client.getCle_id();//une selection sur id_eleve de la table transaction
		if(utilisateur_id != -1){
			query += " AND "+Connect.getBdd_schem().get(12)+" = "+utilisateur_id;
		}
		if(!debut.equals("")){
			query += " AND DATE("+Connect.getBdd_schem().get(11)+")+0 > "+debut;
		}
		if(!fin.equals("")){
			query += " AND DATE("+Connect.getBdd_schem().get(11)+")+0 < "+fin;
		}
		
		Statement state;
		try {
			state = Connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		    ResultSet res = state.executeQuery(query);
	          while(res.next()){
	        	  
	        	  liste_operation.add(res.getString(Connect.getBdd_schem().get(9)));
	        	  liste_montant.add(res.getFloat(Connect.getBdd_schem().get(10)));
	        	  liste_date.add(res.getString(Connect.getBdd_schem().get(11)));
	        	  liste_IDutilisateur.add(res.getInt(Connect.getBdd_schem().get(12)));
	          }
	          res.close();
	          state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int id_utilisateur : liste_IDutilisateur){
			Statement state2;
			try {
				state2 = Connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			    ResultSet res = state2.executeQuery("SELECT nom FROM utilisateurs WHERE id = "+id_utilisateur);
		          if(res.next()){
		        	  liste_utilisateur.add(res.getString("nom"));
		          }
		          else{
		        	  liste_utilisateur.add("inconnu");
		          }
		          res.close();
		          state2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.updateObservateurs();
	}

	public static int getUtilisateur_id() {
		return utilisateur_id;
	}

	public static void setUtilisateur_id(int utilisateur_id) {
		Selection_transaction.utilisateur_id = utilisateur_id;
	}

	public static String getDate_debut() {
		return date_debut;
	}

	public static void setDate_debut(String date_debut) {
		Selection_transaction.date_debut = date_debut;
	}

	public static String getDate_fin() {
		return date_fin;
	}

	public static void setDate_fin(String date_fin) {
		Selection_transaction.date_fin = date_fin;
	}

	@Override
	public void addObservateur(Observateur obs) {
		// TODO Auto-generated method stub
		listObservateur.add(obs);
	}

	@Override
	public void deleteObservateurs() {
		// TODO Auto-generated method stub
		listObservateur = new ArrayList<Observateur>();
	}

	@Override
	public void updateObservateurs() {
		// TODO Auto-generated method stub
		for(Observateur obs : listObservateur )
			obs.update(liste_operation, liste_montant, liste_date, liste_utilisateur);
	}
	
	
}
