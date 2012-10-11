package droits.vue;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import droit.model.Liste_Utilisateurs;
import droit.observable.Observateur;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;

public class Fen_Liste_utilisateurs extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private Liste_Utilisateurs listUtil;
	private ArrayList<Pane_utilisateur> pan_list;

	/**
	 * Create the frame.
	 */
	public Fen_Liste_utilisateurs() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		listUtil = new Liste_Utilisateurs();
		
		JMenuBar menuBar = new JMenuBar();
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		JMenuItem mntmAjouter = new JMenuItem("ajouter");
		mntmAjouter.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Fen_ajout(listUtil);
			}
			
		});
		menuBar.add(mntmAjouter);
		
		panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new AppliqueModifs());
		panel_1.add(btnValider);
		
		JScrollPane scrollPane = new JScrollPane(panel);
		
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		reafficher(listUtil);
		
		listUtil.addObservateur(new Observateur(){

			@Override
			public void update(Liste_Utilisateurs ListUtil) {
				// TODO Auto-generated method stub
				reafficher(ListUtil);
			}
			
		});
	}

	public void reafficher(Liste_Utilisateurs List){
		pan_list = new ArrayList<Pane_utilisateur>();
		panel.removeAll();
		
		GridBagLayout gbl_panel = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		
		panel.setLayout(gbl_panel);
		
		for(int i=0; i<List.getListe_id().size(); i++){
			c.gridx = 0;
			c.gridy = i;
			Pane_utilisateur pan = new Pane_utilisateur(i, List);
			pan_list.add(pan);
			pan.setBounds(-i*35, 0, 30, pan.getWidth());
			pan.addObservateur(new Observateur(){

				@Override
				public void update(Liste_Utilisateurs ListUtil) {
					// TODO Auto-generated method stub
					reafficher(ListUtil);
				}
				
			});
			panel.add(pan, c);
		}
		panel.revalidate();
		panel.repaint();
	}
	
	public class AppliqueModifs implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			for(int i=0; i<pan_list.size(); i++){
				listUtil.modifier_droit_utilisateur(listUtil.getListe_id().get(i), pan_list.get(i).getComboBox().getSelectedItem().toString());
			}
			dispose();
		}
		
	}
}
