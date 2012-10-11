package doublon;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class Fen_doublons extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Doublons_Group_Pane panel = new Doublons_Group_Pane();

	/**
	 * Create the frame.
	 */
	public Fen_doublons() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel1 = new JPanel();
		contentPane.add(panel1, BorderLayout.CENTER);
		panel1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane(panel);
		panel1.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel(panel.getNombreDeDoublons()+" doublons détectés");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new Fen_Doublon_Listener(this));
		panel_1.add(btnSupprimer);
	}

	public static Doublons_Group_Pane getPanel() {
		return panel;
	}

	public static void setPanel(Doublons_Group_Pane panel) {
		Fen_doublons.panel = panel;
	}

}
