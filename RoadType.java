import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public abstract class RoadType extends JPanel {
    String description = "Unknown Road Type";
    int[] roadDirection = {-1, -1};    //{1, 0} indicates East-West, {0, 1} indicates North-South, {0, 0} indicates junction/intersection
    int laneX = 0;
    int laneY = 0;
    int laneWidth = 0;
    int laneHeight = 0;
    public Color roadColor = Color.DARK_GRAY;
    public Color junctionColor = Color.GRAY;
    public Color ctrLineColor = Color.YELLOW;
    Rectangle2D road;
    Line2D line1, line2, line3, line4;






    public int getX() { return laneX; }

    public int getY() { return laneY; }

    public int getWidth() { return laneWidth; }

    public int getHeight() { return laneHeight; }

    public String getDescription(){ return description; }
}
