package bdd.interfaces;

import java.util.ArrayList;

public interface Observateur {
	public void update(ArrayList<Boolean> listeDesTest);
	public void updateString(ArrayList<String> listeDesLabels);
}
