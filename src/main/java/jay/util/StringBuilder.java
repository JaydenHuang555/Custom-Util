package jay.util;

import java.util.Iterator;
import java.util.List;

import static jay.util.Util.memset;

/**
 * string appending in java creates a new string cause java is stupid
 */
public final class StringBuilder implements Iterable{

    private char buffer[] = null;
    private int off = 0, len = 1 << 3;

    public StringBuilder(){
        buffer = new char[len];
    }

    public void append(final char c){
        buffer[off++] = c;
        if(off == len){
            char next[] = new char[len *= 2];
            for(int i = 0; i < buffer.length; i++) next[i] = buffer[i];
            buffer = next;
        }
    }

    public void append(final char next[]) {
        for(int i = 0; i < next.length; i++) append(next[i]);
    }

    public void append(final String s){
        for(int i = 0; i < s.length(); i++)
            append(s.charAt(i));
    }

    public void append(Object o){
        append(o.toString());
    }

    public void append(final OrderedList<?> list){
        for(Object auto : list) append(auto);
    }

    public void append(final List<?> list){
        for(Object o : list) append(o.toString());
    }

    public void push(final char c){
        char next[] = new char[++off];
        for(int i = 1; i < next.length; i++) next[i] = buffer[i];
        next[0] = c;
        buffer = next;
    }

    public int size(){
        return off;
    }

    public boolean isEmpty(){
        return off == 0;
    }

    public void foreach(ForEachFunc f){
        for(int i = 0; i < off; i++) f.f(buffer[i]);
    }

    @Override
    public String toString(){
        char next[] = new char[off];
        for(int i = 0; i < off; i++) next[i] = buffer[i];
        return new String(next);
    }
    @Override
    public Iterator<Character> iterator() {
        return new JayClassIterator(this);
    }

    private class JayClassIterator implements Iterator<Character> {

        private int i = 0;
        private String buffer;
        private JayClassIterator(StringBuilder buffer){
            this.buffer = buffer.toString();
        }

        @Override
        public boolean hasNext() {
            return i < buffer.length();
        }

        @Override
        public Character next() {
            return buffer.charAt(i++);
        }
    }

}
