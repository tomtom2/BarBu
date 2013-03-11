package bdd.utilisateur;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import source.Ardoise;
import source.MotDePass;
import au.com.bytecode.opencsv.CSVReader;
import bdd.model.Connect;

public class Utilisateur {

	public static String filepath = "util.csv";
	public static int id = 1;
	private static String nom = "";
	private static String code;
	public static String droit;
	//private String description;
	
	//les chemins de fichiers
	public static String dir_root = "";
	public static String dir_comptes = dir_root+"comptes/";
	public static String dir_historiques = dir_comptes+"historiques/";
	public static String dir_private = dir_comptes+"privat/";
	public static String dir_images = dir_root+"images/";
	
	public static String fichier_comptes = dir_comptes+"comptes.txt";
	public static String fichier_conso = dir_comptes+"conso.csv";
	public static String fichier_types = dir_private+"types.csv";
	
	public static boolean first_time = true;
	
	public Utilisateur(){
		//work only with single user systeme!
		Utilisateur.setId(Connect.getUserId());
		Utilisateur.nom = Connect.getUserName();
		Utilisateur.dir_root = Connect.getUserName()+"/";
		init(1);
	}

	public boolean init(int id){
		boolean taskDone = false;
		try {
			Connection conn = Connect.getSearchInstance();
			Statement state = conn.createStatement();
			String query = "SELECT * FROM "+Connect.tableUser[0]+" WHERE "+Connect.getColumn_userId()+" = "+id;
			ResultSet res = state.executeQuery(query);
			if(res.next()){
				nom = res.getString(Connect.getColumn_userName());
				code = res.getString(Connect.getColumn_password());
				droit = "";//res.getString("droit");
				//description = res.getString("description");
				
				dir_root = getNom()+"/";
				dir_comptes = getNom()+"/"+"comptes/";
				dir_historiques = dir_comptes+"historiques/";
				dir_private = dir_comptes+"privat/";
				dir_images = getNom()+"/"+"images/";
				
				fichier_comptes = dir_comptes+"comptes.txt";
				fichier_conso = dir_comptes+"conso.csv";
				fichier_types = dir_private+"types.csv";
				
				
				
				MotDePass.setMot_de_pass(code);
				if(first_time){
					first_time = false;
					
					new File(Utilisateur.dir_root+"comptes/privat/").mkdirs();
					new File(Utilisateur.dir_root+"comptes/historiques/").mkdir();
					new File(Utilisateur.dir_root+"images/").mkdir();
					try {
						Ardoise.init(fichier_conso);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							
							new MotDePass(getNom());
						}
					});
				}
				
			}
			else{
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						
						new Inscription_vue();
					}
				});
				
			}
			res.close();
			state.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new Inscription_vue();
		}
		
		return taskDone;
	}
	
	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Utilisateur.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		Utilisateur.code = code;
	}

	public String getDroit() {
		return droit;
	}

	public void setDroit(String droit) {
		Utilisateur.droit = droit;
	}
/*
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
*/
	public static void setNom(String nom) {
		Utilisateur.nom = nom;
	}

	public static String getNom() {
		return Connect.getUserName();
	}
	
}
