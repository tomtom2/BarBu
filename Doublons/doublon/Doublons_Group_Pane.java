package doublon;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JSeparator;

import source.Abonnes;


public class Doublons_Group_Pane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ArrayList<Doublon_Pane> liste = new ArrayList<Doublon_Pane>();
	private int nombreDeDoublons = 0;

	/**
	 * Create the panel.
	 */
	public Doublons_Group_Pane() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		for(int i = 0; i< Abonnes.getDoublons().size(); i++){
			liste.add(new Doublon_Pane(Abonnes.getDoublons().get(i)));
		}
		
		boolean newblock = false;
		for(int i = 0; i< liste.size(); i++){
			if(liste.get(i).getClient().getCle_id() == -1){
				JSeparator separator = new JSeparator();
				add(separator);
				newblock = true;
			}
			else{
				if(newblock){
					liste.get(i).getRdbtnNewRadioButton().setSelected(false);
					newblock = false;
				}
				
				add(liste.get(i));
				nombreDeDoublons++;
			}
		}

		
	}

	public int getNombreDeDoublons() {
		return nombreDeDoublons;
	}

	public static ArrayList<Doublon_Pane> getListe() {
		return liste;
	}

}
