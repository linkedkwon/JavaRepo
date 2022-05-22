package data_type.generic;

import java.util.List;

//case 1 : Type Generation By Object
class GenerationByObject{

    private Object obj;

    public GenerationByObject(Object obj) {
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

}

class GenerationCase {

    public static void main(String[] args){
        //업 캐스팅 시 별다른 문제가 없다.
        GenerationByObject obj1 = new GenerationByObject("Hello");
        GenerationByObject obj2 = new GenerationByObject(12345);

        //다운 캐스팅 시 문제가 발생할 소지가 있다
        String str = (String)obj1.getObj();
        Integer num = (Integer)obj2.getObj();
    }

}


//case 2 : Type Generation By Generic
class Generic<T>{

    private T obj;

    public Generic(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

}

public class GenericCase{

    public static void main(String[] args){
        Generic<String> obj1 = new Generic<>("Hello");
        Generic<Integer> obj2 = new Generic<>(12345);

        String str = obj1.getObj();
        Integer num = obj2. getObj();

    }

}

//case 3 : Generic Class and Method
class GenericMethod<T>{

    private T obj;

    public T getObj(){
        return obj;
    }

    public void setObj(T obj){
        this.obj = obj;
    }

    public static <T> boolean compareCode(T code1, T code2){
        return code1.equals(code2);
    }

}

//case 4 : Multi Parameter
class MultiParameter<T, M>{

    private T name;
    private M age;

    public MultiParameter(T name, M age) {
        this.name = name;
        this.age = age;
    }

    public T getName() {
        return name;
    }

    public M getAge() {
        return age;
    }

}

//case 5 : Wild Card
class WildCard {

    public void allType(List<?> list){
        StringBuilder sb = new StringBuilder();
        for(Object obj : list)
            sb.append(obj);
        System.out.println(sb);
    }

    public void childType(List<? extends Number> list){
        int sum = 0;
        for(Number e : list)
            sum += e.intValue();
    }

    public void superType(List<? super Integer> list){
        list.add(1);
        list.add(2);
    }

}