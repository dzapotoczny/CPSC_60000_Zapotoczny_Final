public class RoadFactory {
    public RoadType createRoad(String type, int x, int y, int enterDir, int exitDir){
        RoadType roadType = null;

        if (type.equals("Junction")){
            roadType = new Junction(x, y, enterDir, exitDir);
        }
        return roadType;
    }
    public RoadType createRoad(String type, int x, int y){
        RoadType roadType = null;

        if (type.equals("NS")){
            roadType = new TwoLaneTwoWayRoadNS(x, y);
        } else if (type.equals("EW")){
            roadType = new TwoLaneTwoWayRoadEW(x, y);
        }
        return roadType;
    }

}
