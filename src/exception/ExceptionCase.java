package exception;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ExceptionCase {

    //case 1 : Compile Error
    /**
    public String getSum(){
        int A = "123";
        String B = 123;

        int sum = A + B;
        return sum;

    }
    */

    //case 2 : Runtime Error
    public void setArray(){
        int[] arr = new int[10];

        for(int i = 0; i < 100; i++)
            arr[i] = i + 1;
    }


    //case 3 : try-catch block
    public void readFile(){
        try{
            FileInputStream is = new FileInputStream("exception.txt");
            BufferedInputStream bis = new BufferedInputStream(is);

            int read;
            while((read = bis.read()) != -1){
                System.out.println((char)read);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    //case 4 : Multi-catch block
    public void printArray(){
        try {
            int[] arr = new int[10];
            System.out.println(arr[11]);
        }catch(ArrayIndexOutOfBoundsException | NullPointerException referenceError){
            System.out.println("Reference Error!");
        }catch(Exception e){
            System.out.println("Any Error!");
        }
    }

    //case 5 : finally
    public void readFileWithFinally() throws IOException{
        FileInputStream is = null;
        BufferedInputStream bis = null;

        try{
            is = new FileInputStream("exception.txt");
            bis = new BufferedInputStream(is);

            int read;
            while((read = bis.read()) != -1){
                System.out.println((char)read);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(is != null) is.close();
            if(bis != null) bis.close();
        }

    }

    //case 6: Method printStackTrace() & getMessage()
    public void printArrayWithMsg(){
        try {
            int[] arr = new int[10];
            System.out.println(arr[11]);
        }catch(ArrayIndexOutOfBoundsException | NullPointerException rfe){
            rfe.printStackTrace();
        }catch(Exception e){
            System.out.println("Any Error!" + e.getMessage());
        }
    }

}