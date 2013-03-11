package source;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import main.vue.Fenetre_principale;
import bdd.model.Connect;
import bdd.utilisateur.Utilisateur;

public class Abonnes {

	private static ArrayList<Client> Tab = new ArrayList<Client>();
	private static ArrayList<Client> doublons = new ArrayList<Client>();
	
	public Abonnes(){
		
		Connect.init();
		Fenetre_principale.affichage_MaJ();
		for(int i=0; i<Tab.size(); i++){
			try {
				Suivi_client.init(Tab.get(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void miseAzero(){
		if(!Tab.isEmpty()){
			Tab.clear();
		}
		if(!doublons.isEmpty()){
			doublons.clear();
		}
	}
	
	public static boolean ajouter(Client c){
		int i = 0;
		int n;
		boolean done =false;
		
		
		do{
			if(!Tab.isEmpty() && i < Tab.size()){
			    String strC = c.getPrenom() + " " + c.getNom();
			    String str = Tab.get(i).getPrenom() + " " + Tab.get(i).getNom();
			    n = str.compareTo(strC);
			    if(n == 0){
			    	if(!Connect.isConnecting()){
			    		JOptionPane.showMessageDialog(null, "le compte :\n" + strC + "\nexiste deja !", "Erreur : doublon", JOptionPane.ERROR_MESSAGE);
			    	}
			    	
			    	done = false;
			    	if(!doublons.contains(Tab.get(i))){
			    		doublons.add(new Client(-1, "", "", 0, 0));
			    		doublons.add(Tab.get(i));
			    	}
			    	doublons.add(doublons.indexOf(Tab.get(i))+1, c);
			    	
			    	break;
			    }
			    else if(n > 0){
				    Tab.add(i, c);
				    try {
						Suivi_client.init(c);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    done = true;
				    break;
			    }
			    i++;
			}
			else{
				Tab.add(c);
				try {
					Suivi_client.init(c);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				done = true;
				break;
			}
			
		}while(n < 0);
		if(done){
			Fenetre_principale.affichage_MaJ();
		}
		return done;
		//save(Fenetre_principale.fichier_comptes);*/
	}
	
	public static void supprimer(Client client){
		Tab.remove(client);
	}
	
	
	
	public static ArrayList<Client> getTab() {
		return Tab;
	}



	public void setTab(ArrayList<Client> tab) {
		Tab = tab;
	}
	

	public static ArrayList<Client> getDoublons() {
		return doublons;
	}

	public static void save(){
        FileWriter fw;
        try {
        	fw = new FileWriter(
        								new File(Utilisateur.fichier_comptes));
        	for(int i = 0; i < Tab.size(); i++){
        		String str = Tab.get(i).getNom() + " " + Tab.get(i).getPrenom() + " " + Tab.get(i).getPromo()+ " " + Tab.get(i).getSolde() + "\n";
        		fw.write(str);
        	}
        	
            fw.close();
        	
			
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
	}
	public static void save(String fichier){
        FileWriter fw;
        try {
        	fw = new FileWriter(
        								new File(fichier));
        	for(int i = 0; i < Tab.size(); i++){
        		String str = Tab.get(i).getNom() + " " + Tab.get(i).getPrenom() + " " + Tab.get(i).getPromo()+ " " + Tab.get(i).getSolde() + "\n";
        		fw.write(str);
        	}
        	
            fw.close();
        	
			
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
	}
	
	}
