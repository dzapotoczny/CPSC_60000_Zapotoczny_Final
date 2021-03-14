import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class TwoLaneTwoWayRoadEW extends RoadType {

    public TwoLaneTwoWayRoadEW(int x, int y){
        description = "EW";
        laneX = x;
        laneY = y;
        laneWidth = 200;
        laneHeight = 40;
        roadDirection[0] = 1;
        roadDirection[1] = 0;

        road = new Rectangle2D.Double(laneX, laneY, laneWidth, laneHeight);

        line1 = new Line2D.Double(laneX, laneY + (laneHeight / 2) - 1, laneX + laneWidth, laneY + (laneHeight / 2) - 1);
        line2 = new Line2D.Double(laneX, laneY + (laneHeight / 2) + 1, laneX + laneWidth, laneY + (laneHeight / 2) + 1);
    }
    /*
        @Override
        public void paintComponent (Graphics g){
            super.paintComponent(g);


            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.GRAY);

            //g2.fill(new Rectangle2D.Double(laneX, laneY, laneWidth, laneHeight));
            g2.fillRect(laneX, laneY, laneWidth, laneHeight);

            g2.setColor(Color.YELLOW);
            g2.drawLine(laneX, laneY + (laneHeight / 2) - 1, laneX + laneWidth, laneY + (laneHeight / 2) - 1);
            g2.drawLine(laneX, laneY + (laneHeight / 2) + 1, laneX + laneWidth, laneY + (laneHeight / 2) + 1);

        }

        */
    public void createLane(){

    }

    public int getLaneX() { return laneX; }

    public int getLaneY() { return laneY; }

    public int getLaneWidth() { return laneWidth; }

    public int getLaneHeight() { return laneHeight; }

}
