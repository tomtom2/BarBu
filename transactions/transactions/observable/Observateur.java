package transactions.observable;

import java.util.ArrayList;

public interface Observateur {

	public void update(	ArrayList<String> liste_operation,
						ArrayList<Float> liste_montant,
						ArrayList<String> liste_date,
						ArrayList<String> liste_utilisateur);
}
