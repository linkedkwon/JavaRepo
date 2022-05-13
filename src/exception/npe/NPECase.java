package exception.npe;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NPECase {

    //common class & repository
    class Member{

        private long id;
        private String name;
        private String email;
        private String phoneNum;
        private LocalDateTime lastEntered;
        private boolean isPremium;
        //...

        public Member(){}

        public Member(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public Member(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getEmail() {
            return email;
        }
    }

    class Repository{
        public Member findMemberByEmail(String email) {
            return new Member();
        }

        public Member findMemberById(long id){
            return new Member();
        }
    }

    private Repository repository = new Repository();

    //case 1 : NPE case
    public List<Member> findMemberUnderDate(LocalDateTime now){
        //...
        return new ArrayList<>();
    }

    public List<Member> getMembersByPremium(List<Member> list){
        //...
        return new ArrayList<>();
    }

    //case 2 : Null Parameter & Return
    public List<Member> findMemberByHasPayed(int flag){
        //...
        return new ArrayList<>();
    }

    public List<Member> getMembersByHasPayed(int flag){
        //...
        return batchMembers(findMemberByHasPayed(flag));
    }

    public List<Member> batchMembers(List<Member> list){
        //...
        return new ArrayList<>();
    }

    //case 3 : Null Check
    public String getEmailByPhoneNum(String email){
        Member entity = Optional.ofNullable(repository. findMemberByEmail(
                email)).orElseGet(() -> ( new Member("null")));

        return entity.email;
    }

    //case 4 : method chaining
    public String getLowerCaseNameById(long id){
        return repository.findMemberById(id).name.toLowerCase();
    }

    //case 5 : initialization
    public void initInstance(){
        Member member = new Member("", "");
        String str = "";
    }

    //case 6 : toString -> valueOf()
    public String convertIntegerToString(Integer num){
        // return obj.toString(); NPE
        return String.valueOf(num); //return "null"
    }

    //case 7 : equals()
    public boolean compareStr(String str){
        return str.equals("HELLO, NPE!");
    }

    public boolean compareStrByNonNull(String str){
        return "NOT NPE".equals(str);
    }

}