package source;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.main.Main;
import au.com.bytecode.opencsv.CSVWriter;
import bdd.utilisateur.Utilisateur;


public class DebiteurListener extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static DebiteurListener deb;
	private JPanel contentPane;
	private static File c_file = new File(Utilisateur.dir_root+"debiteurs.csv");
	//private static ArrayList<Client> Tab = new ArrayList<Client>();
	private static ArrayList<Client> Tab = new ArrayList<Client>();
	private static ArrayList<Client> Tri = new ArrayList<Client>();
     /**
	 * Create the frame.
	 */
	public DebiteurListener() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		init();
		
		Object[][] data = new Object[Tri.size()][3];
		for(int i = 0; i<Tri.size(); i++){
			Client cli = Tri.get(i);
			Object[] client = {cli.getPrenom(), cli.getNom(), cli.getSolde()+""};
			data[i] = client;
		}
	        //Les titres des colonnes
		String  title[] = {"prenom", "nom", "solde"};
		contentPane.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblDbiteurs = new JLabel("Débiteurs :");
		lblDbiteurs.setSize(this.getWidth(), 50);
		//lblDbiteurs.setSize(new Dimension(this.getWidth(), 50));
		lblDbiteurs.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDbiteurs);
		JTable tableau = new JTable(data, title);
                //On ajoute notre tableau à notre contentPane dans un scroll
                //Sinon les titres des colonnes ne s'afficheront pas ! !
		contentPane.add(new JScrollPane(tableau));
	}
	
	/*public static DebiteurListener getListe(){

		init();
		
			deb = new DebiteurListener();
			
		return deb;
	}*/
	
	public static void init(){

		if(Tri.isEmpty()){
		Tab = new ArrayList<Client>();
		
		for(int i=0; i<Abonnes.getTab().size(); i++){
			Client cl = Abonnes.getTab().get(i);
			if(cl.getSolde()<0){
				Tab.add(cl);
			}
		}
		if(Tab.size()>0){
		Tri.add(Tab.get(0));
		for(int i = 1; i<Tab.size(); i++){
			for(int k = 0; k< Tri.size(); k++){
				if(Tab.get(i).getSolde()<Tri.get(k).getSolde()){
					Tri.add(k, Tab.get(i));
					break;
				}
				if(k == Tri.size()-1){
					Tri.add(Tab.get(i));
					break;
				}
				
			}
		}
		}
		}
	}
	
	public static void ajouter(Client c){
		int i = 0;
		
		do{
			if(!Tab.isEmpty() && i < Tab.size()){
			    
			    if(c.getSolde()<Tab.get(i).getSolde()){
				    Tab.add(i, c);
				    break;
			    }
			    i++;
			}
			else{
				Tab.add(c);
				break;
			}
			
		}while(c.getSolde()>=Tab.get(i).getSolde());
		save();
	}
	
	public static void save(){
        FileWriter fw;
        try {
        	System.out.println(Utilisateur.dir_root);
        	System.out.println(c_file);
        	fw = new FileWriter(c_file, false);
        	CSVWriter writer = new CSVWriter(fw);
            // feed in your array (or convert your data to an array)
        	for(int i = 0; i < Tri.size(); i++){
        		String str = Tri.get(i).getNom() + "#" + Tri.get(i).getPrenom() + "#" + Tri.get(i).getSolde();
        		String[] entries = str.split("#");
                writer.writeNext(entries);
        	}
        	
            fw.close();
            writer.close();
        	
			
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
