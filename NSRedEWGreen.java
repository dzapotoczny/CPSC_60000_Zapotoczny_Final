import java.awt.*;
import java.util.List;

public class NSRedEWGreen implements TrafficLightState{

    List<TrafficLightController> controllers;
    CommandCycle c;

    public NSRedEWGreen(List<TrafficLightController> con, CommandCycle c) {
        this.controllers = con;
        this.c = c; }

    @Override
    public void greenNS(){
        for (TrafficLightController c : controllers){
            for (TrafficLight t : c.lights){
                if (t.direction.equals("N") || t.direction.equals("S")){
                    t.lightColor = Color.GREEN;
                    t.green = true;
                } else if (t.direction.equals("E") || t.direction.equals("W")){
                    t.lightColor = Color.RED;
                    t.green = false;
                }
            }
        }
        c.setState(c.getNSGreenState());
    }
    @Override
    public void greenEW(){
        for (TrafficLightController c : controllers){
            for (TrafficLight t : c.lights){
                if (t.direction.equals("E") || t.direction.equals("W")){
                    t.lightColor = Color.GREEN;
                    t.green = true;
                } else if (t.direction.equals("N") || t.direction.equals("S")){
                    t.lightColor = Color.RED;
                    t.green = false;
                }
            }
        }
        c.setState(c.getEWGreenState());

    }
}
