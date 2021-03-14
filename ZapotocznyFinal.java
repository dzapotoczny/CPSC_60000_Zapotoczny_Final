////////////////////////////////////////////////////////////////////////////////////////////////////
// Name:            Daniel Zapotoczny
// Date:            12-Mar-2021
// Course Name:		CPSC 60000 | Object Oriented Development
// Semester:		Spring I 2021
// Assignment Name:	Final Project
// Program Name:	ZapotocznyFinal
////////////////////////////////////////////////////////////////////////////////////////////////////

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ZapotocznyFinal extends JPanel {
    MapGenerator map = new MapGenerator();
    List<RoadType> roads = map.roads;
    Vehicles vehicles = new Vehicles(map.roads);
    List<TrafficLightController> trafficLightControllers = map.lightControllers;
    CommandCycle cycle = new CommandCycle(trafficLightControllers);
    long timeSinceLastCycle = System.currentTimeMillis();

    public void cycleLight(){
        if (System.currentTimeMillis() - timeSinceLastCycle > 3000){
            cycle.execute();
            timeSinceLastCycle = System.currentTimeMillis();
        }
    }

    public void driveCars(){
        vehicles.drive(trafficLightControllers);
    }

    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //g2.fillRect(laneX, laneY, laneWidth, laneHeight);
        //setBackground(Color.BLACK);
        for (RoadType r : roads) {
            if (r.description.equals("NS") || r.description.equals("EW")) {
                g2.setColor(r.roadColor);
                //g2.fill(new Rectangle(laneX, laneY, laneWidth, laneHeight));
                g2.fill(r.road);

                g2.setColor(r.ctrLineColor);
                g2.draw(r.line1);
                g2.draw(r.line2);
            } else if (r.description.equals("Junction")) {
                g2.setColor(r.junctionColor);
                g2.fill(r.road);
                g2.setColor(Color.white);
                g2.draw(r.line1);
                g2.draw(r.line2);
                g2.draw(r.line3);
                g2.draw(r.line4);
            }
        }
        for (TrafficLightController trafficLightController : trafficLightControllers){
            for (TrafficLight trafficLight : trafficLightController.lights){
                g2.setColor(Color.BLACK);
                g2.fill(trafficLight.box);
                g2.setColor(trafficLight.lightColor);
                g2.fill(trafficLight.light);
            }
        }
        for (Car car : vehicles.vehicles){
            g2.setColor(car.carColor);
            g2.fill(car.car);
        }
    }
    public static void main(String [] args) {
        JFrame frame = new JFrame("Simulation Map");
        JPanel panel = new JPanel();

/*
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {



            }
        });*/
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ZapotocznyFinal paint = new ZapotocznyFinal();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        frame.add(paint);
        frame.pack();
        frame.setSize(screenSize);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        while (true){
            paint.cycleLight();
            paint.driveCars();
            frame.repaint();
            frame.validate();
        }



    }
}
