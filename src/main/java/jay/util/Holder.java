package jay.util;

public class Holder<T> {

    private T hold;

    public Holder() {

    }

    public Holder(T hold){
        set(hold);
    }

    public void set(T hold){
        this.hold = hold;
    }

    public T get() {
        return hold;
    }

}
