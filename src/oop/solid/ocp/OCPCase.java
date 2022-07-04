package oop.solid.ocp;

//case 1 : OCP not adapted
/**
class PayService{

    private APay aPay;

    public void setAPay(APay aPay) {
        this.aPay = aPay;
    }

    public void executePay(){
        setAPay(new APay());
        if(aPay.hasAuthenticated())
            aPay.pay();
    }

}

class APay{

    public boolean hasAuthenticated(){
        //....
        return true;
    }

    public void pay(){
        //....
        System.out.println("Execute Pay");
    }

}
*/

//case 2 : OCP not adapted
/**
class PayService{

    private APay aPay;
    private BPay bPay;

    public void setBPay(BPay bPay) {
        this.bPay = bPay;
    }

    public void setAPay(APay aPay) {
        this.aPay = aPay;
    }

    public void executePay(int option){
        if(option == 1) {
            setAPay(aPay);
            if(aPay.hasAuthenticated())
                aPay.pay();
        }else {
            setBPay(new BPay());
            if (bPay.hasAuthenticated())
                bPay.pay();
        }
    }

}

class BPay{

    public boolean hasAuthenticated(){
        //...
        return true;
    }

    public void pay(){
        //...
        System.out.println("Execute Pay");
    }

}

class APay{

    public boolean hasAuthenticated(){
        //....
        return true;
    }

    public void pay(){
        //....
        System.out.println("Execute Pay");
    }

}
*/

//case 3 : OCP adapted
interface Pay{

    boolean hasAuthenticated();
    void pay();

}

class APay implements Pay{

    @Override
    public boolean hasAuthenticated() {
        //..
        return false;
    }

    @Override
    public void pay() {
        //..
        System.out.println("Execute A Pay");
    }

}

class BPay implements Pay{

    @Override
    public boolean hasAuthenticated() {
        //..
        return false;
    }

    @Override
    public void pay() {
        //..
        System.out.println("Execute B Pay");
    }

}

//case 4 : OCP adapted
class PayService{

    private Pay pay;

    public PayService(Pay pay) {
        this.pay = pay;
    }

    public void executePay(){
        if(pay.hasAuthenticated())
            pay.pay();
    }

}

public class OCPCase {

    void pay(){
        PayService obj = new PayService(new BPay()); // or APay()
        obj.executePay();
    }

}