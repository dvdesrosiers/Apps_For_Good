import java.util.ArrayList;

public class BusTimeSuper {
    private ArrayList<BusTimes> Buses;

    public void addBus(BusTimes input){
        Buses.add(input);
    }

    public void removeDuplicates(){
        int i = 0;
        while(i<Buses.size()){
            if (Buses.get(i).getBusID().equals(Buses.get(i+1).getBusID()))
                Buses.remove(i);
            else i++;
        }
    }

    public void clear(){
        Buses.clear();
    }

    public BusTimes getBus(String ID){
        int i = 0;
        for(i=0;i<Buses.size();i++){
            if (Buses.get(i).getBusID().equalsIgnoreCase(ID)||Buses.get(i).getBusRoute().equalsIgnoreCase(ID))
                return Buses.get(i);
        }
        return null;
    }
}
