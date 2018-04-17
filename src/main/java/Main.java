import org.opencv.core.Core;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    nu.pattern.OpenCV.loadShared();
                    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
                    final CameraFrame cf= new CameraFrame();
                    cf.setVisible(true);
                    Thread updateFrame= new Thread(new DisplayCameraFeed(cf));
                    updateFrame.start();
                    Thread runAnimation = new Thread() {
                        public void run() {
                            try {
                                while (true) {
                                    cf.getCanvas().updatePanel();
                                    SwingUtilities.updateComponentTreeUI(cf.getCanvas());
                                    //Thread.sleep(50);
                                }
                            } catch(Exception v) {
                                System.out.println(v);
                            }
                        }
                    };
                    runAnimation.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}