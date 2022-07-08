package keyword;

//case 1 : Use instanceof keyword
public class InstanceOfCase {

    public static void main(String[] args){
        Sub obj = new Sub();

        boolean case1 = obj instanceof Sub;
        boolean case2 = obj instanceof Super;

        System.out.println(case1 + "," + case2);
    }

}

class Sub extends Super{
        public Sub() {}
    }

class Super{
        public Super() {
        }
    }

//case 2 : Optimization
class Optimization{

    public void changeCode(){
        Object obj = new Object();

        boolean case1 = obj instanceof Object;
        boolean case2 = obj.getClass() == Object.class;

        System.out.println(case1 + "," + case2);

    }

}