package oop.overloading;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//case 1 : overloading constructor
class Member {

    //common
    private long id;
    private String email;
    private String password;

    //for OAuth
    private String provider;

    //general
    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //for OAuth
    public Member(String email, String password, String provider) {
        this.email = email;
        this.password = password;
        this.provider = provider;
    }

}

//case 2 : how to use
public class OverloadingCase {

    public void process(List<?> list){
        System.out.println("");
        //..
    }

    public void process(ArrayList<?> list){
        System.out.println("");
        //..
    }

    public void process(LinkedList<?> list){
        System.out.println("");
        //..
    }

}