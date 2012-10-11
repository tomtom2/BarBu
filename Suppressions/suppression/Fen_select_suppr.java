package suppression;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import source.Client;


public class Fen_select_suppr extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	
	private JButton btnValider = new JButton("Valider");
	private JButton btnRetour = new JButton("< Retour");
	private JButton btnSupprimer = new JButton("Supprimer");
	

	/**
	 * Create the frame.
	 */
	public Fen_select_suppr() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		
		btnValider.addActionListener(new Validation());
		
		btnRetour.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				panel.removeAll();
				contentPane.removeAll();
				contentPane.add(panel, BorderLayout.SOUTH);
				panel.add(btnValider);
				contentPane.add(new SelectPane());
				contentPane.revalidate();
				contentPane.repaint();
			}
			
		});
		
		btnSupprimer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ArrayList<Client> L = new ArrayList<Client>();
				
				for(int i=0; i<ListPane.getTab().size(); i++){
					if(ListPane.getTab().get(i).getCheckBox().isSelected()){
						L.add(ListPane.getTab().get(i).getClient());
					}
				}
				new Suppression_pass(L);
				dispose();
			}
			
		});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.add(new SelectPane());
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		panel.add(btnValider);
		
		setVisible(true);
		pack();
	}
	
	class Validation implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			SelectPane.process();
			panel.removeAll();
			contentPane.removeAll();
			contentPane.add(panel, BorderLayout.SOUTH);
			contentPane.add(new ListPane());
			panel.add(btnRetour);
			btnSupprimer.setEnabled(true);
			panel.add(btnSupprimer);
			if(Select_suppr.getListe().isEmpty()){
				btnSupprimer.setEnabled(false);
			}
			contentPane.revalidate();
			contentPane.repaint();
		}
		
	}

	
}
