package memory.leak;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class Stream {

    public static void main(String[] args){
        setIOStream();
        setConnection();
    }

    public static void setIOStream(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try{
            bw.write(br.readLine().trim()); bw.close(); br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        Path path = Paths.get("/Users/linkedkwon/java_repo");
        try {
            java.util.stream.Stream<Path> stream = Files.list(path);
            stream.filter(e -> e.startsWith("stream"))
                  .forEach(System.out::println);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setConnection(){
        try {
            Connection conn = DriverManager.getConnection("url", "user", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from linkedkwon");
            rs.close(); stmt.close(); conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}