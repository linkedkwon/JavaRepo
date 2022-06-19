package oop.overriding;

//case 1 : Overriding
class Unit {

    private int x;
    private int y;

    public void move() {
        x += 2;
        y += 2;
    }

}

class Tank extends Unit{

    private boolean isSiegeMode;

    public void move() {
        if(isSiegeMode)
            return;
        super.move();
    }

}

//case 2 : @Overriding Annotation
class Character {

    public void attack() {
        System.out.println("Attack!");
    }

    public void stop() {
        System.out.println("Stop!");
    }

}

class Marine extends Character{

    public void attack() {
        System.out.println("Marine Attack!");
    }

    @Override
    public void stop() {
        System.out.println("Marine Stop!");
    }

}

public class OverridingCase {}