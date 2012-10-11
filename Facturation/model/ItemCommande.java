package model;

import source.Boisson;

public class ItemCommande {
	private Boisson ingredient;
	private int qtt;
	
	public ItemCommande(Boisson ingredient, int quantite){
		this.ingredient = ingredient; qtt = quantite;
	}

	public Boisson getIngredient() {
		return ingredient;
	}

	public void setIngredient(Boisson ingredient) {
		this.ingredient = ingredient;
	}

	public int getQtt() {
		return qtt;
	}

	public void setQtt(int qtt) {
		this.qtt = qtt;
	}
	
}
