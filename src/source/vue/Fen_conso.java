package source.vue;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import source.Ardoise;
import source.Boisson;


public class Fen_conso extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private static int nb_fen = 0;
	private static Fen_conso fen_conso;
	private Boisson conso;
	private JTextField txtImage;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField jtf;
	private JTextField txtPrix;
	private JComboBox comboBox;
	private JButton valider;
	private JButton modifier;
	private JRadioButton rbBoisson;
	private JRadioButton rbSnack;
	private ButtonGroup bg = new ButtonGroup();

	private JLabel lblImage;
	private JLabel lblPrix;


	
	/**
	 * Create the frame.
	 */
	private Fen_conso() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 250, 220);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(2, 2));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(8, 2, 5, 5));
		
		rbBoisson = new JRadioButton("boisson");
		rbBoisson.setSelected(true);
		rbBoisson.addActionListener(new StateListener());
		panel.add(rbBoisson);
		
		rbSnack = new JRadioButton("snack");
		rbSnack.addActionListener(new StateListener());
		panel.add(rbSnack);
		
		bg.add(rbBoisson);
		bg.add(rbSnack);
		
		JLabel nom = new JLabel("nom");
		panel.add(nom);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("volume (cl)");
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("%alcool");
		panel.add(lblNewLabel_2);
		
		jtf = new JTextField();
		panel.add(jtf);
		jtf.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("type");
		panel.add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.addItem("blanche");
		comboBox.addItem("blonde");
		comboBox.addItem("ambree");
		comboBox.addItem("brune");
		comboBox.addItem("autre");
		panel.add(comboBox);
		
		lblPrix = new JLabel("prix");
		panel.add(lblPrix);
		
		txtPrix = new JTextField();
		panel.add(txtPrix);
		txtPrix.setColumns(10);
		
		lblImage = new JLabel("image");
		panel.add(lblImage);
		
		txtImage = new JTextField();
		txtImage.setText("image.jpg");
		panel.add(txtImage);
		txtImage.setColumns(10);

		valider = new JButton("valider");
		valider.addActionListener(new Valider());
		panel.add(valider);
		
		JButton annuler = new JButton("annuler");
		annuler.addActionListener(new Annuler());
		panel.add(annuler);
	}
	
	private Fen_conso(Boisson cons) {
		this.conso = cons;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 250, 220);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(2, 2));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(8, 2, 5, 5));
		
		JMenuBar menuBar = new JMenuBar();
		getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenuItem suppr = new JMenuItem("supprimer");
		suppr.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Fen_suppr(conso);
				dispose();
			}
			
		});
		menuBar.add(suppr);
		
		rbBoisson = new JRadioButton("boisson");
		rbBoisson.setSelected(true);
		//rbBoisson.setEnabled(false);
		rbBoisson.addActionListener(new StateListener());
		
		rbSnack = new JRadioButton("snack");
		rbSnack.setSelected(false);
		//rbSnack.setEnabled(false);
		rbSnack.addActionListener(new StateListener());
		
		if(conso.getType().equals("snack")){
			rbBoisson.setSelected(false);
			rbSnack.setSelected(true);
		}
		panel.add(rbBoisson);
		panel.add(rbSnack);
		
		bg.add(rbBoisson);
		bg.add(rbSnack);
		
		JLabel nom = new JLabel("nom");
		panel.add(nom);
		
		textField = new JTextField(conso.getNom());
		panel.add(textField);
		//textField.setEnabled(false);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("volume (cl)");
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField(""+conso.getVolum());
		panel.add(textField_1);
		//textField_1.setEnabled(false);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("%alcool");
		panel.add(lblNewLabel_2);
		
		jtf = new JTextField(""+conso.getDeg());
		panel.add(jtf);
		//jtf.setEnabled(false);
		jtf.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("type");
		panel.add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.addItem("blanche");
		comboBox.addItem("blonde");
		comboBox.addItem("ambree");
		comboBox.addItem("brune");
		comboBox.addItem("autre");
		
		if(conso.getType().equals("blanche")){
			comboBox.setSelectedItem("blanche");
		}
		else if(conso.getType().equals("blonde")){
			comboBox.setSelectedItem("blonde");
		}
		else if(conso.getType().equals("ambree")){
			comboBox.setSelectedItem("ambree");
		}
		else if(conso.getType().equals("brune")){
			comboBox.setSelectedItem("brune");
		}
		else{comboBox.setSelectedItem("autre");}
		
		panel.add(comboBox);
		//comboBox.setEnabled(false);
		
		lblPrix = new JLabel("prix");
		panel.add(lblPrix);
		
		txtPrix = new JTextField(""+conso.getPrix());
		panel.add(txtPrix);
		//txtPrix.setEnabled(false);
		txtPrix.setColumns(10);
		
		lblImage = new JLabel("image");
		panel.add(lblImage);
		
		txtImage = new JTextField(conso.getImg());
		//txtImage.setText("image.png");
		panel.add(txtImage);
		//txtImage.setEnabled(false);
		txtImage.setColumns(10);
		
		modifier = new JButton("modifier");
		modifier.addActionListener(new Modifier());
		panel.add(modifier);
		
		JButton annuler = new JButton("annuler");
		annuler.addActionListener(new Annuler());
		panel.add(annuler);
	}
	
	public class Valider implements ActionListener{

		/**
		 * 
		 */
		private String nom = "";
		private float volum = 0;
		private float deg = 0;
		private float prix = 0;
		private String type = "";
		private String image = "";
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			nom = textField.getText().replace(' ', '_');
			
			if(rbBoisson.isSelected()){
			//on verifie le format du float pour le volume
			volum = verif_form_float(textField_1.getText());
			
			//on verifie le format du float pour le degré d'alcool
			deg = verif_form_float(jtf.getText());
			
			type = comboBox.getSelectedItem().toString();
			}
			
			prix = verif_form_float(txtPrix.getText());
			
			image = txtImage.getText().replace(' ', '_');
			
			boolean boissonOK = false;
			boolean snackOK = false;
			if(rbBoisson.isSelected() && nom != "" && volum != 0 && prix != 0){
				boissonOK = true;
			}
			if(rbSnack.isSelected() && nom != "" && prix != 0){
				snackOK = true;
			}
			
			if(boissonOK){
				Conf_conso.conf(fen_conso, nom, volum, deg, type, prix, image);
			    valider.setEnabled(false);
			    txtImage.setEnabled(false);
				textField.setEnabled(false);
				textField_1.setEnabled(false);
				jtf.setEnabled(false);
				txtPrix.setEnabled(false);
				comboBox.setEnabled(false);
				rbBoisson.setEnabled(false);
				rbSnack.setEnabled(false);
			}
			if(snackOK){
				Conf_conso.conf(fen_conso, nom, 0, 0, "snack", prix, image);
			    valider.setEnabled(false);
			    txtImage.setEnabled(false);
				textField.setEnabled(false);
				//textField_1.setEnabled(false);
				//jtf.setEnabled(false);
				txtPrix.setEnabled(false);
				//comboBox.setEnabled(false);
				rbBoisson.setEnabled(false);
				rbSnack.setEnabled(false);
			}
		}
		
	}
	public class Annuler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			fen_conso.dispose();
			nb_fen--;
		}
		
	}
	
	public class StateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			/*on prevoit de charger la fenetre de confirmation
			et la methode de sauvegarde adequate*/
			if(rbSnack.isSelected()){
				textField_1.setEnabled(false);
				jtf.setEnabled(false);
				comboBox.setSelectedItem("autre");
				comboBox.setEnabled(false);
			}
			if(rbBoisson.isSelected()){
				textField_1.setEnabled(true);
				jtf.setEnabled(true);
				comboBox.setEnabled(true);
			}
		}
		
	}
	
	public class Modifier implements ActionListener{

		/**
		 * 
		 */
		private Boisson boissonAMod;
		private Boisson Mod;
		private String nom;
		private String image;
		private String type;
		private float deg;
		private float volum;
		private float prix;
		private boolean doublon = false;
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			nom = textField.getText();
			image = txtImage.getText();
			type = comboBox.getSelectedItem().toString();
			deg = verif_form_float(jtf.getText());
			volum = verif_form_float(textField_1.getText());
			prix = verif_form_float(txtPrix.getText());
			Mod = new Boisson(nom, image, volum, deg, prix, type);
			boissonAMod = conso;
			
			for(int i = 0; i<Ardoise.getTab().size(); i++){
				if(textField.getText().equals(Ardoise.getTab().get(i).getNom())){
					doublon = true;
				}
			}
			if(doublon){
				JOptionPane.showMessageDialog(null, nom+" existe deja !", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else{
			
			String chnom = boissonAMod.getNom()+" --> "+Mod.getNom();
			String chvolum = boissonAMod.getVolum()+" cl --> "+Mod.getVolum()+" cl";
			String chdeg = boissonAMod.getDeg()+"% --> "+Mod.getDeg()+"%";
			String chtype = boissonAMod.getType()+" --> "+Mod.getType();
			String chprix = boissonAMod.getPrix()+"€ --> "+Mod.getPrix()+"€";
			String chimage = boissonAMod.getImg()+" --> "+Mod.getImg();
			
			JOptionPane.showMessageDialog(null, chnom+"\n"+chvolum+"\n"+chdeg+"\n"+chtype+"\n"+chprix+"\n"+chimage+"\n", "modif sur "+boissonAMod.getNom(), JOptionPane.DEFAULT_OPTION);
			Ardoise.remove(boissonAMod);
			Ardoise.ajouter(Mod);
			Ardoise.afficher_case_conso();
			fermer();
			
		}
		}
		
	}
	
	public static Fen_conso fen_conso(){
		if(nb_fen == 0){
			fen_conso = new Fen_conso();
			nb_fen++;
		}
		return fen_conso;
	}
	public static Fen_conso fen_conso(Boisson boi){
		if(nb_fen == 0){
			fen_conso = new Fen_conso(boi);
			nb_fen++;
		}
		return fen_conso;
	}
	
	public static void fermer(){
		fen_conso.dispose();
		nb_fen--;
	}
	
	public float verif_form_float(String txt){
		int nb_sep = 0;//nombre de séparateurs
		boolean ok = true;
		
		float nb = 0;
		
		String deg_tmp = "";
		char[] num = txt.toCharArray();
		for(int i=0; i<num.length; i++){
			if(num[i]=='0'||num[i]=='1'||num[i]=='2'||num[i]=='3'||num[i]=='4'||num[i]=='5'||num[i]=='6'||num[i]=='7'||num[i]=='8'||num[i]=='9'){
				deg_tmp = deg_tmp + String.valueOf(num[i]);
			}
			else if(num[i]==','||num[i]=='.'){nb_sep++; deg_tmp = deg_tmp + ".";}
			else{JOptionPane.showMessageDialog(null, "Un nombre est composé de chiffres (0,1,2,3,4,5,6,7,8,9)\net eventuellement d'un point (.)\nou encore d'une virgule (,)\nmerci !", "Erreur", JOptionPane.ERROR_MESSAGE);
			ok = false;}
		}
		
	    if(nb_sep>1){JOptionPane.showMessageDialog(null, "tu y vas un peu fort sur les virgules, non?", "Erreur", JOptionPane.ERROR_MESSAGE);
		ok = false;}
	    
		if(ok){
			nb = Float.valueOf(deg_tmp).floatValue();
		}

		return nb;
	}

	
	
	public static int getNb_fen() {
		return nb_fen;
	}

	public static void setNb_fen(int nbFen) {
		nb_fen = nbFen;
	}

	public JButton getValider() {
		return valider;
	}

	public void setValider(JButton valider) {
		this.valider = valider;
	}

	public JTextField getTxtImage() {
		return txtImage;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public JTextField getJtf() {
		return jtf;
	}

	public JTextField getTxtPrix() {
		return txtPrix;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public JRadioButton getRbBoisson() {
		return rbBoisson;
	}

	public JRadioButton getRbSnack() {
		return rbSnack;
	}
	
	
}
