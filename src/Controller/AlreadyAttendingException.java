package Controller;

public class AlreadyAttendingException  extends Exception {

    public AlreadyAttendingException(String e) {
        System.err.println(e);
    }

}