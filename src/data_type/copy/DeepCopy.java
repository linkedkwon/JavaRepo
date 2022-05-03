package data_type.copy;

public class DeepCopy {

    //1. Copy constructor & factory
    static class OBJ{
        private String name;
        private int age;

        public OBJ(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public OBJ newInstance(OBJ obj){
            return new OBJ(obj.name, obj.age);
        }
    }

    //2. Direct copy
    public static void main(String[] args){
        OBJ obj = new OBJ("hello", 20);
        OBJ copied = new OBJ(obj.name, obj.age);
        System.out.println(copied.name + " " + copied.age);
    }


    //3. Cloneable
    static class Clone implements Cloneable{
        private String name;
        private int age;

        public Clone(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        protected Clone clone() throws CloneNotSupportedException{
            return (Clone) super.clone();
        }
    }

    /**
    public static void main(String[] args) throws CloneNotSupportedException {
        Clone clone = new Clone("hello", 20);
        Clone copied = clone.clone();
        System.out.println(copied.name + " " + copied.age);
    }
    */

}
