package thread.synchronization;

public class SynchronizationCase {}

//case 1 : synchronized keyword
/**
class Stand{

    private int item;

    public synchronized void stockItem(){
        //logic..
        this.item += 1;
    }

    public synchronized void sellItem(){
        //logic...
        this.item -= 1;
    }

}
*/

//case 2 : wait(), notify()
class Stand{

    private int itemCnt;

    public synchronized void stockItem(){
         if(itemCnt > 99){
             try{
                 wait();
             }catch (Exception e){
                 e.printStackTrace();
             }
         }
         itemCnt += 1;
         notify();
    }

    public synchronized void sellItem(){
        if(itemCnt < 1){
            try{
                wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        itemCnt -= 1;
        notify();
    }

}