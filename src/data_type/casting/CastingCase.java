package data_type.casting;

public class CastingCase {

    //case 1 : Casting definition
    public void primitiveCasting(){
        double a = 123.456;
        int b = (int)a;
        double c = b;
        System.out.println(c);
    }

    //case 2 : down casting
    /**
    class Car{
        private String name;
        private int cost;

        public Car(String name, int cost) {
            this.name = name;
            this.cost = cost;
        }
    }

    class Audi extends Car{

        private boolean hasForeignInsurance;

        public Audi(String name, int cost){
            super(name, cost);
        }

        public void setHasForeignInsurance(boolean hasForeignInsurance) {
            this.hasForeignInsurance = hasForeignInsurance;
        }
    }

    public void referenceCasting(){
        Car car = new Audi("A6", 1000);
        Audi audi = (Audi)car;
        audi.setHasForeignInsurance(true);
    }
     */

    //case 3 : abstract by upcasting
    class Car{
        //public void printCarInfo(Bentley car){}

        //public void printCarInfo(Sonata car) {}

        public void printCarInfo(Car car) {}
    }

    class Bentley extends Car {}

    class Sonata extends Car {}

}