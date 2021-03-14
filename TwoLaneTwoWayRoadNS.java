import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class TwoLaneTwoWayRoadNS extends RoadType {

    public TwoLaneTwoWayRoadNS(int x, int y){
        description = "NS";
        laneX = x;
        laneY = y;
        laneWidth = 40;
        laneHeight = 200;
        roadDirection[0] = 0;
        roadDirection[1] = 1;
        road = new Rectangle2D.Double(laneX, laneY, laneWidth, laneHeight);
        line1 = new Line2D.Double(laneX + (laneWidth / 2) - 1, laneY, laneX + (laneWidth / 2) - 1, laneY + laneHeight);
        line2 = new Line2D.Double(laneX + (laneWidth / 2) + 1, laneY, laneX + (laneWidth / 2) + 1, laneY + laneHeight);
    }
/*
    System.out.println(laneX);
        System.out.println(laneY);
        System.out.println(laneWidth);
        System.out.println(laneHeight);

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
    //}

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(roadColor);

        //g2.fill(new Rectangle2D.Double(laneX, laneY, laneWidth, laneHeight));
        g2.fillRect(laneX, laneY, laneWidth, laneHeight);

        g2.setColor(ctrLineColor);
        g2.drawLine(laneX + (laneWidth / 2) - 1, laneY, laneX + (laneWidth / 2) - 1, laneY + laneHeight);
        g2.drawLine(laneX + (laneWidth / 2) + 1, laneY, laneX + (laneWidth / 2) + 1, laneY + laneHeight);

    }/*
*/
    public void createLane(){

    }

    public int getLaneX() { return laneX; }

    public int getLaneY() { return laneY; }

    public int getLaneWidth() { return laneWidth; }

    public int getLaneHeight() { return laneHeight; }

}
