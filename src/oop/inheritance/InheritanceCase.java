package oop.inheritance;


//case 1 : Keyword extends
class Product{
    //...
}

class Model extends Product{
    //....
}

//case 2 : Super keyword
class Car{

    private final int MAX_SPEED;
    public boolean option;

    public Car(int MAX_SPEED){
        this.MAX_SPEED = MAX_SPEED;
    }

}

class Sonata extends Car{

    private boolean option;

    public Sonata(int MAX_SPEED){
        super(MAX_SPEED);
        super.option = false;
        this.option = true;
    }

}

public class InheritanceCase{

    void sonataInstance(){
        Sonata obj = new Sonata(280);
    }
}

//case 3 : Overriding
class Unit{

    private int x, y;

    public void move(){
        x += 5; y += 5;
    }

    public void addCoordinate(int x, int y){
        this.x += x;
        this.y += y;
    }

}

class Tank extends Unit{

    @Override
    public void move(){
        addCoordinate(3, 3);
    }

}