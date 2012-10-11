package source;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class Case_conso extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pan_image pan_image;
	private JButton bouton_conso;
	/**
	 * Create the panel.
	 */
	public Case_conso(Boisson conso) {
		
		bouton_conso = conso.getBouton();
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		
		pan_image = new Pan_image(conso.getImg());
		panel_1.add(pan_image);
		pan_image.setPreferredSize(new Dimension(40, 40));
		
		JPanel panel_2 = new JPanel();
		panel_2.add(bouton_conso);
		JScrollPane scrollPane = new JScrollPane(panel_2);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.SOUTH);
		

	}

}
