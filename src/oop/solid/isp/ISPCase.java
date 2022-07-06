package oop.solid.isp;

//case 1 : not adapted
/**
interface Vehicle{

    void fly();
    void paddle();
    void load();

}

class Airplane implements Vehicle{

    @Override
    public void fly() {}

    @Override
    public void paddle() {}

    @Override
    public void load() {

    }

}

class Boat implements Vehicle{
    @Override
    public void fly() {

    }

    @Override
    public void paddle() {
    }

    @Override
    public void load() {

    }

}

class Ship implements Vehicle{
    @Override
    public void fly() {

    }

    @Override
    public void paddle() {

    }

    @Override
    public void load() {

    }
}
*/
//case 2 : ISP adapted
interface Fly{
    void fly();
}

interface Paddle{
    void paddle();
}

interface Load{
    void load();
}

class Airplane implements Fly{

    @Override
    public void fly() {
        System.out.println("fly");
        System.out.println("fly");
    }

}

class Boat implements Paddle{

    @Override
    public void paddle() {
        System.out.println("");
        return;
    }

}

class Ship implements Load{

    @Override
    public void load() {
        System.out.println("");
        return;
    }

}

public class ISPCase {}