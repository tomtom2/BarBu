package suppression;
import java.util.ArrayList;

import source.Abonnes;
import source.Client;


public class Select_suppr {

	private static ArrayList<Client> liste = new ArrayList<Client>();
	
	public static final int NULL = -1;
	public static final int EGAL_A = 0;
	public static final int SUPPERIEUR_A = 1;
	public static final int INFERIEUR_A = 2;
	public static final int COMPRI_ENTRE = 3;
	
	private static int promo = 2014;
	private static int action = NULL;
	private static float solde = 0;
	
	private static float soldeInf = 0;
	private static float soldeSup = 0;
	
	public Select_suppr(int promo, float solde, float solde1, float solde2){
		Select_suppr.promo = promo;
		Select_suppr.solde = solde;
		Select_suppr.soldeInf = solde1;
		Select_suppr.soldeSup = solde2;
		
		process(action);
	}
	
	private static void process(int action){
		liste.clear();
		selectPromo();
		
		switch (action)
	     {
	       case NULL:
	         break;
	         
	       case EGAL_A:
	    	   selectSoldeEgalA();
	         break;
	       
	       case SUPPERIEUR_A: 
	    	   selectSoldeSupA();
	         break;
	         
	       case INFERIEUR_A:
	    	   selectSoldeInfA();
	         break;
	         
	       case COMPRI_ENTRE:
	    	   selectSoldeCompriEntre();
	    	   break;            
	     }
		
	}
	
	public static void clear(){
		liste.clear();
		promo = 2014;
		action = NULL;
		solde = 0;
		soldeInf = 0;
		soldeSup = 0;
	}
	
	public static void selectPromo(){
		for(int i=0; i<Abonnes.getTab().size(); i++){
			if(Abonnes.getTab().get(i).getPromo()==promo){
				liste.add(Abonnes.getTab().get(i));
			}
		}
	}
	
	public static void selectSoldeEgalA(){
		for(int i=0; i<liste.size(); i++){
			if(liste.get(i).getSolde() != solde){
				liste.remove(i);
				if(!liste.isEmpty()){
					i = i-1;
				}
				
			}
		}
	}
	
	public static void selectSoldeSupA(){
		for(int i=0; i<liste.size(); i++){
			if(liste.get(i).getSolde() < solde){
				liste.remove(i);
				if(!liste.isEmpty()){
					i = i-1;
				}
			}
		}
	}
	
	public static void selectSoldeInfA(){
		for(int i=0; i<liste.size(); i++){
			if(liste.get(i).getSolde() > solde){
				liste.remove(i);
				if(!liste.isEmpty()){
					i = i-1;
				}
			}
		}
	}
	
	public static void selectSoldeCompriEntre(){
		for(int i=0; i<liste.size(); i++){
			if(liste.get(i).getSolde() < soldeInf || liste.get(i).getSolde() > soldeSup){
				liste.remove(i);
				if(!liste.isEmpty()){
					i = i-1;
				}
			}
		}
	}

	public static ArrayList<Client> getListe() {
		return liste;
	}

	public static void setListe(ArrayList<Client> liste) {
		Select_suppr.liste = liste;
	}

	public static int getPromo() {
		return promo;
	}

	public static void setPromo(int promo) {
		Select_suppr.promo = promo;
	}

	public static int getAction() {
		return action;
	}

	public static void setAction(int action) {
		Select_suppr.action = action;
	}

	public static float getSolde() {
		return solde;
	}

	public static void setSolde(float solde) {
		Select_suppr.solde = solde;
	}

	public static float getSoldeInf() {
		return soldeInf;
	}

	public static void setSoldeInf(float soldeInf) {
		Select_suppr.soldeInf = soldeInf;
	}

	public static float getSoldeSup() {
		return soldeSup;
	}

	public static void setSoldeSup(float soldeSup) {
		Select_suppr.soldeSup = soldeSup;
	}
	
}
