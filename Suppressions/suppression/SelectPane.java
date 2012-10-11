package suppression;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;


public class SelectPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int promo = Select_suppr.getPromo();
	private int action = Select_suppr.getAction();
	private float solde = Select_suppr.getSolde();
	private float soldeInf = Select_suppr.getSoldeInf();
	private float soldeSup = Select_suppr.getSoldeSup();

	private static JComboBox comboBox;
	
	private static SpinnerNumberModel modelPromo;
	private static SpinnerNumberModel modelSolde1;
	private static SpinnerNumberModel modelSolde2;
	private static SpinnerNumberModel modelSolde3;
	private JSpinner spinner = new JSpinner();
	private JSpinner spinner_1 = new JSpinner();
	private JSpinner spinner_2 = new JSpinner();
	private JSpinner spinner_3 = new JSpinner();
	private JPanel panel_2 = new JPanel();
	private JPanel panelOpt1;
	private JPanel panelOpt2;
	private JLabel label;
	private JLabel lblEt;
	private JLabel label_1;
	
	/**
	 * Create the panel.
	 */
	public SelectPane() {
		
		modelPromo = new SpinnerNumberModel(promo, null, null, new Integer(1));
		modelSolde1 = new SpinnerNumberModel(solde, null, null, new Float(1));
		modelSolde2 = new SpinnerNumberModel(soldeInf, null, null, new Float(1));
		modelSolde3 = new SpinnerNumberModel(soldeSup, null, null, new Float(1));
		
		
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblSlctionDesComptes = new JLabel("Séléction des comptes à supprimer");
		lblSlctionDesComptes.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSlctionDesComptes, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblPromo = new JLabel("promo : ");
		lblPromo.setHorizontalAlignment(JLabel.CENTER);
		panel_1.add(lblPromo);
		
		spinner = new JSpinner();
		panel_1.add(spinner);
		spinner.setPreferredSize(new Dimension(80, 30));
		spinner.setModel(modelPromo);
		
		
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblSolde = new JLabel("solde : ");
		lblSolde.setBounds(50, 12, 52, 15);
		panel_4.add(lblSolde);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		
		comboBox = new JComboBox();
		comboBox.addItem("-");
		comboBox.addItem("=");
		comboBox.addItem(">=");
		comboBox.addItem("<=");
		comboBox.addItem("entre");
		comboBox.setSelectedIndex(action+1);//voir la classe Select_suppr variables final !!!
		comboBox.addActionListener(new ItemState());
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_5.add(comboBox);
		
		panelOpt2 = new JPanel();
		//panel_6.add(panelOpt2);
		panelOpt2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		spinner_2.setPreferredSize(new Dimension(80, 30));
		spinner_2.setModel(modelSolde2);
		panelOpt2.add(spinner_2);
		
		lblEt = new JLabel("et");
		panelOpt2.add(lblEt);
		
		
		spinner_3.setPreferredSize(new Dimension(80, 30));
		spinner_3.setModel(modelSolde3);
		panelOpt2.add(spinner_3);
		
		label_1 = new JLabel("€");
		panelOpt2.add(label_1);
		
		panelOpt1 = new JPanel();
		
		if(comboBox.getSelectedIndex() == 0){
			spinner_1.setEnabled(false);
			panel_2.add(panelOpt1);
		}
		else if(comboBox.getSelectedIndex() == 4){
			panel_2.add(panelOpt2);
		}
		else{
			panel_2.add(panelOpt1);
		}
		
		panelOpt1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		spinner_1.setPreferredSize(new Dimension(80, 30));
		panelOpt1.add(spinner_1);
		spinner_1.setModel(modelSolde1);
		
		label = new JLabel("€");
		panelOpt1.add(label);

	}
	
	public static void process(){
		new Select_suppr(((Integer) modelPromo.getValue()).intValue(), ((Float) modelSolde1.getValue()).floatValue(), ((Float) modelSolde2.getValue()).floatValue(), ((Float) modelSolde3.getValue()).floatValue());
		
	}

	/**
     * Classe interne implémentant l'interface ItemListener
     */
    class ItemState implements ActionListener{

            public void actionPerformed(ActionEvent e) {
                    String item = comboBox.getSelectedItem().toString();
                    
                    if(item.equals("-")){
                    	Select_suppr.setAction(Select_suppr.NULL);
                    	if(panel_2.isAncestorOf(panelOpt2)){
                    		panel_2.remove(panelOpt2);
                    		panel_2.add(panelOpt1);
                    	}
                    	panel_2.add(panelOpt1);
                    	spinner_1.setEnabled(false);
                    	panel_2.revalidate();
                    	panel_2.repaint();
                    }
                    else if(item.equals("=")){
                    	Select_suppr.setAction(Select_suppr.EGAL_A);
                    	if(panel_2.isAncestorOf(panelOpt2)){
                    		panel_2.remove(panelOpt2);
                    		panel_2.add(panelOpt1);
                    	}
                    	spinner_1.setEnabled(true);
                    	panel_2.revalidate();
                    	panel_2.repaint();
                    }
                    else if(item.equals("<=")){
                    	Select_suppr.setAction(Select_suppr.INFERIEUR_A);
                    	if(panel_2.isAncestorOf(panelOpt2)){
                    		panel_2.remove(panelOpt2);
                    		panel_2.add(panelOpt1);
                    	}
                    	spinner_1.setEnabled(true);
                    	panel_2.revalidate();
                    	panel_2.repaint();
                    }
                    else if(item.equals(">=")){
                    	Select_suppr.setAction(Select_suppr.SUPPERIEUR_A);
                    	if(panel_2.isAncestorOf(panelOpt2)){
                    		panel_2.remove(panelOpt2);
                    		panel_2.add(panelOpt1);
                    	}
                    	spinner_1.setEnabled(true);
                    	panel_2.revalidate();
                    	panel_2.repaint();
                    }
                    else if(item.equals("entre")){
                    	Select_suppr.setAction(Select_suppr.COMPRI_ENTRE);
                    	if(panel_2.isAncestorOf(panelOpt1)){
                    		panel_2.remove(panelOpt1);
                    		panel_2.add(panelOpt2);
                    	}
                    	panel_2.revalidate();
                    	panel_2.repaint();
                    }
                    	
            }
    }
}
