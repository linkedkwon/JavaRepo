package memory.leak;

public class Autoboxing {

    public static void main(String[] args){
        int[] arr = new int[1000];
        int sum = getSum(arr);
        System.out.print(sum);
    }

    public static int getSum(int[] arr){
        Integer sum = 0;
        int len = arr.length;

        for(int i = 0; i < len; i++)
            sum += arr[i];

        return sum;
    }

}



