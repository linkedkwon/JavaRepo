package data_type;

public class Scope {

    public static void main(String[] args){
        /** Scope 2 : 지역변수 **/
        int timber = 100;
        /** Scope 3 : 인스턴스 **/
        Truck truck = new Truck(new Timber(100));
    }
    /** 메인 스레드가 종료되고 지역변수는 할당이 해제되며, 인스턴스는 참조 주소가 없으므로 GC 대상이 된다.**/
}

class Truck{
    /** Scope 1: 전역 변수 **/
    Timber timber;
    static final int MAX_WEIGHT = 1000;

    public Truck(Timber timber) {
        this.timber = timber;
    }

    public void setTimber(Timber timber){
        this.timber = timber;
    }

}

class Timber{
    private int timberCnt;

    public Timber(int timberCnt) {
        this.timberCnt = timberCnt;
    }
}