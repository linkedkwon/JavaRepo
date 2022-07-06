package oop.solid.dip;

//case 1 : not adapted
/**
class PayService{

    private APay a;
    private BPay b;
    private CPay c;

    public void setA(APay a) {
        this.a = a;
    }

    public void setB(BPay b) {
        this.b = b;
    }

    public void setC(CPay c) {
        this.c = c;
    }

    public void executePay(int option){
        switch (option){
            case 1 : setA(new APay()); break;
            case 2 : setB(new BPay()); break;
            case 3 : setC(new CPay()); break;
        }
        //...logic
    }

}

class APay{

    private String pay;

}

class BPay{

    private String pay;
}

class CPay{

    private String pay;

}
*/

//case 2 : adapted
class PayService{

    private PayModule pay;

    public PayService(PayModule pay) {
        this.pay = pay;
    }

    public void executePay(){
        pay.runPay();
    }

}

interface PayModule{
    void runPay();
}

class APay implements PayModule{

    @Override
    public void runPay() {
        System.out.println("APay Run!");
        //..logic
    }

}

class BPay implements PayModule{

    @Override
    public void runPay() {
        System.out.println("BPay Run!");
        //..logic
    }

}

class CPay implements PayModule{

    @Override
    public void runPay() {
        System.out.println("CPay Run!");
        //..logic
    }

}

public class DIPCase {}
