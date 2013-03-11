package source;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import main.main.Main;

import source.vue.Fen_conso;

import bdd.utilisateur.Utilisateur;


public class Ardoise extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTabbedPane bieresPane;
	private static JPanel blanchesPane = new JPanel();
	private static JPanel blondesPane = new JPanel();
	private static JPanel ambreesPane = new JPanel();
	private static JPanel brunesPane = new JPanel();
	private static JPanel autresPane = new JPanel();
	
	private static ArrayList<Boisson> Tab = new ArrayList<Boisson>();
    private static ArrayList<Boisson> blanches = new ArrayList<Boisson>();
    private static ArrayList<Boisson> blondes = new ArrayList<Boisson>();
    private static ArrayList<Boisson> ambrees = new ArrayList<Boisson>();
    private static ArrayList<Boisson> brunes = new ArrayList<Boisson>();
    private static ArrayList<Boisson> autre = new ArrayList<Boisson>();
	
    
	/**
	 * Create the frame.
	 */
	public Ardoise() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		JMenu mnBoisson = new JMenu("boisson");
		menuBar.add(mnBoisson);
		
		JMenuItem mntmAjouter = new JMenuItem("Ajouter");
		mntmAjouter.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Fen_conso.fen_conso();
			}
			
		});
		mnBoisson.add(mntmAjouter);
		
		JMenu mnAide = new JMenu("aide");
		menuBar.add(mnAide);
		
		JMenuItem mntmAjouter_1 = new JMenuItem("Ajouter");
		mnAide.add(mntmAjouter_1);
		
		JMenu mnModifier = new JMenu("Modifier");
		mnAide.add(mnModifier);
		
		JMenuItem mntmImage = new JMenuItem("image");
		mnModifier.add(mntmImage);
		
		JMenuItem mntmInfos = new JMenuItem("infos");
		mnModifier.add(mntmInfos);
		
		bieresPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(bieresPane, BorderLayout.CENTER);
		
		blondesPane = new JPanel();
		blondesPane.setLayout(new GridLayout(0, 5, 5, 5));
		
		ambreesPane = new JPanel();
		ambreesPane.setLayout(new GridLayout(0, 5, 5, 5));
		
		brunesPane = new JPanel();
		brunesPane.setLayout(new GridLayout(0, 5, 5, 5));
		
		autresPane = new JPanel();
		autresPane.setLayout(new GridLayout(0, 5, 5, 5));
		
		blanchesPane = new JPanel();
		blanchesPane.setLayout(new GridLayout(0, 5, 5, 5));
		
		JScrollPane scrollPane_1 = new JScrollPane(blanchesPane);
		bieresPane.addTab("blanches", null, scrollPane_1, null);
		
		JScrollPane scrollPane = new JScrollPane(blondesPane);
		bieresPane.addTab("blondes", null, scrollPane, null);
		
		JScrollPane scrollPane_2 = new JScrollPane(ambreesPane);
		bieresPane.addTab("ambrees", null, scrollPane_2, null);
		
		JScrollPane scrollPane_3 = new JScrollPane(brunesPane);
		bieresPane.addTab("brunes", null, scrollPane_3, null);
		
		JScrollPane scrollPane_4 = new JScrollPane(autresPane);
		bieresPane.addTab("autres", null, scrollPane_4, null);
		setVisible(true);
		afficher_case_conso();
	}

	public static void ajouter(Boisson c){
		

		ajouterTab(c);
		
		if(Tab.contains(c)){
		
		    if(c.getType().equals("blanche")){
			    ajouter(blanches, c);
		    }
		    else if(c.getType().equals("blonde")){
			    ajouter(blondes, c);
		    }
		    else if(c.getType().equals("ambree")){
			    ajouter(ambrees, c);
		    }
		    else if(c.getType().equals("brune")){
			    ajouter(brunes, c);
		    }
		    else{ajouter(autre, c);}
		   }
		save();
		
	}
	
	public static void ajouter(ArrayList<Boisson> liste, Boisson c){
		int i = 0;
		int n;
		
		
		do{
			if(!liste.isEmpty() && i < liste.size()){
			    String strC = c.getNom();
			    String str = liste.get(i).getNom();
			    n = str.compareTo(strC);
			    
			    if(n > 0){
			    	liste.add(i, c);
				    break;
			    }
			    i++;
			}
			else{
				liste.add(c);
				break;
			}
			
		}while(n < 0);
		//save();
	}
	
	public static void ajouterTab(Boisson c){
		int i = 0;
		int n;
		
		
		do{
			if(!Tab.isEmpty() && i < Tab.size()){
			    String strC = c.getNom();
			    String str = Tab.get(i).getNom();
			    n = str.compareTo(strC);
			    if(n == 0){
			    	new JOptionPane();
					JOptionPane.showMessageDialog(null, strC + " existe deja !", "Erreur : doublon", JOptionPane.ERROR_MESSAGE);
			    	break;
			    }
			    else if(n > 0){
			    	Tab.add(i, c);
				    break;
			    }
			    i++;
			}
			else{
				Tab.add(c);
				break;
			}
			
		}while(n < 0);
		//save();
	}
	
	public static void remove(Boisson boi){
		Tab.remove(boi);
		if(boi.getType().equals("blanche")){
			blanches.remove(boi);
		}
		else if(boi.getType().equals("blonde")){
			blondes.remove(boi);
		}
		else if(boi.getType().equals("ambree")){
			ambrees.remove(boi);
		}
		else if(boi.getType().equals("brune")){
			brunes.remove(boi);
		}
		else{autre.remove(boi);}
		
		save();
	}
	
	public static void save(){
        FileWriter fw;
        try {
        	fw = new FileWriter(
        			//new File(Utilisateur.fichier_conso));
        			new File(Utilisateur.fichier_conso));
        	if(!Tab.isEmpty()){
        	for(int i = 0; i < Tab.size(); i++){
        		String str = Tab.get(i).getNom() + " " + Tab.get(i).getImg() + " " + Tab.get(i).getVolum() + " " + Tab.get(i).getDeg() + " " + Tab.get(i).getPrix() + " " + Tab.get(i).getType() + "\n";
        		fw.write(str);
        	}}
        	
            fw.close();
        	
			
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
	}
	public static void save(String file){
        FileWriter fw;
        try {
        	fw = new FileWriter(
        								new File(file));
        	for(int i = 0; i < Tab.size(); i++){
        		String str = Tab.get(i).getNom() + " " + Tab.get(i).getImg() + " " + Tab.get(i).getVolum() + " " + Tab.get(i).getDeg() + " " + Tab.get(i).getPrix() + " " + Tab.get(i).getType() + "\n";
        		fw.write(str);
        	}
        	
            fw.close();
        	
			
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}
	
public static void init(){
		
		FileReader fr;
        try {
        	fr = new FileReader(
    		        //new File(Utilisateur.fichier_conso));
        			new File(Utilisateur.fichier_conso));
        	
        	enreg(fr);
        	
            fr.close();
        	
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
	}
public static void init(String file){
	
	FileReader fr;
    try {
    	fr = new FileReader(
								new File(file));
    	
    	enreg(fr);
    	
        fr.close();
    	
        
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    
}
	
	private static void enreg(FileReader fr){
		String[] str = {"", "", "", "", "", ""};
		float vol;
		float deg;
		float prix;
		int i;
		int value = 0;
		//Lecture des données
		try {
			while((i = fr.read()) != -1){
				if((char)i != ' ' && (char)i != '\n'){
				str[value] += Character.toString((char)i);}
				else if((char)i == ' '){
					value++;
				}
				else if(Character.isWhitespace((char)i) && (char)i != ' '){
					
					vol = Float.valueOf(str[2]).floatValue();
					deg = Float.valueOf(str[3]).floatValue();
					prix = Float.valueOf(str[4]).floatValue();
						
					ajouter(new Boisson(str[0], str[1], vol, deg, prix, str[5]));
					
					value = 0; str[0] = str[1] = str[2] = str[3] = str[4] = str[5] = "";
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void afficher_case_conso(){
		blanchesPane.removeAll(); blanchesPane.repaint();
		blondesPane.removeAll(); blondesPane.repaint();
		ambreesPane.removeAll(); ambreesPane.repaint();
		brunesPane.removeAll(); brunesPane.repaint();
		autresPane.removeAll(); autresPane.repaint();
		
		for(int i = 0; i < blanches.size(); i++){
			JPanel pan = new JPanel();
			pan.add(new JScrollPane(new Case_conso(blanches.get(i))));
			pan.setPreferredSize(new Dimension(50, 95));
			blanchesPane.add(pan);
		}
		
		for(int i = 0; i < blondes.size(); i++){
			JPanel pan = new JPanel();
			pan.add(new JScrollPane(new Case_conso(blondes.get(i))));
			pan.setPreferredSize(new Dimension(50, 95));
			blondesPane.add(pan);
		}
		
		for(int i = 0; i < ambrees.size(); i++){
			JPanel pan = new JPanel();
			pan.add(new JScrollPane(new Case_conso(ambrees.get(i))));
			pan.setPreferredSize(new Dimension(50, 95));
			ambreesPane.add(pan);
		}
		
		for(int i = 0; i < brunes.size(); i++){
			JPanel pan = new JPanel();
			pan.add(new JScrollPane(new Case_conso(brunes.get(i))));
			pan.setPreferredSize(new Dimension(50, 95));
			brunesPane.add(pan);
		}
		
		for(int i = 0; i < autre.size(); i++){
			JPanel pan = new JPanel();
			pan.add(new JScrollPane(new Case_conso(autre.get(i))));
			pan.setPreferredSize(new Dimension(50, 95));
			autresPane.add(pan);
		}
		
	}

	public static ArrayList<Boisson> getTab() {
		return Tab;
	}

	public static ArrayList<Boisson> getBlanches() {
		return blanches;
	}

	public static ArrayList<Boisson> getBlondes() {
		return blondes;
	}

	public static ArrayList<Boisson> getAmbrees() {
		return ambrees;
	}

	public static ArrayList<Boisson> getBrunes() {
		return brunes;
	}

	public static ArrayList<Boisson> getAutre() {
		return autre;
	}
	
}
