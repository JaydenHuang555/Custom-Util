package jay.util;

import static jay.util.Util.memset;

/**
 * string appending in java creates a new string cause java is stupid
 */
public final class StringBuilder {

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

    public void append(final String s){
        for(int i = 0; i < s.length(); i++)
            append(s.charAt(i));
    }

    public void append(Object o){
        append(o.toString());
    }

    public void append(final OrderedList<?> list){
        for(int i = 0; i < list.size(); i++)
            append(list.get(i).toString());
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

}
