import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class MapGenerator {
    JFrame frame = new JFrame("Simulation Map");
    JPanel panel = new JPanel();
    List<RoadType> roads = new ArrayList<RoadType>();
    private RoadFactory roadFactory = new RoadFactory();
    List<TrafficLightController> lightControllers = new ArrayList<TrafficLightController>();
    Graphics g;
    TwoLaneTwoWayRoadNS road1;

    public MapGenerator(){
        int ES = 10110;
        int NES = 11110;
        int NE = 11100;
        int ESW = 10111;
        int NESW = 11111;
        int NEW = 11101;
        int SW = 10011;
        int NSW = 11011;
        int NW = 11001;
        roads.add(roadFactory.createRoad("Junction", 300, 160, NW, ES));
        roads.add(roadFactory.createRoad("Junction", 300, 400, NSW, NES));
        roads.add(roadFactory.createRoad("Junction", 540, 160, NEW, ESW));
        roads.add(roadFactory.createRoad("Junction", 540, 400, NESW, NESW));
        roads.add(roadFactory.createRoad("Junction", 980, 840, ES, NW));
        roads.add(roadFactory.createRoad("Junction", 980, 160, NE, SW));
        roads.add(roadFactory.createRoad("Junction", 980, 400, NES, NSW));
        roads.add(roadFactory.createRoad("Junction", 300, 840, SW, NE));
        roads.add(roadFactory.createRoad("Junction", 540, 840, ESW, NEW));
        roads.add(roadFactory.createRoad("NS", 300, 200));
        roads.add(roadFactory.createRoad("EW", 340, 160));
        roads.add(roadFactory.createRoad("NS", 540, 200));
        roads.add(roadFactory.createRoad("EW", 340, 400));

        roads.add(roadFactory.createRoad("EW", 580, 160));
        roads.add(roadFactory.createRoad("EW", 780, 160));
        roads.add(roadFactory.createRoad("EW", 580, 400));
        roads.add(roadFactory.createRoad("EW", 780, 400));
        roads.add(roadFactory.createRoad("NS", 980, 200));
        roads.add(roadFactory.createRoad("NS", 300, 440));
        roads.add(roadFactory.createRoad("NS", 300, 640));
        roads.add(roadFactory.createRoad("NS", 540, 440));
        roads.add(roadFactory.createRoad("NS", 540, 640));
        roads.add(roadFactory.createRoad("EW", 340, 840));
        roads.add(roadFactory.createRoad("EW", 580, 840));
        roads.add(roadFactory.createRoad("EW", 780, 840));
        roads.add(roadFactory.createRoad("NS", 980, 440));
        roads.add(roadFactory.createRoad("NS", 980, 640));

        for (RoadType road : roads) {
            if (road instanceof Junction) {
                Junction j = (Junction) road;
                lightControllers.add(new TrafficLightController(j));
            }
        }
        //roads.add(new TwoLaneTwoWayRoadNS(500,200));
        //roads.add(new TwoLaneTwoWayRoadEW(540, 160));
        //road1 = new TwoLaneTwoWayRoadNS(1000,200);
    }


    public void generateMap(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

            }
        });
    }


}
