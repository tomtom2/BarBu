package source.vue;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import source.Ardoise;
import source.Boisson;


public class CP_conf_snack extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Conf_conso conf_conso;
	private JButton valider;
	
	private JLabel lblPrix;
	private JLabel lblnom;
	private JLabel lbltype;
	private JLabel lblprix;
	private JPanel panel_1;
	private JLabel lblimage;
	
	private String nom;
	private float prix;
	private String type;
	private String image;
	/**
	 * Create the panel.
	 */
	public CP_conf_snack(Conf_conso conf, Fen_conso conso, String n, float v, float d, String t, float p, String img) {

		nom = n;
		type = t;
		prix = p;
		image = img;
		
		CP_conf_snack.conf_conso = conf;
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(2, 2));
		
		JPanel panel = new JPanel();
		this.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 2, 5, 5));
		
		JLabel nom1 = new JLabel("nom");
		panel.add(nom1);
		
		lblnom = new JLabel(nom);
		panel.add(lblnom);
		
		
		JLabel lblNewLabel = new JLabel("type");
		panel.add(lblNewLabel);
		
		lbltype = new JLabel(type);
		panel.add(lbltype);
		
		lblPrix = new JLabel("prix");
		panel.add(lblPrix);
		
		lblprix = new JLabel(""+prix);
		panel.add(lblprix);

		valider = new JButton("valider");
		valider.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Ardoise.ajouter(new Boisson(nom, image, 0, 0, prix, "snack"));
				
				Ardoise.afficher_case_conso();
				conf_conso.dispose();
				Fen_conso.fen_conso().dispose();
				Fen_conso.fen_conso();
				Fen_conso.setNb_fen(Fen_conso.getNb_fen()-1);
			}
		});
		
		lblimage = new JLabel("image");
		panel.add(lblimage);
		
		panel_1 = new JPanel();
		panel.add(panel_1);
		panel.add(valider);
		
		JButton annuler = new JButton("annuler");
		annuler.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				conf_conso.dispose();
				Fen_conso.fen_conso().getValider().setEnabled(true);
				Fen_conso.fen_conso().getTxtImage().setEnabled(true);
				Fen_conso.fen_conso().getTextField().setEnabled(true);
				Fen_conso.fen_conso().getTextField_1().setEnabled(true);
				Fen_conso.fen_conso().getJtf().setEnabled(true);
				Fen_conso.fen_conso().getTxtPrix().setEnabled(true);
				Fen_conso.fen_conso().getComboBox().setEnabled(true);
				Fen_conso.fen_conso().getRbBoisson().setEnabled(true);
				Fen_conso.fen_conso().getRbSnack().setEnabled(true);
			}
		});
		panel.add(annuler);
	}

}
