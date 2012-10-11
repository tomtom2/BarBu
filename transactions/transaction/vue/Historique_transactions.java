package transaction.vue;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import transactions.model.Selection_transaction;
import transactions.observable.Observateur;

public class Historique_transactions extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Selection_transaction transactions;
	private Object[][] data;
	
	private JScrollPane scrollPane;
	private JTable tableau;

	/**
	 * Create the panel.
	 */
	public Historique_transactions(Selection_transaction transaction) {
		setLayout(new BorderLayout(5, 5));
		
		this.transactions = transaction;
		
		//initialisation du tableau via l'Observateur
		transactions.addObservateur(new Observateur(){

			@Override
			public void update(ArrayList<String> liste_operation,
					ArrayList<Float> liste_montant,
					ArrayList<String> liste_date,
					ArrayList<String> liste_utilisateur) {
				// TODO Auto-generated method stub
				data = new Object[liste_operation.size()][4];
				for(int i = 0; i<liste_operation.size(); i++){
					Object[] op = {liste_operation.get(i), liste_montant.get(i)+"", liste_date.get(i), liste_utilisateur.get(i)};
					data[i] = op;
				}
			}
			
		});
		transactions.updateObservateurs();
		
	        //Les titres des colonnes
		String  title[] = {"operation", "montant", "date", "effectué par"};
		tableau = new JTable(data, title);
		//fin de l'initialisation
		scrollPane = new JScrollPane(tableau);
		add(scrollPane, BorderLayout.CENTER);

	}

}
