import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class TrafficLight extends JPanel {

    public boolean green;
    private final double lightX;
    private final double lightY;
    public String direction;
    public Color lightColor;
    public Rectangle2D box;
    public Ellipse2D light;

    public TrafficLight(int x, int y, boolean isGreen, String dir){
        lightX = x;
        lightY = y;
        green = isGreen;
        direction = dir;

        if (green){
            lightColor = Color.GREEN;
        } else {
            lightColor = Color.RED;
        }
        box = new Rectangle2D.Double(lightX, lightY,20,20);
        light = new Ellipse2D.Double(lightX + 4, lightY + 4, 12, 12);

    }
    /*
    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        //System.out.print(lightX + " " + lightY + "\n");
        g2.fillRect(lightX, lightY, 20, 20);

        g2.setColor(lightColor);
        g2.fillOval(lightX + 4, lightY + 4, 12, 12);
    }
    */


    public void cycleLight(){
        if(green){
            green = false;
            lightColor = Color.RED;
        } else {
            green = true;
            lightColor = Color.GREEN;
        }
    }

}
