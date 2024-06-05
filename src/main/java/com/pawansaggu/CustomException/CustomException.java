package CustomException;

public class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}

class Main {
    public static void main(String[] args) {
        CustomException userException = new CustomException("user not found");
        
        try {
            throw userException;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}