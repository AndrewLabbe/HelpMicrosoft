
import javax.swing.JFrame;

public class App extends JFrame {	
    public App() {
        super("Git Gui");
        
        this.setSize(1000, 900);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.add(new DrawingPanel());
    
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
}