package exception.resource;

import java.io.*;

public class TryWithResourceCase {

    //case 1 : memory leak on try-catch
    public void memoryLeakOnTry(){
        try{
            FileInputStream is = new FileInputStream("resource.txt");
            BufferedInputStream bis = new BufferedInputStream(is);

            int read;
            while((read = bis.read()) != -1){
                System.out.println((char)read);
            }
            //스트림을 닫기 전 예외가 발생한다면?
            is.close();
            bis.close();

        }catch (IOException e) {
            //....처리 로직
            e.printStackTrace();
        }
    }

    //case 2 : return memory on finally
    public void useFinally() throws IOException{
        FileInputStream is = null;
        BufferedInputStream bis = null;

        try{
            is = new FileInputStream("java_repo.txt");
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

    //case 3 : try-with-resources
    void useTryWithResources(){
        try(
            FileInputStream is = new FileInputStream("resource.txt");
            BufferedInputStream bis = new BufferedInputStream(is);
        ){
            int read;
            while((read = bis.read()) != -1){
                System.out.println((char)read);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}

//case 4 : custom class extended autocloseable
class CustomOBJ implements AutoCloseable{

    public void logic(){
        System.out.println("java");
        System.out.println("repo");
    }

    @Override
    public void close() throws Exception {
        //log...
    }

}

class TryWithResource{

    public static void main(String[] args){
        try(
            CustomOBJ obj = new CustomOBJ();
        ){
            obj.logic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}