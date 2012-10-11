package source;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import source.vue.Compt_client;

import bdd.model.Connect;


public class CreditListener extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private Client client;
	private static CreditListener listener;
	/**
	 * Create the frame.
	 */
	public CreditListener(Client cl) {
		this.client = cl;
		this.setTitle(client.getPrenom()+" "+client.getNom());
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 200, 130);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 5, 5));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 2, 5, 5));
		
		JLabel lblcredit = new JLabel("crediter de :");
		panel.add(lblcredit);
		
		textField = new JTextField("0");
		panel.add(textField);
		textField.setColumns(10);
		textField.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				int code = arg0.getKeyCode();
				
				if(code == KeyEvent.VK_ENTER){
					float dbt = verif_form_float(textField.getText());
					try {
						Suivi_client.enreg_transaction(client, dbt);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					listener.dispose();
					Connect.crediter(client, dbt);
					float cmp = client.getSolde() + dbt;
					float k = verif_form_float(""+cmp);
					client.setSolde(k);
					client.boutMaJ();
					
					//Abonnes.save();
					Compt_client.cp_client(client);
					//JOptionPane.showMessageDialog(null, "il reste "+client.getSolde()+"€ sur ce compt", client.getPrenom()+" "+client.getNom(), JOptionPane.INFORMATION_MESSAGE);
				}
				else if(code == KeyEvent.VK_ESCAPE){
					dispose();
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		textField.addFocusListener(new SelectJTF(textField));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new Crediter());
		panel_1.add(btnOk);
	}
	
	public static CreditListener credit(Client client){
		//ne doit pas se declancher si client a deja sa fenetre compt ouverte !!!
		if(listener != null){listener.dispose();}
		listener = new CreditListener(client);
			
		return listener;
	}
	
	public class Crediter implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			float dbt = verif_form_float(textField.getText());
			try {
				Suivi_client.enreg_transaction(client, dbt);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connect.crediter(client, dbt);
			listener.dispose();
			float cmp = client.getSolde() + dbt;
			float k = verif_form_float(""+cmp);
			client.setSolde(k);
			
			client.boutMaJ();
			//Abonnes.save();
			Compt_client.cp_client(client);
			//JOptionPane.showMessageDialog(null, "il reste "+client.getSolde()+"€ sur ce compt", client.getPrenom()+" "+client.getNom(), JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	public float verif_form_float(String txt){
		int nb_sep = 0;//nombre de séparateurs
		boolean ok = true;
		
		float nb = 0;
		
		String deg_tmp = "";
		NumberFormat formatter = new DecimalFormat("#0.00");
		String txt2 = suppr_virgule(txt);
		String s = formatter.format(Float.valueOf(txt2).floatValue());
		char[] num = s.toCharArray();
		for(int i=0; i<num.length; i++){
			if(i == 0 && num[0]=='-'){
				deg_tmp = "-";
			}
			else if(num[i]=='0'||num[i]=='1'||num[i]=='2'||num[i]=='3'||num[i]=='4'||num[i]=='5'||num[i]=='6'||num[i]=='7'||num[i]=='8'||num[i]=='9'){
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
	
	public String suppr_virgule(String txt){
		String nb = "";
		
		String deg_tmp = "";
		
		char[] num = txt.toCharArray();
		for(int i=0; i<num.length; i++){
			if(num[i]==','||num[i]=='.'){deg_tmp = deg_tmp + ".";}
			else{deg_tmp = deg_tmp + String.valueOf(num[i]);}
			}
		
		nb = deg_tmp;
		
		return nb;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
