package oop.serialization;

import java.io.*;

//case 1 : condition
class Member implements Serializable{

    private String email;
    private transient String password;
    private String name;

    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

}

public class SerializationCase {

    //case 2 : Serialization & Deserialization
    void executeSerialization(){

        File file = new File("./javaRepo.txt");
        Member member = new Member("Hello", "Java", "Repo");
        try(
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(member);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void executeDeSerialization(){

        File file = new File("./javaRepo.txt");
        Member member;
        try(
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
        ){
            member = (Member)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //case 4 : SUID
    private static final long serialVersionUID = 1234567L;

}