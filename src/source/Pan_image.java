package source;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import bdd.utilisateur.Utilisateur;


public class Pan_image extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String image;
	/**
	 * Create the panel.
	 */
	public Pan_image(String img) {

		image = img;
	}

	public void paintComponent(Graphics g){
		if(new File(Utilisateur.dir_images + image).exists()){
			try {
                Image img = ImageIO.read(new File(Utilisateur.dir_images + image));
                //Pour une image de fond
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
		}
		else{
			ImageIcon img_default = new ImageIcon( getClass() .getResource( "image.png" )); 
			//Image img = ImageIO.read(new File("img/image.png"));
			//Pour une image de fond
			//g.drawImage(img_default, 0, 0, this.getWidth(), this.getHeight(), this);
			img_default.paintIcon(this, g, 0, 0);
		}
        
        
}
}
