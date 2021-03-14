public class Controller {
    CommandTrafficLights c;

    public Controller(CommandTrafficLights c) {
        this.c = c;
    }

    public void cycle() {
        c.execute();
    }
}

