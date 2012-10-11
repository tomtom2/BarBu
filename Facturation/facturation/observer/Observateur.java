package facturation.observer;

import java.util.ArrayList;

import model.ItemCommande;

public interface Observateur {
	public void update(ArrayList<ItemCommande> item);
}
