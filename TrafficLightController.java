import java.util.ArrayList;
import java.util.List;

public class TrafficLightController implements TrafficControlDevices {
    private final int topLeftX, topLeftY, bottomRightX, bottomRightY;
    private int exitDirections, entranceDirections, tempEnt, tempExit;
    public TrafficLight lightN, lightE, lightS, lightW;
    public Junction junction;
    private int[] NESW = {-1, -1, -1, -1};
    private int N, E, S, W;
    public List<TrafficLight> lights = new ArrayList<TrafficLight>();


    public TrafficLightController(Junction j){
        topLeftX = j.laneX;
        topLeftY = j.laneY;
        bottomRightX = j.laneX + j.laneWidth;
        bottomRightY = j.laneY + j.laneHeight;
        entranceDirections = j.junctionEnterDirections;
        exitDirections = j.junctionExitDirections;
        junction = j;
        createTrafficLights();
    }

    private void createTrafficLights(){
        tempEnt = entranceDirections;

        for (int i = 0; i < 4; i++){
            NESW[i] = tempEnt % 10;
            tempEnt /= 10;
        }
        W = NESW[0];
        S = NESW[1];
        E = NESW[2];
        N = NESW[3];

        if (N == 1){
            lights.add(lightN = new TrafficLight(bottomRightX, bottomRightY + 10, true, "N"));
        }
        if (E == 1){
            lights.add(lightE = new TrafficLight(topLeftX - 30, bottomRightY, false, "E"));
        }
        if (S == 1){
            lights.add(lightS = new TrafficLight(topLeftX - 20, topLeftY - 30, true, "S"));
        }
        if (W == 1){
            lights.add(lightW = new TrafficLight(bottomRightX + 10, topLeftY - 20, false, "W"));
        }
    }

    @Override
    public void cycleLight(){
        for (TrafficLight light : lights){
            light.cycleLight();
        }
    }
}
