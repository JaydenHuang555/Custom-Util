package jay.util;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

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

    public void set(final OrderedList<?> other) {
        off = other.off;
        len = other.len;
        buffer = new Object[other.size()];
        Util.arrayCopy(buffer, other.buffer);
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
        if(index > off || index < 0) throw new IndexConflictBoundException(index, off - 1);
        return (T)buffer[index];
    }

    public boolean contains(T item){
        for(int i = 0; i < off; i++)
            if(buffer[i].hashCode() == item.hashCode())
                return true;
        return false;
    }

    public void pruneFirst(T target) {
        boolean found = false;
        OrderedList<T> next = new OrderedList<>();
        for(T item : this) {
            if(found || (item.equals(target) || item == target)){
                next.add(item);
            }
            if(item.equals(target) || item == target) {
                found = true;
            }
        }
        set(next);
    }

    public void pruneFirstWithSameAddr(T target) {
        boolean found = false;
        OrderedList<T> next = new OrderedList<>();
        for(T item : this) {
            if(found || target != item) next.add(item);
            else if(target == item) found = true;
        }
        set(next);
    }

    public boolean isEmpty() {
        return off == 0;
    }

    public int size(){
        return off;
    }

    @SuppressWarnings("unchecked")
    public void foreach(Consumer<T> f){
        for(int i = 0; i < off; i++) f.accept((T)buffer[i]);
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
