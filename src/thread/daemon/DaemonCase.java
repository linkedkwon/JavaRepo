package thread.daemon;

//case 1 : Daemon thread case
class CustomThread extends Thread{

    @Override
    public void run() {
        if(Thread.currentThread().isDaemon()){
            while(true){
                try{
                    //waiting
                    Thread.sleep(1000L);
                    /** Daemon Thread Logic */
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }else{
            /** General Thread Logic */
        }
    }

}

public class DaemonCase {

    public static void main(String[] arg){

        CustomThread thread = new CustomThread();
        CustomThread daemonThread = new CustomThread();

        daemonThread.setDaemon(true);
        thread.start();
        daemonThread.start();

    }

}