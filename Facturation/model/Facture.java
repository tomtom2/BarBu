package model;

import java.util.ArrayList;

import source.Boisson;
import source.Client;
import facturation.observer.Observable;
import facturation.observer.Observateur;

public class Facture implements Observable{

	private Client client;
	private ArrayList<ItemCommande> commande = new ArrayList<ItemCommande>();
	//Notre collection d'observateurs !
	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
	
	public Facture(Client cli) {
		client = cli;
	}
	
	public void ajouter_item(Boisson ingredient, int nombre){
		commande.add(new ItemCommande(ingredient, nombre));
		//On avertit les observateurs que la liste a été mise à jour !
		this.updateObservateur();
	}
	
	public void retirer_item(int index){
		commande.remove(index);
		//On avertit les observateurs que la liste a été mise à jour !
		this.updateObservateur();
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
		for(Observateur obs : this.listObservateur )
			obs.update(this.commande);
	}
	
	public void valider(){
		for(int i=0; i<commande.size(); i++){
			for(int j = 0; j<commande.get(i).getQtt(); j++){
			    client.ajouterConso(commande.get(i).getIngredient());
			    }
		}
		client.boutMaJ();
		commande.clear();
	}

	public ArrayList<ItemCommande> getCommande() {
		return commande;
	}

	public void setCommande(ArrayList<ItemCommande> commande) {
		this.commande = commande;
	}
	
}
