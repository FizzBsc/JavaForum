package Controller;

public class WrongDateException  extends Exception {

    public WrongDateException(String e) {
        System.err.println(e);
    }
}
