package thread.pool;

import java.util.concurrent.*;

public class PoolCase {

    //case 1, 2, 3
    void makeThreadPool(){

        //ExecutorService executorService = Executors.newCachedThreadPool();
        //ExecutorService executorService = Executors.newFixedThreadPool(5);
        ExecutorService threadPool = new ThreadPoolExecutor(
                10, 100, 120L,
                TimeUnit.SECONDS, new SynchronousQueue<Runnable>()
        );

    }

    //case 4 : execution
    static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Runnable task = new Runnable() {
            @Override
            public void run() {
                //Task logic...
            }
        };

        executorService.execute(task);
        executorService.submit(task);

        executorService.shutdown();

    }

}