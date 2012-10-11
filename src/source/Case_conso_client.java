package source;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import source.vue.Conf_achat;


public class Case_conso_client extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boisson conso;
	private Client client;
	private Pan_image pan_image;
	private JButton bouton_conso;
	/**
	 * Create the panel.
	 */
	public Case_conso_client(Boisson cons, Client cli) {

		this.conso = cons;
		this.client = cli;
		
		//***définition du bouton***
		bouton_conso = new JButton(conso.getNom());
		bouton_conso.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Conf_achat.conf_achat(client, conso);
			}
			
		});
		//******
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		
		pan_image = new Pan_image(conso.getImg());
		panel_1.add(pan_image);
		pan_image.setPreferredSize(new Dimension(40, 40));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel.add(bouton_conso, BorderLayout.CENTER);
	}

}
