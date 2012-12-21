package bdd.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import load.control.ProgressBarLoader;
import main.vue.Fenetre_principale;
import source.Boisson;
import source.Client;
import source.Suivi_client;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import bdd.utilisateur.Connection_set_adress;
import bdd.utilisateur.Utilisateur;
import bdd.vue.TableInfo;

public class Connect{
	
	private static int max;

	private static Connection conn;
	private static String url = "jdbc:sqlite:test.db";//"jdbc:mysql://localhost:3306/elevesdata";
	private static String user = "root";//pour le Bar'bu : "barbu" ;  pour PC-de-THOMAS : "postgres"
	private static String passwd = "";
	
	public static String filepath = "bdd.csv";
	
	private static String tableEleve[] = {"eleves", "table"};
	private static String column_idEleve[] = {"id", "INTEGER PRIMARY KEY"};
	private static String column_prenomEleve[] = {"prenom", "VARCHAR"};
	private static String column_nomEleve[] = {"nom", "VARCHAR"};
	private static String column_promoEleve[] = {"promo", "INTEGER"};
	private static String column_soldeEleve[] = {"solde", "NUMERIC"};
	
	private static String tableTransactions[] = {"transactions", "table"};
	private static String column_idTransactions[] = {"id", ""};
	private static String column_id_eleveTransactions[] = {"id_eleve", ""};
	private static String column_operationTransactions[] = {"operation", ""};
	private static String column_montantTransactions[] = {"montant", ""};
	private static String column_dateTransactions[] = {"date", ""};
	private static String column_id_utilisateurTransactions[] = {"id_utilisateur", ""};
	
	private static int avancement = 0;
	
	private static ArrayList<String[]> bdd_schem_clients = new ArrayList<String[]>();
	private static ArrayList<String[]> bdd_schem_transactions = new ArrayList<String[]>();
	
