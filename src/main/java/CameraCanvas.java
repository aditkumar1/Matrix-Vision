import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CameraCanvas extends JPanel {

    private BufferedImage img;
    @Override
    public Dimension getPreferredSize() {
        return img == null ? new Dimension(200, 200) : new Dimension(img.getWidth(), img.getHeight());
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            int x = (getWidth() - img.getWidth()) / 2;
            int y = (getHeight() - img.getHeight()) / 2;
            g.drawImage(img, x, y, this);
        }
    }
    public void displayImage(BufferedImage bi){
        img=bi;
        this.repaint();
    }
}

