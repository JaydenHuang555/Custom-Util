package jay.util;

public class StringBuilder {

    private char buffer[] = null;
    private int off = 0, len = 1 << 3;

    public StringBuilder(){
        buffer = new char[len];
    }

    public void add(final char c){
        buffer[off++] = c;
        if(off == len){
            char next[] = new char[len *= 2];
            for(int i = 0; i < buffer.length; i++) next[i] = buffer[i];
            buffer = next;
        }
    }

    public void add(final String s){
        for(int i = 0; i < s.length(); i++)
            add(s.charAt(i));
    }

    public void add(final OrderdList<?> list){
        for(int i = 0; i < list.size(); i++)
            add(list.get(i).toString());
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
