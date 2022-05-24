package data_type;

//Case 1 : Constant Fields in Class
class ConstantInField{

    //게시글 작성 시 각 항목들의 유효성을 검사하기 위한 상수 집합
    private static final int TITLE_MAX_LENGTH = 50;
    private static final int CONTENT_MAX_LENGTH = 250;
    private static final int TAG_MAX_LENGTH = 20;
    private static final int REFERENCE_MAX_LENGTH = 50;

    /** 문의글 작성 시 각 항목들의 유효성을 검사하기 위한 상수 집합
    private static final int TITLE_MAX_LENGTH = 40;
    private static final int CONTENT_MAX_LENGTH = 150;
    */
    private static final int INQUIRY_TITLE_MAX_LENGTH = 40;
    private static final int INQUIRY_CONTENT_MAX_LENGTH = 150;

    public boolean hasValidatedForPost(String title, String content, String tag,
                                       String reference){
        if(title.length() > TITLE_MAX_LENGTH || content.length() > CONTENT_MAX_LENGTH ||
        tag.length() > TAG_MAX_LENGTH || reference.length() > REFERENCE_MAX_LENGTH)
            return false;
        return true;
    }

    public boolean hasValidatedForInquiry(String title, String content){
        if(title.length() > INQUIRY_TITLE_MAX_LENGTH || content.length() > INQUIRY_CONTENT_MAX_LENGTH)
           return false;
        return true;
    }

}

//Case 2 : Constant Fields By Interface
interface PostValidationByInterface{
    int TITLE_MAX_LENGTH = 50;
    int CONTENT_MAX_LENGTH = 250;
    int TAG_MAX_LENGTH = 20;
    int REFERENCE_MAX_LENGTH = 50;
}

interface InquiryValidationByInterface{
    int TITLE_MAX_LENGTH = 40;
    int CONTENT_MAX_LENGTH = 150;
}

public class EnumCase {

    public static void main(String[] args){
        if(PostValidationByInterface.TITLE_MAX_LENGTH ==
                InquiryValidationByInterface.TITLE_MAX_LENGTH){
            //....
        }
    }

}

//Case 3 : Constant Fields By Class
class PostValidation{
    public static int TITLE_MAX_LENGTH = 50;
    public static int CONTENT_MAX_LENGTH = 250;
    public static int TAG_MAX_LENGTH = 20;
    public static int REFERENCE_MAX_LENGTH = 50;
}

/**
public class EnumCase {

    public static void main(String[] args){
        PostValidation obj = new PostValidation();
        //switch (obj){ }
    }

}
*/

//Case 4 : Basic Enum
enum Coupon{
    KAKAO, NAVER, COUPANG, TOSS;
}

enum ROLE{
    GENERAL, PREMIUM, ADMIN;
}

/**
public class EnumCase {

    public static void main(String[] args){
        Coupon couponType = Coupon.COUPANG;
        String role = ROLE.ADMIN.name();
    }

}
*/


//Case 5 : Enum
enum PostValidationByEnum{
    MAX_TITLE("title", 50),
    MAX_CONTENT("content", 250);

    private final String name;
    private final int len;

    private PostValidationByEnum(String name, int len){
        this.name = name;
        this.len = len;
    }

    public String getName() {
        return name;
    }

    public int getLen() {
        return len;
    }

}