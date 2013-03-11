package load.control;


import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import main.vue.Fenetre_principale;
import source.Abonnes;
import source.Client;
import bdd.model.Connect;
import doublon.Fen_doublons;

public class ProgressBarLoader extends JPanel
                             implements  PropertyChangeListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JProgressBar progressBar;
    private Task task;
    private JFrame frame;
    
    class Task extends SwingWorker<Void, Void> {
    	private int current;
    	
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {
            try {
    			Abonnes.miseAzero();//On vide la liste des abonnees de la memoire vive
    			//ainsi que la liste des doublons si elle n'est pas deja vide
    			
    			Fenetre_principale.MiseAJour.clear();//on supprimme l'ancien affichage
    			//Utilisateur user = new Utilisateur();
    			//Utilisateur.dir_root = user.getNom()+"/";
            	//On autorise la mise � jour des donn�es et la mise � jour de l'affichage
    			Connection connection = Connect.getInstance();
    			Statement state = connection.createStatement();
    			ResultSet result = state.executeQuery("SELECT * FROM "+Connect.getTableEleve());
        	      
        	      //ResultSet result = state.executeQuery("SELECT * FROM "+Connect.getTableEleve());
        	      
        	      Connect.setConnecting(true);//on empeche l'affichage des messages d'erreure doublon
        			//pendant l'instenciation des clients de la base
        			progressBar.setMaximum(Connect.getNbComptes());
        			
        			while(result.next()){
        				Client cli = new Client(result.getInt(Connect.getColumn_idEleve()), result.getString(Connect.getColumn_nomEleve()), result.getString(Connect.getColumn_prenomEleve()), result.getFloat(Connect.getColumn_soldeEleve()), result.getInt(Connect.getColumn_promoEleve()));
        				Abonnes.ajouter(cli);
        				current += 1;
        				Connect.setAvancement(Connect.getAvancement() + 1);
        				//Connect.getConnect().updateObservateur();
        				//progress = (Integer) ((current/maximum)*100);
        				//setProgress(progress);
        				progressBar.setValue(progressBar.getValue()+1);
        			}
        			Connect.setConnecting(false);//à la fin de l'instanciation, on permet
        			//l'affichage du message d'erreure en cas d'ajout de client "à la main"
        			
        			if(!Abonnes.getDoublons().isEmpty()){
        				//on met la fenetre de gestion des doublons!!!
        				new Fen_doublons();
        			}
    			result.close();
    			state.close();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            setCursor(null); //turn off the wait cursor
            current = 0;
            Connect.setRunning(false);
            Fenetre_principale.getFenetre_principale().setVisible(true);
            frame.dispose();
        }
		public int getCurrent() {
			//this.updateObservateur();
			return current;
		}

		public void setCurrent(int current) {
			this.current = current;
			//this.updateObservateur();
		}
    }

    public ProgressBarLoader(JFrame f) {
        super(new BorderLayout());
        
        frame = f;
        
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        JPanel panel = new JPanel();
        panel.add(progressBar);

        add(panel, BorderLayout.PAGE_START);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //Instances of javax.swing.SwingWorker are not reusuable, so
        //we create new instances as needed.
        task = new Task();
        task.addPropertyChangeListener(this);
        task.execute();
        
    }

	/**
     * Invoked when task's progress property changes.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);
        } 
    }


    /**
     * Create the GUI and show it. As with all GUI code, this must run
     * on the event-dispatching thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Chargement");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Fenetre_principale.getFenetre_principale().dispose();
        
        //Create and set up the content pane.
        JComponent newContentPane = new ProgressBarLoader(frame);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}