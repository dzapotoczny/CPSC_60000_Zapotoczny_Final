import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Vehicles {
    List<Car> vehicles = new ArrayList<Car>();
    private List<RoadType> roads = new ArrayList<RoadType>();
    private List<Junction> junctions = new ArrayList<Junction>();

    public Vehicles(List<RoadType> r) {
        roads = r;
        spawnVehicles();
    }

    private void spawnVehicles(){
        for (int i = 0; i < 10; i++){
            RoadType r = roads.get(i);
            if (r.description.equals("Junction")){
                junctions.add((Junction) r);
            } else {
                double randNum = Math.random();
                if (r.description.equals("NS")){
                    if (randNum > 0.5){          // car will spawn heading north
                        vehicles.add(new Car(r.laneX + r.laneWidth - 15, r.laneY + 50,0, -.00005));
                    } else if (randNum <= .5){   // car will spawn heading south
                        vehicles.add(new Car(r.laneX + 5, r.laneY + r.laneHeight - 50, 0, .00005 ));
                    }
                } else if (r.description.equals("EW")){
                    if (randNum > 0.5){          // car will spawn heading east
                        vehicles.add(new Car(r.laneX  + r.laneWidth - 50, r.laneY + r.laneHeight - 15,.00005, 0));
                    } else if (randNum <= .5){   // car will spawn heading west
                        vehicles.add(new Car(r.laneX + 50, r.laneY + 5, -.00005, 0 ));
                    }
                }
            }


        }
    }

    public void drive(List<TrafficLightController> controllers){
        for (Car car : vehicles){
            car.drive(vehicles, controllers);
        }


    }
}
