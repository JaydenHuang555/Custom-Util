package jay.util;

public class IndexConflictBoundException extends RuntimeException {
    public IndexConflictBoundException() {
        super("index is invalid for list");
    }

    public IndexConflictBoundException(int index, int min, int max) {
        super(Util.format("index %d is invalid, min index is %d, max index is %d", index, min, max));
    }

    public IndexConflictBoundException(int index, int max) {
        this(index, 0, max);
    }

}
