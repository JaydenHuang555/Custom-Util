package jay.util;

public class EmptyListException extends RuntimeException{

    public EmptyListException() {
        super("list is empty, breach in access");
        printStackTrace(System.out);
    }

}
