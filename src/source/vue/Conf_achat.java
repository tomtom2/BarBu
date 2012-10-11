package source.vue;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import source.Boisson;
import source.Client;


public class Conf_achat extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JSpinner spinner;
	private SpinnerNumberModel model = new SpinnerNumberModel(new Integer(1), null, null, new Integer(1));
	private Client client;
	private Boisson conso;
	private static int nb_fen = 0;
	private static Conf_achat conf;
	
	/**
	 * Create the frame.
	 */
	private Conf_achat(Client cli, Boisson cons) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//JFrame.DISPOSE_ON_CLOSE
		setBounds(100, 100, 300, 120);
		setVisible(true);
		this.client = cli;
		this.conso = cons;
		setTitle(client.getPrenom() + " " + client.getNom());
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 1, 5, 5));
		
		JLabel lblNewLabel = new JLabel("ajouter :\n" + conso.getNom() + " ?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblQuantite = new JLabel("quantité : ");
		lblQuantite.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblQuantite);
		
		spinner = new JSpinner();
		spinner.setModel(model);
		panel_2.add(spinner);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(1, 2, 5, 5));
		
		JButton valider = new JButton("valider");
		valider.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int k = model.getNumber().intValue();
				/*for(int i = 0; i<k; i++){
				    client.ajouterConso(conso);
				    }
				client.boutMaJ();*/
				client.getCommande().ajouter_item(conso, k);
				
				dispose();
				nb_fen--;
				//Ardoise_client.fermer();
				
			}
			
		});
		panel_1.add(valider);
		
		JButton annuler = new JButton("annuler");
		annuler.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
				nb_fen--;
			}

		});
		panel_1.add(annuler);
	}
	
	public static Conf_achat conf_achat(Client cli, Boisson cons){
		if(nb_fen == 0){
			new Conf_achat(cli, cons);
			nb_fen++;
		}
		return conf;
	}
	
	public static void fermer(){
		conf.dispose(); nb_fen--;
	}
}
