package oop.inner_class;

import java.time.LocalDate;


//case 1 : Static class
class StaticOuter{

    private static final int A = 20;

    static class StaticClass{
        private static int B = 10;
        private static int C = A + B;

        public static int getC() {
            return C;
        }
    }

}

public class InnerClassCase {

    public static void main(String[] args){
        int sum = StaticOuter.StaticClass.getC();
    }

}

//case 2 : InstanceClass
class InstanceOuter{

    private InstanceClass obj;

    private class InstanceClass{

        public void InnerMethod(){}
    }

    public void executeInnerMethod(){
        obj.InnerMethod();
    }

}

class InstanceClassCase{

    public void useInstanceObject(){
        InstanceOuter obj = new InstanceOuter();
        obj.executeInnerMethod();
    }

}

//case 3 : Local class
class LocalOuter{

    public Object getLoginInfo(String email, String password){

        boolean hasMatched = false;
        //matching logic...

        class LoginResultInfo {
            private LocalDate currentDate;
            private boolean hasLogin;
            private String id = email;
            private String pswd = password;

            public LoginResultInfo(boolean hasLogin, String id, String pswd) {
                this.hasLogin = hasLogin;
                this.id = id;
                this.pswd = pswd;
                currentDate = LocalDate.now();
            }
        }

        LoginResultInfo obj = new LoginResultInfo(hasMatched, email, password);
        return obj;
    }

}

//case 4 : Anonymous class
class Product{

    private static final int COST = 1000;

    public int getCOST() {
        return COST;
    }

}

class AnonymousOuter {

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