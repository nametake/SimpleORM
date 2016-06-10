package info.nametake.exception;


public class AnnotationException extends Exception {
    public AnnotationException() {
        super("Not decorated DatabaseTable annotation.");
    }
}
