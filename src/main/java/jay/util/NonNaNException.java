package jay.util;

public class NonNaNException extends RuntimeException {

    public NonNaNException() {
        super("required value can't be not a number (NaN)");
    }

}
