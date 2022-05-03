package data_type.copy;

public class CallByValue {

    static class Instance{
        private int num;

        public Instance(int num) {
            this.num = num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

    public static void main(String[] args){
        Instance obj = new Instance(1);
        int num = 2;
        setNum(obj, num);
    }

    /** 주소값을 복사한 새로운 참조 변수 obj 와 복사한 값을 담은 num 변수 **/
    static void setNum(Instance obj, int num){
        obj.setNum(num);
    }

}

