package jay.util;

public class UnableToLogException extends RuntimeException {

    public UnableToLogException() {
        this("unable to log");
    }

    public UnableToLogException(final String message) {
        super(message);
        printStackTrace(System.out);
    }

}
