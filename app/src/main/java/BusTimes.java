import java.util.ArrayList;

public class BusTimes {
    private ArrayList<String> busTimes;
    private String busRoute;
    private String busID;

    public void setRoute(String input) {
        busRoute = input;
    }

    public void setID(String input){
        busID = input;
    }

    public void addTime(String time){
        busTimes.add(time);
    }

    public String getBusRoute(){
        return busRoute;
    }

    public String getBusID(){
        return busID;
    }

    public ArrayList<String> getBusTimes(){
        return busTimes;
    }

}
