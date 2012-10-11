package transactions.observable;

public interface Observable {

	public void addObservateur(Observateur obs);
	public void deleteObservateurs();
	public void updateObservateurs();
}
