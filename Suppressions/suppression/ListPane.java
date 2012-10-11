package suppression;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class ListPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ArrayList<Suppr_client_Pane> Tab = new ArrayList<Suppr_client_Pane>();

	/**
	 * Create the panel.
	 */
	public ListPane() {
		Tab.clear();
		for(int i=0; i<Select_suppr.getListe().size(); i++){
			Tab.add(new Suppr_client_Pane(Select_suppr.getListe().get(i)));
		}
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorth = new JPanel();
		add(panelNorth, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel(Tab.size()+" comptes sélèctionné");
		panelNorth.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		for(int i=0; i<Tab.size(); i++){
			panel.add(Tab.get(i));
			panel.revalidate();
			panel.repaint();
		}
		
		JScrollPane scrollPane = new JScrollPane(panel);
		panel.setLayout(new GridLayout(0, 1, 5, 5));
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel panelSouth = new JPanel();
		add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		

	}

	public static ArrayList<Suppr_client_Pane> getTab() {
		return Tab;
	}

}
