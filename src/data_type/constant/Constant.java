package data_type.constant;

import java.nio.charset.StandardCharsets;

public class Constant {

    //1. constant two case : - primitive, object
    static boolean checkEmailValidation(String email){
        final int MAX_LENGTH = 20;
        return email.length() > MAX_LENGTH ? false : true;
    }

    static class MemberDTO{

        private final String EMAIL;
        private String password;

        public MemberDTO(String email, String password) {
            this.EMAIL = email;
            this.password = password;
        }

        public void setEncodedPassword(String password){
            this.password = password;
        }
    }

    static String getEncodedPassword(String password) {
        return new String(password.getBytes(StandardCharsets.UTF_8));
    }

    //2. literal case
    static void literalCase(){
        final int MAX_LENGTH = 20;
        final String MAPPING_URL = "/api/v1";
        final String BASE_ROLE = "MEMBER";

    }

    //3. convention
    private static final int PORT = 8888;
    private static final int MAX_LENGTH = 20;
    private static final String MAPPING_URL = "/api/v1/java";

}