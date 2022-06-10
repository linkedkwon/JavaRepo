package oop.anonymous;

//case 1 : Anonymous Definition
class Product{

    private static final int COST = 1000;

    public int getCOST() {
        return COST;
    }

}

public class AnonymousClassCase {

    public static void main(String[] args){
        Product limitedEdition = new Product(){

            @Override
            public int getCOST() {
                return super.getCOST() * 2;
            }
        };

        limitedEdition.getCOST();
    }

}

//case 2 : By class
class Model{

    private static final String COLOR = "RED/BLUE/GREEn";

    public String getColor() {
        return COLOR;
    }

}

class InstanceByClass {

    public Model getLimitedEdition() {
        return new Model() {

            private static final String COLOR = "SILVER/GREY";

            @Override
            public String getColor() {
                return super.getColor() + '/' + COLOR;
            }
        };
    }

}

//case 3 : By interface
class InstanceByInterface{

    public Runnable getThread(){
        return new Runnable(){
            @Override
            public void run() {
                System.out.println("Waiting Connection");
                while(true){
                    //....
                }
            }
        };
    }

}

//case 4 : As Parameter
class InstanceAsParameter{

    public Thread getThread(){
        return new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Waiting Connection");
                while(true){
                    //....
                }
            }
        });
    }

}