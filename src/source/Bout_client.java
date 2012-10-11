package source;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import source.vue.Conf_achat;


public class Bout_client extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Client client;
	private Boisson conso;
	
	public Bout_client(Client c, Boisson b){
		client = c; conso = b;
		this.setText(conso.getNom());
		this.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Conf_achat.conf_achat(client, conso);
			}
			
		});
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Boisson getConso() {
		return conso;
	}

	public void setConso(Boisson conso) {
		this.conso = conso;
	}

}
