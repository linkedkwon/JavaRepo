package oop;

//case 1 : Abstract Class
abstract class Unit{

    private int groundAttack;

    abstract void upgrade();

    public void setAttack(int attack) {
        this.groundAttack = attack;
    }

    public int getGroundAttack() {
        return groundAttack;
    }

}

//case 2 : Abstract method
class Scout extends Unit{

    private int airAttack;

    @Override
    public void upgrade() {
        setAttack(5);
        setAirAttack(10);
    }

    public void setAirAttack(int damage) {
        this.airAttack = damage;
    }

}

class Tank extends Unit{

    private int siegeModeAttack;

    @Override
    public void upgrade(){
        setAttack(20);
        setSiegeModeAttack(50);
    }

    public void setSiegeModeAttack(int damage) {
        this.siegeModeAttack = damage;
    }

}

public class AbstractCase{}