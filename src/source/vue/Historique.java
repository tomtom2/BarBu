package source.vue;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import source.Boisson;
import source.Client;


public class Historique extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private JPanel contentPane;
	private Client client;

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public Historique(Client cli) throws IOException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		client = cli;
		setTitle(client.getPrenom()+" "+client.getNom());
		ArrayList<Boisson> liste = client.getConso();
		Object[][] data = new Object[client.getConso().size()][4];
		for(int i = 0; i<client.getConso().size(); i++){
			Boisson ingrediant = liste.get(i);
			Object[] conso = {ingrediant.getNom(), ingrediant.getPrix()+""};
			data[i] = conso;
		}
	        //Les titres des colonnes
		String  title[] = {"conso", "prix"};
		contentPane.setLayout(new BorderLayout(0, 0));
		JTable tableau = new JTable(data, title);
                //On ajoute notre tableau à notre contentPane dans un scroll
                //Sinon les titres des colonnes ne s'afficheront pas ! !	
		contentPane.add(new JScrollPane(tableau));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		NumberFormat formatter = new DecimalFormat("#0.00");
		
		JLabel lblNewLabel = new JLabel("depense : "+formatter.format(client.prix())+"€");
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("volume : "+formatter.format(client.volum())+" cl");
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("unites d'alcool : "+formatter.format(client.alcool()));
		panel.add(lblNewLabel_2);
		
		JMenuBar menuBar = new JMenuBar();
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("avance");
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
		mntmNewMenuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					new Histo_avance(client);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		menuBar.add(mntmNewMenuItem);
	}

}
