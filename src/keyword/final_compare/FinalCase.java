package keyword.final_compare;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FinalCase {

    //case 1 : keyword final
    public void keywordFinal(){
        final int num = 10;
        final String str = "Hello, Final";
    }

    //case 2 : finally block
    public void blockFinally(){
        String str = "";
        try{
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(System.in));
            str = br.readLine();
            br.close();
        }catch(Exception e){

        }finally{
            if(str.equals(""))
                str = "UNKNOWN ERROR";
        }
    }

    //case 3 : finalize()
    public void methodFinalize(){
        Object obj = new Object();
    }

}