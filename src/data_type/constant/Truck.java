package data_type.constant;

//3. How to init Object constant : two case
public class Truck {

    private final int MAX_WEIGHT;
    private int fuel;

    public Truck(int MAX_WEIGHT, int fuel) {
       this.MAX_WEIGHT = MAX_WEIGHT;
       this.fuel = fuel;
    }

    public void setFuel(int fuel){
        this.fuel += fuel;
    }

}

class ObjectConstant{

    static void main(String[] args){
        Truck lumberTruck = new Truck(5, 1000);
        Truck siliconTruck = new Truck(10, 1000);

        lumberTruck.setFuel(10);
        siliconTruck.setFuel(10);
    }

}
