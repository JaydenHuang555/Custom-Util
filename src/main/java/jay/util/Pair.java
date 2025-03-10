package jay.util;

public class Pair<T1, T2> {

    private T1 i1;
    private T2 i2;

    public Pair(T1 i1, T2 i2) {
        this.i1 = i1;
        this.i2 = i2;
    }

    public void setFirst(T1 i1) {
        this.i1 = i1;
    }

    public void setSecond(T2 i2) {
        this.i2 = i2;
    }

    public T1 getFirst() {
        return i1;
    }

    public T2 getSecond() {
        return i2;
    }

}
