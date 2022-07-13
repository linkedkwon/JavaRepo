package thread.group;

public class ThreadGroupCase {

    //case 1 : Thread group initialization
    public static void main(String[] args){

        /** Super group - Main thread group */
        ThreadGroup group1 = new ThreadGroup("Group1");

        /** Super group - group1 */
        ThreadGroup group2 = new ThreadGroup(group1, "group2");

    }

    //case 2 : Thread group setting
    static class CustomThread extends Thread{

        public CustomThread(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
            super.run();
            //logic...
        }

    }

    /**
    public static void main(String[] args){

        ThreadGroup threadGroup = new ThreadGroup("threadGroup");

        Thread thread1 = new Thread(threadGroup, "thread1");
        CustomThread thread2 = new CustomThread(threadGroup, "thread2");

        thread1.run();
        thread2.run();
    }
    */
}