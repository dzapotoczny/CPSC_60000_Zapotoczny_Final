import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class test {
    @Test
    public void testCommandCycle(){
        RoadFactory factory = new RoadFactory();
        int ES = 10110;
        int NES = 11110;
        int NE = 11100;
        int ESW = 10111;
        int NESW = 11111;
        int NEW = 11101;
        int SW = 10011;
        int NSW = 11011;
        int NW = 11001;
        List<RoadType> roads = new ArrayList<RoadType>();
        List<TrafficLightController> controllers = new ArrayList<TrafficLightController>();

        roads.add(factory.createRoad("Junction", 300, 160, NW, ES));
        roads.add(factory.createRoad("Junction", 300, 160, NW, ES));
        roads.add(factory.createRoad("Junction", 300, 400, NSW, NES));
        roads.add(factory.createRoad("Junction", 300, 400, NSW, NES));
        roads.add(factory.createRoad("Junction", 540, 400, NESW, NESW));
        roads.add(factory.createRoad("Junction", 540, 400, NESW, NESW));

        Assertions.assertEquals(roads.get(0).laneX, roads.get(1).laneX);
        Assertions.assertEquals(roads.get(0).laneY, roads.get(1).laneY);
        Assertions.assertEquals(roads.get(0).laneHeight, roads.get(1).laneHeight);
        Assertions.assertEquals(roads.get(0).laneWidth, roads.get(1).laneWidth);

        Assertions.assertEquals(roads.get(2).laneX, roads.get(3).laneX);
        Assertions.assertEquals(roads.get(2).laneY, roads.get(3).laneY);
        Assertions.assertEquals(roads.get(2).laneHeight, roads.get(3).laneHeight);
        Assertions.assertEquals(roads.get(2).laneWidth, roads.get(3).laneWidth);

        Assertions.assertEquals(roads.get(4).laneX, roads.get(5).laneX);
        Assertions.assertEquals(roads.get(4).laneY, roads.get(5).laneY);
        Assertions.assertEquals(roads.get(4).laneHeight, roads.get(5).laneHeight);
        Assertions.assertEquals(roads.get(4).laneWidth, roads.get(5).laneWidth);


    }

}
