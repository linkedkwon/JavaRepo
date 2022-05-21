package data_type.collection.firstclass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Common Class
class Member{
private long id;
private int age;
private int postCnt;
private String name;
private String coupon;

public long getId() {
    return id;
}

public void setId(long id) {
    this.id = id;
}

public int getAge() {
    return age;
}

public void setAge(int age) {
    this.age = age;
}

public int getPostCnt() {
    return postCnt;
}

public void setPostCnt(int postCnt) {
    this.postCnt = postCnt;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getCoupon() {
    return coupon;
}

public void setCoupon(String coupon) {
    this.coupon = coupon;
}
}


//case 1 : first class collection use case
class WrappingMember1{
    private List<Member> members;

    public WrappingMember1(List<Member> members) {
        this.members = members;
    }

}

//case 2 : filtering
class WrappingMember2{
    private List<Member> members;

    public WrappingMember2(List<Member> members) {
        this.members = members;
    }

    public List<Member> getUpdatedMembersByReview(){
        List<Member> updatedMembers = members.stream()
                .filter( e -> e.getPostCnt() >= 10)
                .collect(Collectors.toList());

        for(Member obj : updatedMembers)
            obj.setPostCnt(obj.getPostCnt() + 1000);

        return updatedMembers;
    }

}

//case 3 : Immutable Collection
class WrappingMember3{

    private final List<Member> members;

    public WrappingMember3(List<Member> members) {
        this.members = members;
    }

    public List<Member> getMembersByAgeOverTen(){
        return members.stream()
                .filter( e -> e.getAge() >= 10)
                .collect(Collectors.toList());
    }

}

//case 4 : Handling Both Collection and Logic on One side
class WrappingMember4{

    private final List<Member> members;

    public WrappingMember4(List<Member> members) {
        this.members = members;
    }

    public int getAllPostCnt(){
        return members.stream()
                .mapToInt(Member::getPostCnt)
                .sum();
    }

}

//case 5 : Wrapping each group to give them naming
class GeneralUser{

    private final List<Member> members;

    public GeneralUser(List<Member> members) {
        this.members = members;
    }

}

class PremiumUser{

    private final List<Member> members;

    public PremiumUser(List<Member> members) {
        this.members = members;
    }

    public void setCoupon(){
        for(Member e : members)
            e.setCoupon("DISCOUNT_FOR_PREMIUM");
    }
}

public class FirstClassCollectionCase{}