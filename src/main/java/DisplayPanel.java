import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class DisplayPanel extends JPanel {
    static ArrayList<MatOfPoint> contourlist;
    private static final int FONT_SIZE = 15;
    private static final String TEXT = new String("あたアカサザジズゼゾシスセソキクケコイウエオジャな0123456789");
    private static Random random = new Random();
    private static JPanel[] rows;

    public void initializeComponent(){
        this.setBackground(Color.black);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize);
        int totalNoOfRows= (this.getHeight()/FONT_SIZE)-1;
        rows= new JPanel[totalNoOfRows];
        for(int i=0;i<totalNoOfRows;i++){
            rows[i]=initializeRow();
        }
        addRowsToPanel();
    }
    public JPanel initializeRow(){
        JPanel row =new JPanel();
        row.setBackground(Color.BLACK);
        int totalNoOfCharacters= (this.getWidth() / FONT_SIZE)-1;
        row.setLayout(new GridLayout(0,totalNoOfCharacters));
        JLabel[] characters= new JLabel[totalNoOfCharacters];
        for(JLabel character: characters){
            int character_initial = random.nextInt(TEXT.length());
            character = new JLabel("" + TEXT.charAt(character_initial));
            character.setFont(new Font("monospaced", Font.PLAIN, FONT_SIZE));
            character.setForeground(new Color(0, 50+random.nextInt(50), 0));
            row.add(character);
        }
        return row;
    }
    public void updatePanel(){
        for(int i=rows.length-1;i>0;i--){
            rows[i]=rows[i-1];
        }
        rows[0]=initializeRow();
        this.removeAll();
        this.addRowsToPanel();
    }
    public void addRowsToPanel(){
        for(JPanel row: rows){
            this.add(row);
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        if (contourlist != null) {
            synchronized (contourlist) {
                for (MatOfPoint pointMatrx : contourlist) {
                    Point[] contourPoints = pointMatrx.toArray();
                    for (Point contourPoint : contourPoints) {
                        g.drawLine((int) contourPoint.x, (int) contourPoint.y, (int) contourPoint.x, (int) contourPoint.y);
                    }
                }
            }
        }
    }
    public void updateContour(ArrayList<MatOfPoint> contours){
        contourlist=contours;
    }
}

