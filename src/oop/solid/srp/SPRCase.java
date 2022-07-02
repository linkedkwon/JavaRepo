package oop.solid.srp;

//case 1 : SRP not adapted
/**
class Member{

    private String id;
    private String password;

    //1. Property
    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }

    //2. Database
    public void save(){
        Member entity = new Member(this.id, this.password);
        //query....
    }

    public boolean findByIdAndPassword(){
        Member entity = null;
        //query....
        return entity == null ? false : true;
    }

}
*/

//case 2 : SRP not adapted after modification
/**
class Member{

    private String id;
    private String password;
    private String name;

    //1. Property
    public Member(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    //2. Database
    public void save(){
        Member entity = new Member(this.id, this.password, this.name);
        //query....
    }

    public boolean findByIdAndPassword(){
        Member entity = null;
        //query....
        return entity == null ? false : true;
    }

}
*/

//case 3 : SRP adapted
/**
class Member{

    private String id;
    private String password;

    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }

}
*/

//case 4 : SRP adapted with modification
class Member{

    private String id;
    private String password;
    private String name;

    public Member(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

}

class MemberRepository{

    public void save(Member member){
        System.out.println("");
        member.hashCode();
    }

    public Member findByIdAndPassword(String id, String password){
        //query..
        return new Member("", "", "");
    }

}

public class SPRCase {}