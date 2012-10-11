package source.vue;
import javax.swing.JFrame;


public class Conf_conso extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CP_conf_boisson contentPane_boison;
	private CP_conf_snack contentPane_snack;
	private static Conf_conso conf;
	private Fen_conso conso;
	private String type;
	/**
	 * Create the frame.
	 */
	private Conf_conso(Fen_conso fen_conso, String n, float v, float d, String t, float p, String img) {
		setAlwaysOnTop(true);
		
		type = t;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 250, 220);
		setLocationRelativeTo(null);
		setVisible(true);
		
		if(type == "snack"){
			contentPane_snack = new CP_conf_snack(this, conso, n, v, d, t, p, img);
			getContentPane().add(contentPane_snack);}
		else{contentPane_boison = new CP_conf_boisson(this, conso, n, v, d, t, p, img);
		getContentPane().add(contentPane_boison);}
		
	}
	
	public static Conf_conso conf(Fen_conso fen_conso, String n, float v, float d, String t, float p, String img){
		conf = new Conf_conso(fen_conso, n, v, d, t, p, img);
	return conf;
}

}
