package source;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;


public class SelectJTF implements FocusListener{

	private JTextField jtf;
	
	public SelectJTF(JTextField jtf){
		this.jtf = jtf;
	}
	
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		this.jtf.setSelectionStart(0);
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		//this.jtf.setText("");
	}

}
