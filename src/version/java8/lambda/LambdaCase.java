package version.java8.lambda;

public class LambdaCase {

    interface Payment{
        void pay();
    }

    //case 1 : original anonymous method vs lambda adapted
    public void original(){
        Payment payModule = new Payment() {
            @Override
            public void pay() {
                System.out.println("Pay Logic!");
            }
        };

        payModule.pay();
    }

    public void lambda(){
        Payment payModule = () -> System.out.println("Pay Logic!");
        payModule.pay();
    }

    //case 2 : lambda expression
    interface Coupon{
        Integer discountByCouponName(int cost, String couponCode);
    }

    public void expression(){
        Coupon obj = (cost, code) -> code.equals("1234") ? cost /= 2 : cost;

        int cost = obj.discountByCouponName(30000, "1234");
        System.out.println(cost);
    }

    //case 3 : convention
    interface Event{
        int discount(int cost);
    }

    public void convention(){
        Event obj = cost -> cost /= 2;

        int cost = obj.discount(10000);
        System.out.println(cost);
    }

}