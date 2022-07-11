package thread;

class ByClass extends Thread{

    public ByClass(String name) {
        super(name);
    }

    @Override
    public void run() {
        //logic..
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

}

class ByInterface implements Runnable{

    @Override
    public void run() {
        //logic
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

}

public class ThreadCase {}