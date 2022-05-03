package data_type;

public class Initialization {

    private static int breadCnt = 300;
    private static int waterCnt = 100;
    private static int peopleCnt = 10;
    private static int receivedBread;
    private static int receivedWater;
    private static boolean hasOutOfStock;

    static {
        hasOutOfStock = false;
        receivedBread = breadCnt / peopleCnt;
        receivedWater = waterCnt / peopleCnt;
    }

    {
        if(breadCnt == 0 && waterCnt == 0)
            hasOutOfStock = true;
    }

    public Initialization() {
        breadCnt -= receivedBread;
        waterCnt -= receivedWater;
    }

    public Initialization(int peopleCnt) {
        breadCnt -= receivedBread * peopleCnt;
        waterCnt -= receivedWater * peopleCnt;
    }

}