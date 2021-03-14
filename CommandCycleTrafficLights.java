import java.util.List;

public class CommandCycleTrafficLights implements CommandTrafficLights {
    private List<TrafficLightController> trafficLightControllers;

    public CommandCycleTrafficLights(List<TrafficLightController> controllers) { this.trafficLightControllers = controllers; }

    @Override
    public void execute(){
        if (trafficLightControllers != null){
            for (TrafficLightController lights : trafficLightControllers){
                lights.cycleLight();
            }
        }
    }
}
