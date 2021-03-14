import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Junction extends RoadType{

    public int junctionExitDirections;  // int that indicates which directions cars can go at the intersection (0 or 1)
    public int junctionEnterDirections;  // XXXXX -> Don't care value (used to be able to cycle through the last 4 digits), North, East, South, West

    public Junction(int x, int y, int enter, int exit){
        description = "Junction";
        laneX = x;
        laneY = y;
        laneWidth = 40;
        laneHeight = 40;
        roadDirection[0] = 0;
        roadDirection[1] = 0;
        junctionEnterDirections = enter;
        junctionExitDirections = exit;

        road = new Rectangle2D.Double(laneX, laneY, laneWidth, laneHeight);
        line1 = new Line2D.Double(laneX , laneY, laneX + (laneWidth / 2) - 1, laneY);
        line2 = new Line2D.Double(laneX , laneY + laneHeight/2 + 1, laneX, laneY + laneHeight);
        line3 = new Line2D.Double(laneX + (laneWidth / 2) + 1, laneY + laneHeight, laneX + laneWidth, laneY + laneHeight);
        line4 = new Line2D.Double(laneX + laneWidth, laneY, laneX + laneWidth, laneY + laneHeight/2 - 1);
    }
}
