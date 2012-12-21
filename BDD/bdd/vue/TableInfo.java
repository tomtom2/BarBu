package bdd.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import bdd.interfaces.Observateur;
import bdd.model.Connect;
import bdd.model.Connection_valid;

public class TableInfo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_tableEleve;
	private JTextField textField_soldeEleve;
	private JTextField textField_promoEleve;
	private JTextField textField_nomEleve;
	private JTextField textField_prenomEleve;
	private JTextField textField_idEleve;
	
	private JTextField textField_tableTrans;
	private JTextField textField_idTrans;
	private JTextField textField_id_eleveTrans;
	private JTextField textField_operationTrans;
	private JTextField textField_montantTrans;
	private JTextField textField_dateTrans;
	private JTextField textField_idutilTrans;
	
	private ArrayList<JTextField> txtFieldsList= new ArrayList<JTextField>();
	
	private Connection_valid conn = new Connection_valid();
	
	/**
	 * Create the panel.
	 */
	public TableInfo() {
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("eleves", null, scrollPane, null);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(450, 50));
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		
		JLabel lblTable = new JLabel("table : ");
		panel_3.add(lblTable);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		
		textField_tableEleve = new JTextField();
		panel_4.add(textField_tableEleve);
		textField_tableEleve.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(6, 3, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		
		JLabel lblNewLabel = new JLabel("colonne");
		panel_5.add(lblNewLabel);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);
		
		JLabel lblNewLabel_1 = new JLabel("valeure");
		panel_6.add(lblNewLabel_1);
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7);
		
		JLabel lblType = new JLabel("type");
		panel_7.add(lblType);
		
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8);
		
		JLabel lblId = new JLabel("id");
		panel_8.add(lblId);
		
		JPanel panel_9 = new JPanel();
		panel_2.add(panel_9);
		
		textField_idEleve = new JTextField();
		panel_9.add(textField_idEleve);
		textField_idEleve.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		panel_2.add(panel_10);
		
		JLabel lblint_1 = new JLabel("(int)");
		panel_10.add(lblint_1);
		
		JPanel panel_11 = new JPanel();
		panel_2.add(panel_11);
		
		JLabel lblPrenom = new JLabel("prenom");
		panel_11.add(lblPrenom);
		
		JPanel panel_12 = new JPanel();
		panel_2.add(panel_12);
		
		textField_prenomEleve = new JTextField();
		panel_12.add(textField_prenomEleve);
		textField_prenomEleve.setColumns(10);
		
		JPanel panel_13 = new JPanel();
		panel_2.add(panel_13);
		
		JLabel lblvarchar_1 = new JLabel("(VARCHAR)");
		panel_13.add(lblvarchar_1);
		
		JPanel panel_14 = new JPanel();
		panel_2.add(panel_14);
		
		JLabel lblNom = new JLabel("nom");
		panel_14.add(lblNom);
		
		JPanel panel_15 = new JPanel();
		panel_2.add(panel_15);
		
		textField_nomEleve = new JTextField();
		panel_15.add(textField_nomEleve);
		textField_nomEleve.setColumns(10);
		
		JPanel panel_16 = new JPanel();
		panel_2.add(panel_16);
		
		JLabel lblvarchar = new JLabel("(VARCHAR)");
		panel_16.add(lblvarchar);
		
		JPanel panel_17 = new JPanel();
		panel_2.add(panel_17);
		
		JLabel lblPromo = new JLabel("promo");
		panel_17.add(lblPromo);
		
		JPanel panel_18 = new JPanel();
		panel_2.add(panel_18);
		
		textField_promoEleve = new JTextField();
		panel_18.add(textField_promoEleve);
		textField_promoEleve.setColumns(10);
		
		JPanel panel_19 = new JPanel();
		panel_2.add(panel_19);
		
		JLabel lblint = new JLabel("(int)");
		panel_19.add(lblint);
		
		JPanel panel_20 = new JPanel();
		panel_2.add(panel_20);
		
		JLabel lblSolde = new JLabel("solde");
		panel_20.add(lblSolde);
		
		JPanel panel_21 = new JPanel();
		panel_2.add(panel_21);
		
		textField_soldeEleve = new JTextField();
		panel_21.add(textField_soldeEleve);
		textField_soldeEleve.setColumns(10);
		
		JPanel panel_22 = new JPanel();
		panel_2.add(panel_22);
		
		JLabel lblfloat = new JLabel("(float)");
		panel_22.add(lblfloat);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("historique", null, scrollPane_1, null);
		
		JPanel panel_23 = new JPanel();
		scrollPane_1.setViewportView(panel_23);
		panel_23.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_24 = new JPanel();
		panel_24.setPreferredSize(new Dimension(450, 50));
		panel_23.add(panel_24);
		panel_24.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_25 = new JPanel();
		panel_24.add(panel_25);
		
		JLabel label = new JLabel("table : ");
		panel_25.add(label);
		
		JPanel panel_26 = new JPanel();
		panel_24.add(panel_26);
		
		textField_tableTrans = new JTextField();
		textField_tableTrans.setColumns(10);
		panel_26.add(textField_tableTrans);
		
		JPanel panel_27 = new JPanel();
		panel_23.add(panel_27);
		panel_27.setLayout(new GridLayout(7, 3, 0, 0));
		
		JPanel panel_28 = new JPanel();
		panel_27.add(panel_28);
		
		JLabel label_1 = new JLabel("colonne");
		panel_28.add(label_1);
		
		JPanel panel_29 = new JPanel();
		panel_27.add(panel_29);
		
		JLabel label_2 = new JLabel("valeure");
		panel_29.add(label_2);
		
		JPanel panel_30 = new JPanel();
		panel_27.add(panel_30);
		
		JLabel label_3 = new JLabel("type");
		panel_30.add(label_3);
		
		JPanel panel_31 = new JPanel();
		panel_27.add(panel_31);
		
		JLabel label_4 = new JLabel("id");
		panel_31.add(label_4);
		
		JPanel panel_32 = new JPanel();
		panel_27.add(panel_32);
		
		textField_idTrans = new JTextField();
		textField_idTrans.setColumns(10);
		panel_32.add(textField_idTrans);
		
		JPanel panel_33 = new JPanel();
		panel_27.add(panel_33);
		
		JLabel label_5 = new JLabel("(int)");
		panel_33.add(label_5);
		
		JPanel panel_34 = new JPanel();
		panel_27.add(panel_34);
		
		JLabel lblIdeleve = new JLabel("id_eleve");
		panel_34.add(lblIdeleve);
		
		JPanel panel_35 = new JPanel();
		panel_27.add(panel_35);
		
		textField_id_eleveTrans = new JTextField();
		textField_id_eleveTrans.setColumns(10);
		panel_35.add(textField_id_eleveTrans);
		
		JPanel panel_36 = new JPanel();
		panel_27.add(panel_36);
		
		JLabel lblint_2 = new JLabel("(int)");
		panel_36.add(lblint_2);
		
		JPanel panel_37 = new JPanel();
		panel_27.add(panel_37);
		
		JLabel lblOpration = new JLabel("opération");
		panel_37.add(lblOpration);
		
		JPanel panel_38 = new JPanel();
		panel_27.add(panel_38);
		
		textField_operationTrans = new JTextField();
		textField_operationTrans.setColumns(10);
		panel_38.add(textField_operationTrans);
		
		JPanel panel_39 = new JPanel();
		panel_27.add(panel_39);
		
		JLabel label_9 = new JLabel("(VARCHAR)");
		panel_39.add(label_9);
		
		JPanel panel_40 = new JPanel();
		panel_27.add(panel_40);
		
		JLabel lblMontant = new JLabel("montant");
		panel_40.add(lblMontant);
		
		JPanel panel_41 = new JPanel();
		panel_27.add(panel_41);
		
		textField_montantTrans = new JTextField();
		textField_montantTrans.setColumns(10);
		panel_41.add(textField_montantTrans);
		
		JPanel panel_42 = new JPanel();
		panel_27.add(panel_42);
		
		JLabel lblfloat_1 = new JLabel("(float)");
		panel_42.add(lblfloat_1);
		
		JPanel panel_43 = new JPanel();
		panel_27.add(panel_43);
		
		JLabel lblDate = new JLabel("date");
		panel_43.add(lblDate);
		
		JPanel panel_44 = new JPanel();
		panel_27.add(panel_44);
		
		textField_dateTrans = new JTextField();
		textField_dateTrans.setColumns(10);
		panel_44.add(textField_dateTrans);
		
		JPanel panel_45 = new JPanel();
		panel_27.add(panel_45);
		
		JLabel lbldatetime = new JLabel("(DATETIME)");
		panel_45.add(lbldatetime);
		
		JPanel panel_46 = new JPanel();
		panel_27.add(panel_46);
		
		JLabel lblIdutilisateur = new JLabel("id_utilisateur");
		panel_46.add(lblIdutilisateur);
		
		JPanel panel_47 = new JPanel();
		panel_27.add(panel_47);
		
		textField_idutilTrans = new JTextField();
		panel_47.add(textField_idutilTrans);
		textField_idutilTrans.setColumns(10);
		
		JPanel panel_48 = new JPanel();
		panel_27.add(panel_48);
		
		JLabel lblint_3 = new JLabel("(int)");
		panel_48.add(lblint_3);
		
		txtFieldsList.add(textField_tableEleve);
		txtFieldsList.add(textField_idEleve);
		txtFieldsList.add(textField_prenomEleve);
		txtFieldsList.add(textField_nomEleve);
		txtFieldsList.add(textField_promoEleve);
		txtFieldsList.add(textField_soldeEleve);
		
		txtFieldsList.add(textField_tableTrans);
		txtFieldsList.add(textField_idTrans);
		txtFieldsList.add(textField_id_eleveTrans);
		txtFieldsList.add(textField_operationTrans);
		txtFieldsList.add(textField_montantTrans);
		txtFieldsList.add(textField_dateTrans);
		txtFieldsList.add(textField_idutilTrans);
		
		
		conn.addObservateur(new Observateur(){

			@Override
			public void update(ArrayList<Boolean> listeDesTest) {
				// TODO Auto-generated method stub
				for(int i=0; i<listeDesTest.size(); i++){
					if(!listeDesTest.get(i)){
						txtFieldsList.get(i).setBackground(Color.RED);
					}
					else{
						txtFieldsList.get(i).setBackground(Color.WHITE);
						String[] val = {txtFieldsList.get(i).getText(), ""};
						Connect.getBdd_schem().set(i, val);
					}
				}
			}

			@Override
			public void updateString(ArrayList<String> listeDesLabels) {
				// TODO Auto-generated method stub
				/*for(int i=0; i<listeDesLabels.size(); i++){
					
					txtFieldsList.get(i).setText(listeDesLabels.get(i));
				}*/
			}
			
		});
		for(int i=0; i<Connect.getBdd_schem().size(); i++){
			txtFieldsList.get(i).setText(Connect.getBdd_schem().get(i)[0]);
		}
	}

	public ArrayList<JTextField> getTxtFieldsList() {
		return txtFieldsList;
	}

	public void setTxtFieldsList(ArrayList<JTextField> txtFieldsList) {
		this.txtFieldsList = txtFieldsList;
	}

	public Connection_valid getConn() {
		return conn;
	}

	public void setConn(Connection_valid conn) {
		this.conn = conn;
	}

	
}
