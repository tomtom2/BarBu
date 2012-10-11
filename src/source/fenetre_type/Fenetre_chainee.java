package source.fenetre_type;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import source.fenetres.interfaces.Fermable;
import source.fenetres.interfaces.Observable;
import source.fenetres.interfaces.ObservateurChainage;

public class Fenetre_chainee extends JFrame implements WindowListener, Observable, Fermable {

	//Notre collection d'observateurs !
	private ArrayList<ObservateurChainage> listObservateur = new ArrayList<ObservateurChainage>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public Fenetre_chainee() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
	}

	@Override
	public void addObservateurChainage(ObservateurChainage obs) {
		// TODO Auto-generated method stub
		this.getListObservateur().add(obs);
	}

	@Override
	public void delObservateur() {
		// TODO Auto-generated method stub
		this.setListObservateur(new ArrayList<ObservateurChainage>());
	}

	@Override
	public void updateObservateur() {
		// TODO Auto-generated method stub
		for(ObservateurChainage obs : this.getListObservateur() ){
			obs.update();
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fermeture_en_chaine() {
		// TODO Auto-generated method stub
		
	}

	public void setListObservateur(ArrayList<ObservateurChainage> listObservateur) {
		this.listObservateur = listObservateur;
	}

	public ArrayList<ObservateurChainage> getListObservateur() {
		return listObservateur;
	}

}
