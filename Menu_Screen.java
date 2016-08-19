import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Menu_Screen {

	
	private BufferedImage background;
	
	public Menu_Screen()
	{
		 try {
			background = ImageIO.read(new File ("textures/background.jpg"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	
	
}
