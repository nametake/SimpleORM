package info.nametake.exception;

/**
 * Created by shogo on 2016/05/19.
 */
public class AnnotationException extends Exception {
    public AnnotationException() {
        super("Not decorated DatabaseTable annotation.");
    }
}