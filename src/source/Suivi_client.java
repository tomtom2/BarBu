package source;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.main.Main;
import au.com.bytecode.opencsv.CSVWriter;
import bdd.utilisateur.Utilisateur;


public class Suivi_client {

	private static String date;
	
	public static void init(Client cli) throws IOException{
		
		Date date1 = new Date();
		SimpleDateFormat  simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
		date =simpleFormat.format(date1);
		
		String id = cli.getPrenom() + "#" + cli.getNom();
		FileWriter mFileWriter = new FileWriter(Utilisateur.dir_historiques+id+".csv", true);
		CSVWriter writer = new CSVWriter(mFileWriter);
		String[] entries = ("date,"+date).split(",");
		writer.writeNext(entries);
		String[] etat = ("etat du compte,"+cli.getSolde()).split(",");
		writer.writeNext(etat);
			writer.close();
	}
	
	public static void enreg_transaction(Client client, Boisson conso) throws IOException{
		String id = client.getPrenom() + "#" + client.getNom();
		
		FileWriter mFileWriter = new FileWriter(Utilisateur.dir_historiques+id+".csv", true);
		CSVWriter writer = new CSVWriter(mFileWriter);
		String[] entries = ("conso,"+conso.getNom()+","+conso.getPrix()+","+conso.getDeg()+","+conso.getVolum()).split(",");
		writer.writeNext(entries);
			writer.close();
	}
	
	public static void enreg_transaction(Client client, float credit) throws IOException{
		String id = client.getPrenom() + "#" + client.getNom();
		
		FileWriter mFileWriter = new FileWriter(Utilisateur.dir_historiques+id+".csv", true);
		CSVWriter writer = new CSVWriter(mFileWriter);
		String[] entries = ("credit :,"+credit).split(",");
		writer.writeNext(entries);
			writer.close();
	}
	
	public static void replace(Client client1, Client client2) throws IOException{
		String id1 = client1.getPrenom() + "#" + client1.getNom();
		String id2 = client2.getPrenom() + "#" + client2.getNom();
		
		File clMod = new File(Utilisateur.dir_historiques+id2+".csv");
		new File(Utilisateur.dir_historiques+id1+".csv").renameTo(clMod);

	}
	
	public static void loadCSV(String id) throws IOException{
		FileWriter mFileWriter = new FileWriter(new File(Utilisateur.dir_historiques+id+".csv"), true);
		CSVWriter writer = new CSVWriter(mFileWriter);
		String[] entries = ("load from .csv,"+date).split(",");
		writer.writeNext(entries);
			writer.close();
	}
}
