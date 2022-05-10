package data_type.wrapper;

public class WrapperCase {

    public int boxingAndUnboxing(){
        Integer boxing = Integer.valueOf(10);
        int unboxing = boxing.intValue();

        return unboxing;
    }

    private int num;

    public int AutoBoxing(){
        Integer boxing = 100;
        int unboxing = boxing;

        return unboxing;
    }

    public Integer getBoxing(){
        return num;
    }

    public Integer getSkipProcess(){
        return Integer.valueOf(num);
    }

    public int getSuForPrimitive(){
        int sum = 0;
        for(int i = 1; i <= 100; i++)
            sum += i;

        return sum;
    }

    public int getSumForWrapper(){
        Integer sum = 0;
        for(int i = 1; i <= 100; i++)
            sum += i;

        return sum;
    }

}