package bdd.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bdd.interfaces.Observable;
import bdd.interfaces.Observateur;

public class Connection_valid implements Observable{
	
	//Notre collection d'observateurs !
	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
	
	private static ArrayList<Boolean> liste_des_test = new ArrayList<Boolean>();

	
	/*public static boolean connectionIsValide(Connection conn){
		//conn
	}*/
	
	public static boolean tabTransactionValide(Connection conn, String table){
		boolean tableExists = false;
		Statement state;
		try {
			state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		    ResultSet res = state.executeQuery("SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = '"+table+"'");
	          if(res.next()){
	        	  tableExists = true;
	          }
	          res.close();
	          state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tableExists;
	}
	
	public static boolean columnInTab(Connection conn, String table, String column){
		boolean columnExists = false;
		Statement state;
		try {
			state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		    ResultSet res = state.executeQuery("SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '"+table+"' AND COLUMN_NAME = '"+column+"'");
	          if(res.next()){
	        	  columnExists = true;
	          }
	          res.close();
	          state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return columnExists;
	}
	
	public static void miseAJour(ArrayList<String> listString){
		liste_des_test = new ArrayList<Boolean>();
		liste_des_test.add(tabTransactionValide(Connect.getInstance(), listString.get(0)));
		for(int i=1; i<6; i++){
			liste_des_test.add(columnInTab(Connect.getInstance(), listString.get(0), listString.get(i)));
		}
		liste_des_test.add(tabTransactionValide(Connect.getInstance(), listString.get(6)));
		for(int i=7; i<13; i++){
			liste_des_test.add(columnInTab(Connect.getInstance(), listString.get(6), listString.get(i)));
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
			obs.update(liste_des_test);
			obs.updateString(Connect.getBdd_schem());
		}
	}

	public static ArrayList<Boolean> getListe_des_test() {
		return liste_des_test;
	}

	public static void setListe_des_test(ArrayList<Boolean> listeDesTest) {
		liste_des_test = listeDesTest;
	}
	
	public static boolean connectionIsValide(){
		boolean ok = true;
		for(int i= 0; i<liste_des_test.size(); i++){
			if(!liste_des_test.get(i)){
				ok = false;
			}
		}
		return ok;
	}
	
}
