package thread.forkjoinpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinPoolCase {

    //case 1 : forkJoinPool instance
    public void makeForkJoinPool(){
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
    }

    //case 2, 3 : RecursiveAction/task
    public static void main(String[] args){

        ForkJoinPool pool = new ForkJoinPool(10);

        RecursiveAction task = new RecursiveAction() {

            @Override
            protected void compute() {
                //logic..
                System.out.println("ForkJoinPool task...");

                //fork task
                ArrayList<RecursiveAction> subtasks = new ArrayList<>();
                subtasks.addAll(getSubtasks());

                for(RecursiveAction e : subtasks)
                    e.fork();

            }

            private List<RecursiveAction> getSubtasks(){
                //define subtask...
                return new ArrayList<>();
            }
        };

        pool.invoke(task);

    }

}