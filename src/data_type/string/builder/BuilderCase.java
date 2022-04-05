package data_type.string.builder;

public class BuilderCase {

    static void addByStringObject(){
        String str = "";
        for(int i = 0; i < 5; i++)
            str += "A";

        //Expected : "AAAAA"
        System.out.println(str);
    }

    static void addByBuilder(){
        StringBuilder sb = new StringBuilder();
        StringBuffer sbf = new StringBuffer();

        for(int i = 0; i < 5; i++){
            sb.append('A');
            sbf.append('A');
        }

        //Expected : "AAAAA"
        System.out.println(sb);
        System.out.println(sbf);
    }

}