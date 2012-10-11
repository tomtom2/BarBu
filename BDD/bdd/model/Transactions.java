package bdd.model;

import java.sql.SQLException;
import java.sql.Statement;

public class Transactions {

	public static void addTransaction(int id_eleve, String operation, float montant, int id_utilisateur){
		String sql  = "INSERT INTO "+Connect.getTableTransactions()+" (";
		for(int i=8; i<12; i++){
			sql += Connect.getBdd_schem().get(i)+", ";
		}
		sql += Connect.getBdd_schem().get(12)+") VALUES ";
		sql += "("+"'"+id_eleve+"', "+"'"+operation+"', "+"'"+montant+"', "+"NOW(), "+"'"+id_utilisateur+"')";
		
		try {
			Statement statement = Connect.getInstance().createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
