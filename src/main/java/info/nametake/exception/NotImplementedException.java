package info.nametake.exception;

/**
 * Created by shogo on 2016/05/27.
 */
public class NotImplementedException extends Exception {

    public NotImplementedException() {
        super("Not implemented method");
    }

    public NotImplementedException(String message) {
        super(message);
    }

}
