import java.util.List;

public class CommandCycle implements CommandTrafficLights{
    private List<TrafficLightController> controllers = null;
    TrafficLightState NSGreen;
    TrafficLightState NSRed;

    TrafficLightState state;

    public CommandCycle(List<TrafficLightController> controllers) {
        this.controllers = controllers;
        NSGreen = new NSGreenEWRedState(controllers, this);
        NSRed = new NSRedEWGreen(controllers, this);
        state = NSGreen;
    }

    @Override
    public void execute(){
        if (state.equals(NSGreen)){
            EWGreen();
        } else if (state.equals(NSRed)){
            NSGreen();
        }
    }
    public void setState(TrafficLightState state){ this.state = state; }
    public TrafficLightState getNSGreenState(){ return NSGreen; }
    public TrafficLightState getEWGreenState(){ return NSRed; }
    public void NSGreen() { state.greenNS(); }
    public void EWGreen() { state.greenEW(); }
}
