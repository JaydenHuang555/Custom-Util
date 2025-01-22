package jay.util;

public class Util {

    public static <T> void memset(T buffer[], T item, int off, int len){
        for(int i = off; i < len; i++) buffer[i] = item;
    }

    public static <T> void memset(T buffer[], T item, int len){
        memset(buffer, item, 0, len);
    }
}
