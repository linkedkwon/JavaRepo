package data_type.string.builder;

public class ComparePerformance {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        //1. execute(need to choose one)
        executeByStringOBJ();
        executeBySB();
        executeBySBF();

        //2. print execution time
        System.out.println("-execution time : " + (System.currentTimeMillis() - start) + "ms");

        //3. print used heap size to mb
        printUsedHeapSize();
    }

    static void getPerformance(){
        long start = System.currentTimeMillis();

        // Each logic ....

        long executionTime = System.currentTimeMillis() - start;
        long usedHeapSize = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    static void executeByStringOBJ() {
        long start = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < 100000; i++)
            str += "A";
        System.out.println("1.By String Object");
    }

    static void executeBySB(){
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 100000; i++)
            sb.append('A');
        System.out.println("2.By String Builder");
    }

    static void executeBySBF(){
        long start = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer();
        for(int i = 0; i < 100000; i++)
            sbf.append('A');
        System.out.println("3.By String Buffer");
    }

    static void printUsedHeapSize(){
        System.out.printf("-used memory size : %.2fMB", getUsedHeapSizeAsMB());
    }

    static double getUsedHeapSizeAsMB(){
        return (double)(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024);
    }

}