package doublon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import bdd.model.Connect;



public class Fen_Doublon_Listener implements ActionListener {

	private JFrame frame;
	
	public Fen_Doublon_Listener(JFrame f){
		frame = f;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Fen_doublons.getPanel();
		// TODO Auto-generated method stub
		ArrayList<Doublon_Pane> liste = Doublons_Group_Pane.getListe();
		for(int i = 0; i< liste.size(); i++){
			if(liste.get(i).getRdbtnNewRadioButton().isSelected()){
				liste.get(i).getClient().supprimer();
			}
		}
		frame.dispose();
		Connect.init();
		
	}

}
