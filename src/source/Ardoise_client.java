package source;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;


public class Ardoise_client extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Ardoise_client ard_cli;
	private static int nb_fen = 0;
	private JPanel contentPane;
	private JTabbedPane bieresPane;
	private static JPanel blanchesPane;
	private static JPanel blondesPane;
	private static JPanel ambreesPane;
	private static JPanel brunesPane;
	private static JPanel autresPane;
	
	private static ArrayList<Boisson> blanches = new ArrayList<Boisson>();
    private static ArrayList<Boisson> blondes = new ArrayList<Boisson>();
    private static ArrayList<Boisson> ambrees = new ArrayList<Boisson>();
    private static ArrayList<Boisson> brunes = new ArrayList<Boisson>();
    private static ArrayList<Boisson> autre = new ArrayList<Boisson>();
    
	/**
	 * Create the frame.
	 */
	private Ardoise_client(Client client) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		Ardoise.getTab();
	    blanches = Ardoise.getBlanches();
	    blondes = Ardoise.getBlondes();
	    ambrees = Ardoise.getAmbrees();
	    brunes = Ardoise.getBrunes();
	    autre = Ardoise.getAutre();
		
		JMenuBar menuBar = new JMenuBar();
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		JMenuItem mntmLister = new JMenuItem("lister");
		menuBar.add(mntmLister);
		
		bieresPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(bieresPane, BorderLayout.CENTER);
		
		blondesPane = new JPanel();
		blondesPane.setLayout(new GridLayout(0, 5, 5, 5));
		
		ambreesPane = new JPanel();
		ambreesPane.setLayout(new GridLayout(0, 5, 5, 5));
		
		brunesPane = new JPanel();
		brunesPane.setLayout(new GridLayout(0, 5, 5, 5));
		
		autresPane = new JPanel();
		autresPane.setLayout(new GridLayout(0, 5, 5, 5));
		
		blanchesPane = new JPanel();
		blanchesPane.setLayout(new GridLayout(0, 5, 5, 5));
		
		JScrollPane scrollPane_1 = new JScrollPane(blanchesPane);
		bieresPane.addTab("blanches", null, scrollPane_1, null);
		
		JScrollPane scrollPane = new JScrollPane(blondesPane);
		bieresPane.addTab("blondes", null, scrollPane, null);
		
		JScrollPane scrollPane_2 = new JScrollPane(ambreesPane);
		bieresPane.addTab("ambrees", null, scrollPane_2, null);
		
		JScrollPane scrollPane_3 = new JScrollPane(brunesPane);
		bieresPane.addTab("brunes", null, scrollPane_3, null);
		
		JScrollPane scrollPane_4 = new JScrollPane(autresPane);
		bieresPane.addTab("autres", null, scrollPane_4, null);
		setVisible(true);
		//init();
		afficher_case_conso_client(client);
		contentPane.revalidate();
	}
	
	public static Ardoise_client affiche_ard(Client cli){
		if(nb_fen == 0){
			ard_cli = new Ardoise_client(cli);
			nb_fen++;
		}
		return ard_cli;
	}
	
	public static void fermer(){
		ard_cli.dispose();
		nb_fen--;
	}
	
	public static void afficher_case_conso_client(Client cli){
		for(int i = 0; i < blanches.size(); i++){
			JPanel pan = new JPanel();
			pan.setPreferredSize(new Dimension(50, 95));
			pan.add(new JScrollPane(new Case_conso_client(blanches.get(i), cli)));
			blanchesPane.add(pan);
		}
		
		for(int i = 0; i < blondes.size(); i++){
			JPanel pan = new JPanel();
			pan.setPreferredSize(new Dimension(50, 95));
			pan.add(new JScrollPane(new Case_conso_client(blondes.get(i), cli)));
			blondesPane.add(pan);
		}
		
		for(int i = 0; i < ambrees.size(); i++){
			JPanel pan = new JPanel();
			pan.setPreferredSize(new Dimension(50, 95));
			pan.add(new JScrollPane(new Case_conso_client(ambrees.get(i), cli)));
			ambreesPane.add(pan);
		}
		
		for(int i = 0; i < brunes.size(); i++){
			JPanel pan = new JPanel();
			pan.setPreferredSize(new Dimension(50, 95));
			pan.add(new JScrollPane(new Case_conso_client(brunes.get(i), cli)));
			brunesPane.add(pan);
		}
		
		for(int i = 0; i < autre.size(); i++){
			JPanel pan = new JPanel();
			pan.setPreferredSize(new Dimension(50, 95));
			pan.add(new JScrollPane(new Case_conso_client(autre.get(i), cli)));
			autresPane.add(pan);
		}
		
	}

}