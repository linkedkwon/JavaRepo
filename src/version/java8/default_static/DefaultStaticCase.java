package version.java8.default_static;

//case 1 : Basic interface
/**
interface Payment{

    int MAX_PAYMENT = 10000000;

    void pay();
    //Added
    void discount();

}
*/

//case 2 : Violation of OCP
/**
class APay implements Payment{

    @Override
    public void pay() {
        discount();
        //logic...
    }

    @Override
    public void discount() {
        //logic...
        System.out.println("execute discount!");
    }

}

class BPay implements Payment{

    @Override
    public void pay() {
        discount();
        //logic...
    }

    @Override
    public void discount() {
        //logic...
        System.out.println("execute discount!");
    }

}

class CPay implements Payment{

    @Override
    public void pay() {
        discount();
        //logic...
    }

    //Not used
    @Override
    public void discount() {
    }
}
*/

//case 3 : default, static method
interface Payment{

    int MAX_PAYMENT = 10000000;

    void pay();

    static boolean hasExceededMaxPayment(int cost){
        return MAX_PAYMENT < cost;
    }

    default void discount(){
        System.out.println("discounted!");
    }

}

//case 4 : adapted default
class APay implements Payment{

    @Override
    public void pay() {
        discount();
        //logic...
    }

    @Override
    public void discount() {
        System.out.println("20% discounted!");
    }

}

class BPay implements Payment{

    @Override
    public void pay() {
        discount();
        //logic...
    }

    @Override
    public void discount() {
        System.out.println("5% discounted!");
    }

}

class CPay implements Payment{

    @Override
    public void pay() {
        //logic..
        System.out.println("Payed!");
    }

}

//case 5 : default method which has same signature
/**
interface EventA{

    default void discount(){
        //logic..
        System.out.println("payed!");
    }

}

interface EventB{

    default void discount(){
        //logic..
        System.out.println("payed!");
    }

}

class PayModule implements EventA, EventB{

}
*/

//case 6 : default method which has same signature
/**
interface EventA{

    default void discount(){
        //logic..
        System.out.println("payed!");
    }

}

interface EventB{

    default void discount(){
        //logic..
        System.out.println("payed!");
    }

}

class PayModule implements EventA, EventB{

    @Override
    public void discount() {
        EventA.super.discount();
        EventB.super.discount();
    }

}
*/

//case 7 : class between interface which has same signature
class EventA{

    public void discount(){
        //logic..
        System.out.println("discounted!");
    }

}

interface EventB{

    default void discount(){
        //logic..
        System.out.println("discounted!");
    }

}

class PayModule extends EventA implements EventB{

    @Override
    public void discount() {
        super.discount();
        //EventB.super.discount();
    }

}

public class DefaultStaticCase {}