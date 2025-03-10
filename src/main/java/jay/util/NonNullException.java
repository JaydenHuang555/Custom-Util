package jay.util;

public class NonNullException extends RuntimeException {

    public NonNullException() {
        this("required non null variable");
    }

    public NonNullException(String message) {
        super(message);
    }
}
