package thread.scheduling;

public class SchedulingCase {

    //case 1 : thread priority
    public void setThreadPriority(){

        Thread t1 = new Thread("order1"), t2 = new Thread("order2"),
               t3 = new Thread("order3"), t4 = new Thread("order4");

        t1.setPriority(Thread.MAX_PRIORITY); //10
        t2.setPriority(Thread.NORM_PRIORITY); //5
        t3.setPriority(3); /** 1 ~ 10 */
        t4.setPriority(Thread.MIN_PRIORITY); //1

    }

    //case 2 : use sleep()
    public void executeSleep(){

        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    //case 3 : use yield()
    public void executeYield(){

        Thread t = new Thread() {
            boolean stop = false;
            boolean hasWorked = true;

            @Override
            public void run() {
                while(!stop){
                    if(hasWorked) {
                        //logic..
                    }
                    else {
                        Thread.yield();
                    };
                }
            }
        };

        t.run();
    }

    //case 4 : use join()
    public void executeJoin(){

        Thread postThread = new Thread();
        postThread.start();

        Thread preThread = new Thread();
        preThread.setPriority(Thread.MAX_PRIORITY);
        preThread.start();

        try{
            postThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    //case 5 : wait(), notify(), notifyAll()
    public void executeSynchronizedMethod(){

        class Instance{

            public synchronized void logicA(){
                //logic A...
                notify();

                try{
                    wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            public synchronized void logicB(){
                //logic B...
                notify();

                try{
                    wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }

        }

    }

    //case 6 : interrupt()
    public void executeInterrupt() {

        Thread t = new Thread() {
            boolean hasWorked = true;

            @Override
            public void run() {
                while (hasWorked) {
                    //Running..
                    if (Thread.interrupted())
                        break;
                }
            }
        };

        t.run();
        t.interrupt();
    }

}