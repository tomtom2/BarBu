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
	private static int id;
	private static String nom;
	private static String code;
	public static String droit;
	//private String description;
	
	//les chemins de fichiers
	public static String dir_root = "TEST/";
	public static String dir_comptes = dir_root+"comptes/";
	public static String dir_historiques = dir_comptes+"historiques/";
	public static String dir_private = dir_comptes+"privat/";
	public static String dir_images = dir_root+"images/";
	
	public static String fichier_comptes = dir_comptes+"comptes.txt";
	public static String fichier_conso = dir_comptes+"conso.csv";
	public static String fichier_types = dir_private+"types.csv";
	
	public static boolean first_time = true;
	
	public Utilisateur(){
		File f = new File(filepath);
		if(f.exists()){
			try {
				CSVReader reader = new CSVReader(new FileReader(f));
				String [] nextLine;
				nextLine = reader.readNext();
					id = Integer.valueOf(nextLine[0]).intValue();
					 
				reader.close();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("user initiation !");
			//init(id);
			
			
		}
		else{
			//chercher à s'identifier dans la liste des utilisateurs
			
			//si on n'est pas déjà inscrit :
			//coisir un nom et un mot de pass
			//puis on enregistre l'id dans un fichier.
			/*EventQueue.invokeLater(new Runnable() {
				public void run() {
					//new Inscription_model();//on check la BDD
					new Inscription_vue();
				}
			});*/
		}
	}

	public boolean init(int id){
		boolean taskDone = false;
		try {
			Connection conn = Connect.getSearchInstance();
			Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM utilisateurs WHERE id = "+id;
			ResultSet res = state.executeQuery(query);
			if(res.next()){
				nom = "TEST";//res.getString("nom");
				code = res.getString("code");
				droit = res.getString("droit");
				//description = res.getString("description");
				
				dir_root = nom+"/";
				dir_comptes = nom+"/"+"comptes/";
				dir_historiques = dir_comptes+"historiques/";
				dir_private = dir_comptes+"privat/";
				dir_images = nom+"/"+"images/";
				
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
							
							new MotDePass(nom);
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
	public void setNom(String nom) {
		Utilisateur.nom = nom;
	}

	public String getNom() {
		return nom;
	}
	
}
