import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;
import sun.security.ssl.Debug;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static org.opencv.core.CvType.CV_16U;

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
            //Remove this comment when done experimenting with image matrix
            //while(true){
                if (camera.read(iframe)){
                    Debug.println("Frame Obtained","Captured Frame Width " +iframe.width() + " Height " + iframe.height());
                    iframe=drawContours(iframe);
                    //Imgproc.cvtColor(iframe,iframe,Imgproc.COLOR_BGR2GRAY);
                    frame.getCanvas().displayImage(CommonUtils.toBufferedImage(iframe));

                }
            //}
        }
        camera.release();
    }
    public Mat drawContours(Mat originalMat) {
        Mat grayMat = new Mat();
        Mat cannyEdges = new Mat();
        Mat hierarchy = new Mat();
        ArrayList<MatOfPoint> contourList = new ArrayList<MatOfPoint>(); //A list to store all the contours

        //Converting the image to grayscale
        Imgproc.cvtColor(originalMat, grayMat, Imgproc.COLOR_BGR2GRAY);
        Imgproc.Canny(originalMat, cannyEdges, 10, 100);
        //finding contours
        Imgproc.findContours(cannyEdges, contourList, hierarchy,Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        //Drawing contours on a new image
        Mat contours = new Mat();
        contours.create(cannyEdges.rows(), cannyEdges.cols(), CvType.CV_8UC3);
        Random r = new Random();
        for (int i = 0; i < contourList.size(); i++) {
            Imgproc.drawContours(contours, contourList, i, new Scalar(255, 255, 255), -1);
        }
        return contours;
    }
}
