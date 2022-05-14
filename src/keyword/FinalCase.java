package keyword;

public class FinalCase {


    //common class
    class Member{
        private String name;

        public void setName(String name) {
            this.name = name;
        }
    }

    //case 1 : final variables
    private static final int COST = 1000;
    private final int PAYED;
    private int SELECTED_CNT;

    public FinalCase(int selectedCnt){
        SELECTED_CNT = selectedCnt;
        PAYED = COST * SELECTED_CNT;
    }

    //case 2 : final instance
    public void finalInstance(){
        final Member entity = new Member();
        //entity = new Member(); //불가능
        entity.setName("HELLO, JAVA");
    }

    //case 3 : final parameter
    public void finalParameter(final long id, final Member member){
        //id = 10; //불가능
        //member = new Member(); //불가능
    }

    //case 4 : final method
    class Unit{
        public final void move(){}

        public void upgrade(){}
    }

    class SCV extends Unit{
        private int x;
        private int y;
        private int damage;

        /**
        @Override
        public final void move(){}//재정의 불가능
        */

        @Override
        public void upgrade(){
            damage++;
        }
    }

    //case 5 : final class
    final class Constants{
        public static final int CNT = 100;
        public static final int SIZE = 10;
        public static final int LENGTH = 10;

    }
    //class Capacity extends Constants{} //상속 불가능

}