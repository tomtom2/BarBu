package main.main;

import java.awt.EventQueue;

import source.Ardoise;
import source.MotDePass;
import bdd.model.Connect;
import bdd.utilisateur.Utilisateur;

public class Main {

	private static String fichier_conso = "conso.txt";
	private static String nom = "nom Asso";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

				Connect.getInstance();
				Ardoise.init(fichier_conso);
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						
						new MotDePass(nom);
					}
				});
				
				//new Utilisateur();//on lance la session

	}

}
