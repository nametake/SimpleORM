package info.nametake.exception;


public class NotImplementedException extends Exception {

    public NotImplementedException() {
        super("Not implemented method");
    }

    public NotImplementedException(String message) {
        super(message);
    }

}
