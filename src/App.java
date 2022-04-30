
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class App extends JFrame {	
    public App() {
        super("Git Gui");
        
        this.setLayout(new BorderLayout());
        
        this.setSize(1000, 900);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        DrawingPanel drawingPanel = new DrawingPanel();
        
        this.add(drawingPanel, BorderLayout.CENTER);
        BufferedImage qu = null;
		BufferedImage micro = null;
		BufferedImage prototype = null;

		ArrayList<BufferedImage> image = new ArrayList<BufferedImage>();
		try {
			qu = ImageIO.read(new File("./images/QU.png"));
			micro = ImageIO.read(new File("./images/micro.png"));
			prototype = ImageIO.read(new File("./images/Prototype.png"));
			

			image.add(qu);
			image.add(micro);
			image.add(prototype);
			
			this.setIconImages(image);
		}
		catch (IOException e) {
			System.out.println("Unable to load icon images! Using defaults.");
		}
    
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
}