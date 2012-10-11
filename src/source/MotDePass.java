package source;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.vue.Fenetre_principale;
import bdd.model.Connect;
import bdd.utilisateur.Utilisateur;


public class MotDePass extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane1;
	private static String mot_de_pass = "toctoc";
	private JPasswordField passwordField;
	private JMenuBar menuBar;
	private JPanel panel;
	private JMenuItem mntmNewMenuItem;
	
	private static boolean first_time = Utilisateur.first_time;
	private static boolean first_time_logiciel = true;
	private JMenu mnModifier;
	private JMenuItem mntmUtilisateur;
	

	/**
	 * Create the frame.
	 */
	public MotDePass(String title) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 300, 100);
		setLocationRelativeTo(null);
		setTitle(title);
		//setAlwaysOnTop(true);
		setResizable(false);
		
		
		contentPane1 = new JPanel();
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane1);
		contentPane1.setLayout(new BorderLayout(0, 0));
		
		menuBar = new JMenuBar();
		contentPane1.add(menuBar, BorderLayout.NORTH);
		
		mnModifier = new JMenu("modifier");
		menuBar.add(mnModifier);
		
		mntmNewMenuItem = new JMenuItem("password");
		mnModifier.add(mntmNewMenuItem);
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
		
		if(first_time_logiciel){
			//first_time_logiciel = false;
			mntmUtilisateur = new JMenuItem("utilisateur");
			mntmUtilisateur.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					File f = new File(Utilisateur.filepath);
					f.delete();
					new Utilisateur();
					//new Inscription_vue();
					dispose();
				}
				
			});
			mnModifier.add(mntmUtilisateur);
		}
		else{
			Fenetre_principale.getFenetre_principale().setEnabled(false);
			Fenetre_principale.getFenetre_principale().setVisible(false);
		}
		
		
		mntmNewMenuItem.addActionListener(new Modifier());
		
		panel = new JPanel();
		contentPane1.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblMotDePasse = new JLabel("mot de passe : ");
		panel.add(lblMotDePasse);
		
		passwordField = new JPasswordField();
		panel.add(passwordField);
		passwordField.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				int code = arg0.getKeyCode();
				
				if(code == KeyEvent.VK_ENTER){
					String input = new String(passwordField.getPassword());
		            if (input.equals(mot_de_pass)) {
		            	dispose();
		            	first_time_logiciel = false;
		            	javax.swing.SwingUtilities.invokeLater(new Runnable() {
						    public void run() {
						    	
						    	/*if(first_time){
						    		new File(Utilisateur.dir_root+"comptes/privat/").mkdirs();
									new File(Utilisateur.dir_root+"comptes/historiques/").mkdir();
									new File(Utilisateur.dir_root+"images/").mkdir();
						    	}*/
						    	
						    	Fenetre_principale.getFenetre_principale().setEnabled(true);
				            	Fenetre_principale.getFenetre_principale().setVisible(true);
						    }
		            	});
		            	
		            	
		            	if(first_time){
		            		new Abonnes();
		            		first_time = false;
		            	}
		            	
		            }
		            else {
		            	JOptionPane.showMessageDialog(
		                    null, "Invalid password. Try again.",
		                    "Error Message",
		                    JOptionPane.ERROR_MESSAGE);
		            }

				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		try {
			load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		contentPane1.repaint();
		pack();
		setVisible(true);
	}
	
	
	
	public static void setMot_de_pass(String motDePass) {
		mot_de_pass = motDePass;
	}



	public static String getMot_de_pass() {
		return mot_de_pass;
	}



	public class Modifier implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			new ModifierIntFrame();
		}
		
	}

	public static void load() throws IOException{
		Statement stat;//on prend le mot de pass correspondanr à la clef primaire créée de la BDD
		try {
			stat = Connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		    ResultSet res = stat.executeQuery("SELECT code FROM utilisateurs WHERE id = "+Utilisateur.getId());
		    if(res.next()){
		    	mot_de_pass = res.getString("code");
		    }
		    
	          res.close();
	          stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}



	public static void setMotDePass_BDD(String text) {
		// TODO Auto-generated method stub
		Statement state;
		try {
			state = Connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		    state.executeUpdate("UPDATE utilisateurs SET code = '"+text+"' WHERE id = "+Utilisateur.getId());
		    	//on insert l'utilisateur avec les droits minimums
	          
	          state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Utilisateur();
	}

}
