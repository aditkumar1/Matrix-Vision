import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;
import sun.security.ssl.Debug;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class DisplayCameraFeed implements Runnable {
    CameraFrame frame;
    public DisplayCameraFeed(CameraFrame frame){
        this.frame=frame;
    }
    public void run() {
        VideoCapture camera = new VideoCapture(0);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        camera.set(Videoio.CV_CAP_PROP_FRAME_WIDTH,screenSize.getWidth());
        camera.set(Videoio.CV_CAP_PROP_FRAME_HEIGHT,screenSize.getHeight());
        if(!camera.isOpened()){
            Debug.println("camera error","Error Opening Camera");
        }
        else {
            Mat iframe = new Mat();
            while(true){
                if (camera.read(iframe)){
                    ArrayList<MatOfPoint> contourList = getContoursList(iframe);
                    frame.getCanvas().updateContour(contourList);
                }
            }
        }
        camera.release();
    }
    public ArrayList<MatOfPoint> getContoursList(Mat originalMat) {
        Mat grayMat = new Mat();
        Mat cannyEdges = new Mat();
        Mat hierarchy = new Mat();
        int frameWidth=frame.getCanvas().getWidth();
        int frameHeight=frame.getCanvas().getHeight();
        int imageWidth=frameWidth>0?frameWidth:originalMat.width();
        int imageHeight=frameHeight>0?frameHeight:originalMat.height();
        Size sz = new Size(imageWidth,imageHeight);
        Imgproc.resize( originalMat, originalMat, sz );
        ArrayList<MatOfPoint> contourList = new ArrayList<MatOfPoint>();
        Imgproc.cvtColor(originalMat, grayMat, Imgproc.COLOR_BGR2GRAY);
        Imgproc.Canny(originalMat, cannyEdges, 10, 100);
        Imgproc.findContours(cannyEdges, contourList, hierarchy,Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        Random r = new Random();
        return contourList;
    }
}
