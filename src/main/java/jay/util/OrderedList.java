package jay.util;

import java.util.Iterator;
import java.util.List;

public class OrderedList<T extends Object> implements Iterable<T> {

    private int off = 0, len = 0x10;
    private Object buffer[] = new Object[len];

    public OrderedList(){

    }

    public OrderedList(T arr[]){
        addAll(arr);
    }
    

    @SuppressWarnings("unchecked")
    public OrderedList(final OrderedList<?> other){
        off = other.off;
        len = other.len;
        buffer = new Object[len];
        for(int i = 0; i < other.len; i++) add((T)other.buffer[i]);
    }

    public void addAll(T arr[]){
        for(int i = 0; i < arr.length; i++) add(arr[i]);
    }

    public static <T> OrderedList<T> of(T incoming[]){
        OrderedList<T> list = new OrderedList<>();
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

    public boolean contains(T item){
        for(int i = 0; i < off; i++)
            if(buffer[i].hashCode() == item.hashCode())
                return true;
        return false;
    }

    public int size(){
        return off;
    }

    @SuppressWarnings("unchecked")
    public void foreach(ForEachFunc<T> f){
        for(int i = 0; i < off; i++) f.f((T)buffer[i]);
    }

    @Override
    public Iterator<T> iterator(){
        return new OrderedListIterator(this);
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("{ ");
        for(int i = 0; i < off; i++)
            builder.append(String.format("%s, ", buffer[i].toString()));
        builder.append("\b\b }");
        return builder.toString();
    }

    private class OrderedListIterator implements Iterator<T> {
        private int off = 0;
        private OrderedList<T> orderedList;

        private OrderedListIterator(OrderedList<T> orderedList){
            this.orderedList = orderedList;
        }


        @Override
        public boolean hasNext() {
            return off < orderedList.size();
        }

        @Override
        public T next() {
            return orderedList.get(off++);
        }
    }

}
