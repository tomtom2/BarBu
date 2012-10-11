package droit.observable;

import droit.model.Liste_Utilisateurs;

public interface Observateur {
	public void update(Liste_Utilisateurs ListUtil);
}
