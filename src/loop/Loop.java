package loop;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Loop {

    static void loop(){
        int[] arr = new int[10];
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > 10)
                break;
        }
    }

    static void iterator(){
        List<String> list = Arrays.asList("spring", "boot", "MSA");
        Iterator<String> iterator = list.iterator();

        while(iterator.hasNext()){
            String obj = iterator.next();
            iterator.remove();
        }
    }

    static void foreach(){
        StringBuilder sb = new StringBuilder();
        List<String> list = Arrays.asList("s", "p", "r", "i", "n", "g");
        for(String str : list)
            sb.append(str);
    }

}