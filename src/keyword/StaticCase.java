package keyword;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//1. static block
class Cost{

    private static double INGREDIENT;
    private static double MATERIAL;
    private static double EXCHANGE_RATE = 0.8;

    static {
        INGREDIENT = 1000 * EXCHANGE_RATE;
        MATERIAL = 2000 * EXCHANGE_RATE;
    }
}

//2. static variables
class TestResult{

    private static final double PASS_CUT = 80;
    private int id;
    private String name;
    private double average;

    public TestResult(int id, String name, double average) {
        this.id = id;
        this.name = name;
        this.average = average;
    }

    public void hasPassed(){
        if(average >= PASS_CUT)
            System.out.println(this.name + " has passed.");
    }
}

//3 ~ 4. static Inner class
public class StaticCase {

    static class InnerClass{
        public static void printDate(){
            System.out.println(LocalDate.now().format(
                    DateTimeFormatter.ofPattern("yyyy-mm-dd")));
        }
    }

    public static void main(String[] args){
        InnerClass.printDate();
    }

}