package data_type.string;

public class ImmutableObjectString {

    static void makeInstanceByNewKeyword(){
        String str1 = new String("Hello");

        char[] c = {'W', 'o', 'r', 'l', 'd'};
        String str2 = new String(c);
    }

    static void updateUserInfo(String password, String address){
         //Encoding Password..
         //Parsing Address..
         //Save Entity
    }

    static void concatString(){
        String A = "Hello";
        String B = "World";

        String C = "Hello";
        String D = "World";

        String E = A + B;
    }

}
