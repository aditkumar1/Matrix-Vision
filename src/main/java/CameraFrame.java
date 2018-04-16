import javax.swing.*;
import java.awt.*;

public class CameraFrame extends JFrame {
    CameraCanvas cameraCanvas;
    public CameraFrame() throws HeadlessException {
        initialize();
    }
    private void initialize() {
        cameraCanvas=new CameraCanvas();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(100, 100, (int)screenSize.getWidth(),(int) screenSize.getHeight());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(cameraCanvas, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
    }
    public CameraCanvas getCanvas(){
        return this.cameraCanvas;
    }
}
