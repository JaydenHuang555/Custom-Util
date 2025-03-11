package jay.util;

public class Pair<T1, T2> {

    private T1 i1;
    private T2 i2;

    public Pair() {}

    public Pair(T1 i1, T2 i2) {
        this.i1 = i1;
        this.i2 = i2;
    }

    public Pair<T1, T2> withFirst(T1 i1) {
        this.i1 = i1;
        return this;
    }

    public Pair<T1, T2> withSecond(T2 i2) {
        this.i2 = i2;
        return this;
    }

    public T1 getFirst() {
        return i1;
    }

    public T2 getSecond() {
        return i2;
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object other) {
        if(other != this && !(other instanceof Pair<?, ?>)) return false;
        Pair<T1, T2> next = (Pair<T1, T2>) other;
        return next.getFirst().equals(getFirst()) &&
                next.getSecond().equals(getSecond())
                || this == next;
    }

}
