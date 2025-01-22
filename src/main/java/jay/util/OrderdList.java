package jay.util;

public class OrderdList<T extends Object> {

    private int off = 0, len = 1 << 3;
    private Object buffer[] = new Object[len];

    public OrderdList(){
        buffer = new Object[len];
    }
    @SuppressWarnings("unchecked")
    public OrderdList(final OrderdList<?> other){
        off = other.off;
        len = other.len;
        for(int i = 0; i < other.len; i++) add((T)other.buffer[i]);
    }

    public static <T> OrderdList<T> of(T incoming[]){
        OrderdList<T> list = new OrderdList<>();
        for(int i = 0; i < incoming.length; i++) list.add(incoming[i]);
        return list;
    }

    public void add(T other){
        buffer[off++] = other;
        if(off == len){
            Object next[] = new Object[len *= 2];
            for(int i = 0; i < buffer.length; i++) next[i] = buffer[i];
            buffer = next;
        }
    }
    @SuppressWarnings("unchecked")
    public T get(int index){
        if(index > off || index < 0) throw new RuntimeException("invalid index");
        return (T)buffer[index];
    }

    public int size(){
        return off;
    }

    public void foreach(ForEachFunc f){
        for(int i = 0; i < off; i++) f.f(buffer[i]);
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.add("{ ");
        for(int i = 0; i < off; i++)
            builder.add(String.format("%s, ", buffer[i].toString()));
        builder.add("\b\b }");
        return builder.toString();
    }

}
