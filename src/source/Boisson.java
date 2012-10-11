package source;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import source.vue.Conf_achat;
import source.vue.Fen_conso;


public class Boisson implements Supprimable {

	private String nom;
	private String img;
	private String type;
	private float volum;
	private float deg;
	private float prix;
	private Boisson conso;
	
	private JButton bouton;
	private JButton bout;
	
	public Boisson(String n, String i, float v, float d, float p, String t){
		nom = n; img = i; volum = v; deg = d; prix = p; type = t; conso = this;
		bouton = new JButton(nom);
		bouton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Fen_conso.fen_conso(conso);
			}
			
		});
		
		bout = new JButton(nom);
	}
	
	public JButton bouton(Client client){
		final Client c = client;
		bout.setHorizontalAlignment(JButton.LEFT);
		bout.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Conf_achat.conf_achat(c, conso);
			}
			
		});
		return bout;
	}

	public JButton getBouton() {
		return bouton;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public float getVolum() {
		return volum;
	}

	public void setVolum(float volum) {
		this.volum = volum;
	}

	public float getDeg() {
		return deg;
	}

	public void setDeg(float deg) {
		this.deg = deg;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public void supprimer() {
		// TODO Auto-generated method stub
		Ardoise.remove(this);
		Ardoise.afficher_case_conso();
		new Ardoise();
	}
	
	public Boisson clone(){
		return new Boisson(getNom(), getImg(), getVolum(), getDeg(), getPrix(), getType());
	}
	
	
}
