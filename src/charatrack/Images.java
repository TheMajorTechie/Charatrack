package charatrack;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Images {

	private BufferedImage image;

	public Images(BufferedImage img) {
		image = img;
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}
	
}
