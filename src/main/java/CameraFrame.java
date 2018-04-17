import javax.swing.*;
import java.awt.*;

public class CameraFrame extends JFrame {
    DisplayPanel displayPanel;
    public CameraFrame() throws HeadlessException {
        initialize();
    }
    private void initialize() {
        displayPanel =new DisplayPanel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(displayPanel, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setMinimumSize(new Dimension(300, 200));
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.black);
        displayPanel.initializeComponent();
    }
    public DisplayPanel getCanvas(){
        return this.displayPanel;
    }
}
