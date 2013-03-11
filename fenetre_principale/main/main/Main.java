package main.main;

import java.awt.EventQueue;

import source.Ardoise;
import source.MotDePass;
import bdd.model.Connect;
import bdd.utilisateur.Utilisateur;

public class Main {


	public static void main(String[] args) {
		// TODO Auto-generated method stub

				Connect.getInstance();
				new Utilisateur();
				Ardoise.init(Utilisateur.fichier_conso);
				
//				EventQueue.invokeLater(new Runnable() {
//					public void run() {
//						new MotDePass(Utilisateur.getNom());
//					}
//				});
				

	}

}
