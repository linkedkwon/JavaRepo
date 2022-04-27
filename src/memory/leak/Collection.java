package memory.leak;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Collection {

    static class User{
        private String name;

        public User(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args){
        List<User> userList = new ArrayList<>();
        userList.add(new User("AAA"));
        userList.add(new User("BBB"));

        writeAllUserInfo(userList);
        userList.clear();
    }

    public static void writeAllUserInfo(List<User> list){
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        for(User obj : list)
            sb.append(obj.name).append('\n');

        try {
            bw.write(sb.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

