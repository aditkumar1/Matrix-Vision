import org.opencv.core.Core;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    nu.pattern.OpenCV.loadShared();
                    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
                    CameraFrame cf= new CameraFrame();
                    cf.setVisible(true);
                    Thread updateFrame= new Thread(new DisplayCameraFeed(cf));
                    updateFrame.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}