	private static boolean connecting = true;
	private static boolean running = false;
	
		
		private Connect() throws IOException{
			//Utilisateur util = new Utilisateur();
			//Fenetre_principale.dir_root = util.getNom();
			bdd_schem_clients.add(tableEleve);
			bdd_schem_clients.add(column_idEleve);
			bdd_schem_clients.add(column_prenomEleve);
			bdd_schem_clients.add(column_nomEleve);
			bdd_schem_clients.add(column_promoEleve);
			bdd_schem_clients.add(column_soldeEleve);
			
			bdd_schem_transactions.add(tableTransactions);
			bdd_schem_transactions.add(column_idTransactions);
			bdd_schem_transactions.add(column_id_eleveTransactions);
			bdd_schem_transactions.add(column_operationTransactions);
			bdd_schem_transactions.add(column_montantTransactions);
			bdd_schem_transactions.add(column_dateTransactions);
			bdd_schem_transactions.add(column_id_utilisateurTransactions);
			
		try {
			if(new File(filepath).exists()){
				CSVReader reader;
				try {
					reader = new CSVReader(new FileReader(filepath));
			    String [] nextLine;
			    while ((nextLine = reader.readNext()) != null) {
			        // nextLine[] is an array of values from the line
			    	if(nextLine.length == 1){
			    		url = nextLine[0]; user = ""; passwd = "";
			    	}
			    	else if(nextLine.length == 2){
			    		url = nextLine[0]; user = nextLine[1]; passwd = "";
			    	}
			    	else if(nextLine.length == 3){
			    		url = nextLine[0]; user = nextLine[1]; passwd = nextLine[2];
			    	}
			    }
			    reader.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
			}
			System.out.println("Connect : "+Utilisateur.dir_private);
			
			
			if(url.split(":")[1].equals("sqlite")){
				try {
					Class.forName("org.sqlite.JDBC");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			conn = DriverManager.getConnection(url);//, user, passwd);
			System.out.println("connection établie !");
		} catch (SQLException e) {
			e.printStackTrace();
			UIManager.put("OptionPane.okButtonText", "fait chier !!!");
			JOptionPane.showMessageDialog(null, "la connexion à la base de donnée a échoué", "erreur de connexion", JOptionPane.ERROR_MESSAGE);
				new Connection_set_adress();
			
		}
	}
		
		public static Connection getInstance(){
			if(conn == null){
				try {
					new Connect();
				} catch (IOException e) {
					e.printStackTrace();
				}
				createTablesIfNotExists();
				
			}
			return conn;	
		}
		
		private static void createTablesIfNotExists(){
			String tableName = bdd_schem_clients.get(0)[0];
			String update = "CREATE  TABLE IF NOT EXISTS "+tableName+" (";
			for(int i=1; i<bdd_schem_clients.size(); i++){
				String column = bdd_schem_clients.get(i)[0];
				String type = bdd_schem_clients.get(i)[1];
				if(i<bdd_schem_clients.size()-1){
					update += column+" "+type+", ";
				}
				else{
					update += column+" "+type+")";
				}
			}
			try {
				conn.createStatement().executeUpdate(update);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		private static void addColumn(String tableName, String column, String type){
			String update = "ALTER  TABLE \"main\".\""+tableName+"\" ADD (\""+column+"\" "+type+")";
			try {
				conn.createStatement().executeUpdate(update);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static Connection getSearchInstance(){
			Connection con = null;
				try {
					try {
						if(new File(filepath).exists()){
							CSVReader reader;
							try {
								reader = new CSVReader(new FileReader(filepath));
						    String [] nextLine;
						    while ((nextLine = reader.readNext()) != null) {
						        // nextLine[] is an array of values from the line
						    	if(nextLine.length == 1){
						    		url = nextLine[0]; user = ""; passwd = "";
						    	}
						    	else if(nextLine.length == 2){
						    		url = nextLine[0]; user = nextLine[1]; passwd = "";
						    	}
						    	else if(nextLine.length == 3){
						    		url = nextLine[0]; user = nextLine[1]; passwd = nextLine[2];
						    	}
						    }
						    reader.close();
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					try{	
						con = DriverManager.getConnection(url, user, passwd);
					} catch (SQLException e) {
						e.printStackTrace();
						UIManager.put("OptionPane.okButtonText", "fait chier !!!");
						JOptionPane.showMessageDialog(null, "la connexion à la base de donnée a échoué", "erreur de connexion", JOptionPane.ERROR_MESSAGE);
							new Connection_set_adress();
						
					}
					//new Connect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			return con;	
		}
	
	public static String getUrl() {
			return url;
		}

		public static String getUser() {
			return user;
		}

		public static String getPasswd() {
			return passwd;
		}

	public static void ajouter(Client client){
		
		String prenom = client.getPrenom(); String nom = client.getNom(); int promo = client.getPromo(); float solde = client.getSolde();
		
		Statement state;
		try {
			state = conn.createStatement();
			String columnNames = getColumn_prenomEleve()+", "+getColumn_nomEleve()+", "+getColumn_promoEleve()+", "+getColumn_soldeEleve();
			String valuesToInsert = "'"+prenom+"', '"+nom+"', '"+promo+"', "+solde;
		    String sqlInsert = "INSERT INTO "+getTableEleve()+" ("+columnNames+") VALUES ("+valuesToInsert+")";
		    
		    state.execute(sqlInsert);
		    
	          state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Statement state2;//on prend la clef primaire créée par la BDD qu'on indique à l'objet
		try {
			state2 = conn.createStatement();
		    ResultSet uprs = state2.executeQuery("SELECT ROWID FROM "+getTableEleve());
		    System.out.println(uprs.getInt(1));
	          client.setCle_id(uprs.getInt(getColumn_idEleve()));
	          
	          uprs.close();
	          state2.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void modifier(String prenom1, String nom1, String prenom2, String nom2, int promo, float solde){

		try {
			
			Statement state = conn.createStatement();
			String query = "UPDATE "+getTableEleve()+" SET "+getColumn_prenomEleve()+" = '"+prenom2+"', "+getColumn_nomEleve()+" = '"+nom2+"', "+getColumn_promoEleve()+" = '"+promo+"', "+getColumn_soldeEleve()+" = '"+solde+"' WHERE "+getColumn_prenomEleve()+" = '"+prenom1+"' AND "+getColumn_nomEleve()+" = '"+nom1+"'";
			state.executeUpdate(query);
			state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void supprimer(Client client){
		
		try {
			
			Statement state = conn.createStatement();
			String query = "DELETE FROM "+getTableEleve()+" WHERE "+getColumn_idEleve()+" = '"+client.getCle_id()+"'";
			state.executeUpdate(query);
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
public static void supprimerTOUT(){
		
		try {
			
			Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "DELETE FROM "+getTableEleve();
			state.executeUpdate(query);
			state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public static void crediter(Client client, float crd){
	
	try {
		Statement stateR = conn.createStatement();
		String queryR = "SELECT * FROM "+getTableEleve()+" WHERE "+getColumn_idEleve()+" = "+client.getCle_id();
		ResultSet res = stateR.executeQuery(queryR);
		res.next();
		float solde = res.getFloat(getColumn_soldeEleve()) + crd;
		stateR.close();
		res.close();
		
		Statement state = conn.createStatement();
		String query = "UPDATE "+getTableEleve()+" SET "+getColumn_soldeEleve()+" = '"+Client.verif_form_float(""+solde)+"' WHERE "+getColumn_idEleve()+" = "+client.getCle_id();
		state.executeUpdate(query);
		state.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//Transactions.addTransaction(client.getCle_id(), "crédit", crd, Utilisateur.getId());
}

public static void debiter_conso(Client client, Boisson ing){
	
	try {
		Statement stateR = conn.createStatement();
		String queryR = "SELECT * FROM "+getTableEleve()+" WHERE "+getColumn_idEleve()+" = "+client.getCle_id();
		ResultSet res = stateR.executeQuery(queryR);
		res.next();
		float solde = res.getFloat(getColumn_soldeEleve()) - ing.getPrix();
		stateR.close();
		res.close();
		
		Statement state = conn.createStatement();
		String query = "UPDATE "+getTableEleve()+" SET "+getColumn_soldeEleve()+" = '"+Client.verif_form_float(""+solde)+"' WHERE "+getColumn_idEleve()+" = "+client.getCle_id();
		state.executeUpdate(query);
		state.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//Transactions.addTransaction(client.getCle_id(), ing.getNom(), - ing.getPrix(), Utilisateur.getId());
}
	
public static void setSolde(String prenom, String nom, float crd){
		
		
		try {
			Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "UPDATE "+getTableEleve()+" SET "+getColumn_soldeEleve()+" = '"+crd+"' WHERE "+getColumn_prenomEleve()+" = '"+prenom+"' AND "+getColumn_nomEleve()+" = '"+nom+"'";
			state.executeUpdate(query);
			state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public static float getSolde(String prenom, String nom){
	
	float solde = 0;
	try {
		Statement stateR = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String queryR = "SELECT * FROM "+getTableEleve()+" WHERE "+getColumn_prenomEleve()+" = '"+prenom+"' AND "+getColumn_nomEleve()+" = '"+nom+"'";
		ResultSet res = stateR.executeQuery(queryR);
		res.first();
		solde = res.getFloat(getColumn_soldeEleve());
		res.close();
		stateR.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return solde;
}
	
	public static void init(){
		if(!isRunning())
			Fenetre_principale.getFenetre_principale().setVisible(false);
			running = true;
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					ProgressBarLoader.createAndShowGUI();
				}
			});
	}
	
	public static void loadFromCSV(String fichier) throws IOException{
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader(fichier));
		
	    String [] nextLine;
	    while ((nextLine = reader.readNext()) != null) {
	    	float solde = Float.valueOf(nextLine[3]).floatValue();
	    	int promo = Integer.valueOf(nextLine[2]).intValue();
	    	Client c = new Client(-1, nextLine[1].toLowerCase(), nextLine[0].toLowerCase(), solde, promo);
	        ajouter(c);
	        Suivi_client.loadCSV(nextLine[0].toLowerCase() + "#" + nextLine[1].toLowerCase());
	    }
	    reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void remplacerParCSV(String fichier){
		supprimerTOUT();
		try {
			loadFromCSV(fichier);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void generCSV(String path) throws IOException{
		
		CSVWriter writer = new CSVWriter(new FileWriter(path));
	     
		//pouet //
		try {
        	//On autorise la mise � jour des donn�es et la mise � jour de l'affichage
    		Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    	      
    	      ResultSet result = state.executeQuery("SELECT * FROM "+getTableEleve());
    			
    			while(result.next()){			
    				// feed in your array (or convert your data to an array)
    			     String[] entries = (result.getString(getColumn_prenomEleve())+","+ result.getString(getColumn_nomEleve())+","+ result.getString(getColumn_promoEleve())+","+result.getFloat(getColumn_soldeEleve())).split(",");
    			     writer.writeNext(entries);
    			}
			result.close();
			state.close();
			writer.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	public static void recovery() throws NumberFormatException, IOException{
		
		CSVReader reader = new CSVReader(new FileReader(Utilisateur.dir_comptes+"restauration.csv"));
	    String [] nextLine;
	    while ((nextLine = reader.readNext()) != null) {
	        // nextLine[] is an array of values from the line
	    	crediter(nextLine[0], nextLine[1], Client.verif_form_float(nextLine[2]));
	    	String id = nextLine[0]+"#"+nextLine[1];
	    	FileWriter mFileWriter = new FileWriter(Utilisateur.dir_historiques+id+".csv", true);
			CSVWriter writer = new CSVWriter(mFileWriter);
			String[] entries = ("recovery :"+"#"+Client.verif_form_float(nextLine[2])).split("#");
			writer.writeNext(entries);
				writer.close();
	    }
		reader.close();
		JOptionPane.showMessageDialog(null, "La restauration est terminée.\nAfin que les changements soient pris en compte,\nil faut redémarrer le programme !", "Recovery Done", JOptionPane.INFORMATION_MESSAGE);
	}
	*/
	public static boolean connectMaJ(String ur, String use, String pass, TableInfo tableInfo){
		
		boolean done = false;
		
		try {
			conn = DriverManager.getConnection(ur, use, pass);
			
			done = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(done){
			url = ur;  //"jdbc:postgresql://localhost:5432/bar";
			user = use;  //"postgres";//pour le Bar'bu : "barbu" ;  pour PC-de-THOMAS : "postgres"
			passwd = pass;  //"biere";
			CSVWriter writer;
			try {
				writer = new CSVWriter(new FileWriter(filepath));
		     // feed in your array (or convert your data to an array)
		     String[] entries = (url+"#"+user+"#"+passwd).split("#");
		     writer.writeNext(entries);
		     writer.close();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*ArrayList<String> list = new ArrayList<String>();
			for(int i=0; i<tableInfo.getTxtFieldsList().size(); i++){
				list.add(tableInfo.getTxtFieldsList().get(i).getText());
			}
			tableInfo.getConn();
			Connection_valid.miseAJour(list);
			ArrayList<Boolean> tests = Connection_valid.getListe_des_test();
			CSVWriter writer2;
			try {
				writer2 = new CSVWriter(new FileWriter(Utilisateur.dir_private+"table.csv"));
			
			for(int i=0; i<tests.size(); i++){
				
			     // feed in your array (or convert your data to an array)
			     String[] entries = ("").split("#");
			     
				if(tests.get(i)){
					bdd_schem.set(i, tableInfo.getTxtFieldsList().get(i).getText());
					entries = bdd_schem.get(i).split("#");
				}
				
				writer2.writeNext(entries);
				
			}
			writer2.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		else{
			try {
				//conn.close();
				conn = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return done;
	}
	
	public static boolean simpleConnection(String ur, String use, String pass){
		boolean done = false;
		try {
			conn = DriverManager.getConnection(ur, use, pass);
			url = ur;
			user = use;
			passwd = pass;
			
			CSVWriter writer;
			try {
				writer = new CSVWriter(new FileWriter(filepath));
		     // feed in your array (or convert your data to an array)
		     String[] entries = (url+"#"+user+"#"+passwd).split("#");
		     writer.writeNext(entries);
		     writer.close();
			done = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null, "la connexion à la base de donnée a échoué", "erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		return done;
	}
	
	public static String getPassWd(){
		// TODO
		String pass = "";
		return pass;
	}

	public static void setPassWd(String passwd){
		// TODO
	}

	public static boolean isConnecting() {
		return connecting;
	}

	public static ArrayList<String[]> getBdd_schem() {
		ArrayList<String[]> bdd_schem = new ArrayList<String[]>();
		for(int i=0; i<bdd_schem_clients.size(); i++){
			bdd_schem.add(bdd_schem_clients.get(i));
		}
		for(int i=0; i<bdd_schem_transactions.size(); i++){
			bdd_schem.add(bdd_schem_transactions.get(i));
		}
		return bdd_schem;
	}
	
	public static int getNbComptes() throws SQLException{
		String sql  = "SELECT * FROM "+getTableEleve();
		Statement statement = conn.createStatement();
		ResultSet resultat = statement.executeQuery(sql);
		
		int counter = 0;
		while(resultat.next()){ counter++; }
		return counter;
	}

	public static int getAvancement() {
		return avancement;
	}

	public static void setAvancement(int avancement) {
		Connect.avancement = avancement;
	}

	public static void setTableEleve(String tableEleve) {
		Connect.tableEleve[0] = tableEleve;
	}
	public static String getTableEleve() {
		return tableEleve[0];
	}
	public static void setConnecting(boolean connecting) {
		Connect.connecting = connecting;
	}
	public static void setMax(int max) {
		Connect.max = max;
	}
	public static int getMax() {
		return max;
	}
	public static void setColumn_soldeEleve(String column_soldeEleve) {
		Connect.column_soldeEleve[0] = column_soldeEleve;
	}
	public static String getColumn_soldeEleve() {
		return column_soldeEleve[0];
	}
	public static void setColumn_idEleve(String column_idEleve) {
		Connect.column_idEleve[0] = column_idEleve;
	}
	public static String getColumn_idEleve() {
		return column_idEleve[0];
	}
	public static void setColumn_nomEleve(String column_nomEleve) {
		Connect.column_nomEleve[0] = column_nomEleve;
	}
	public static String getColumn_nomEleve() {
		return column_nomEleve[0];
	}
	public static void setColumn_promoEleve(String column_promoEleve) {
		Connect.column_promoEleve[0] = column_promoEleve;
	}
	public static String getColumn_promoEleve() {
		return column_promoEleve[0];
	}
	public static void setColumn_prenomEleve(String column_prenomEleve) {
		Connect.column_prenomEleve[0] = column_prenomEleve;
	}
	public static String getColumn_prenomEleve() {
		return column_prenomEleve[0];
	}
	public static void setRunning(boolean running) {
		Connect.running = running;
	}
	public static boolean isRunning() {
		return running;
	}

	public static void setTableTransactions(String tableTransactions) {
		Connect.tableTransactions[0] = tableTransactions;
	}

	public static String getTableTransactions() {
		return tableTransactions[0];
	}

}