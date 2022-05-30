package exception.handling;

public class ExceptionHandlingCase {

    //case 1 : 예외 복구
    public String allocateThread() {
        int maxTry = 50;
        while (maxTry-- > 0) {
            try {
                //Request...
                return "accessed";
            } catch (Exception e) {
                //log...
            } finally {
                //closed...
            }
        }
        return "failed";
    }

    //case 2 : 예외 회피
    public String getEmailById(long id) throws Exception {
        String email = "";
        if (email == null || email.isEmpty())
            throw new Exception("email has not found");
        return email;
    }

    public void handler() {
        try {
            getEmailById(10);
        } catch (Exception e) {
            //e.getMessage()...
        }
    }

    //case 3 : 예외 전환
    public void findUserIdByEmail(String email) {
        //try{}catch(SQLException e){throw new DuplicateUserIdException();}
    }

}