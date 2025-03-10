package jay.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Util {

    /**
     * @param day date
     * @param month month date
     * @param year year date
     */
    public static void fillNumericDayMonthYear(Holder<Integer> day, Holder<Integer> month, Holder<Integer> year) {
        Calendar calendar = new GregorianCalendar();
        day.set(calendar.get(Calendar.DATE));
        month.set(calendar.get(Calendar.MONTH));
        year.set(calendar.get(Calendar.YEAR));
    }

    /**
     *
     * @param month month date
     * @param day day date
     * @param year year date
     */
    public static void fillNumericMonthDayYear(Holder<Integer> month, Holder<Integer> day, Holder<Integer> year) {
        fillNumericDayMonthYear(day, month, year);
    }

    public static Holder<String>[] fillHolderToString(Holder<?> ... holders) {
        requireNonNull(holders);
        Holder<String> buffer[] = new Holder[holders.length];
        for(int i = 0; i < holders.length; i++) {
            buffer[i].set(holders[i].toString());
        }
        return buffer;
    }

    public static String[] holderToStringArray(Holder<?> ... holders) {
        requireNonNull(holders);
        String buffer[] = new String[holders.length];
        for(int i = 0; i < holders.length; i++)
            buffer[i] = holders[i].toString();
        return buffer;
    }

    public static void writeToFile(File output, final String message) {
        try (FileOutputStream stream = new FileOutputStream(output)) {
            for(int i = 0; i < message.length(); i++)
                stream.write(message.charAt(i));
        } catch (Exception e) {
            throw new RuntimeException("unable to write to file");
        }
    }

    public static void fillHourMinuteSecond(Holder<Integer> hour, Holder<Integer> minute, Holder<Integer> second) {
        Util.requireNonNull(hour, minute, second);
        Calendar calendar = new GregorianCalendar();
        hour.set(calendar.get(Calendar.HOUR_OF_DAY));
        minute.set(calendar.get(Calendar.MINUTE));
        second.set(calendar.get(Calendar.SECOND));
    }

    public static void fillSecondMinuteHour(Holder<Integer> sec, Holder<Integer> min, Holder<Integer> hour) {
        fillHourMinuteSecond(hour, min, sec);
    }


    public static void requireNonNull(Object ... o){
        if(o == null) throw new NonNullException();
        for(int i = 0; i < o.length; i++)
            if(o[i] == null) throw new NonNullException();
    }

    public static <T> T[] arrayCopy(T src[], T dst[]){
        return arrayCopy(src, dst, dst.length);
    }

    public static <T> T[] arrayCopy(T src[], T dst[], int len){
        return arrayCopy(src, dst, 0, len);
    }

    public static <T> T[] arrayCopy(T src[], T dst[], int off, int len){
        for(int i = off; i < len; i++)
            src[i] = dst[i];
        return src;
    }

    public static <T> void memset(T buffer[], T item, int off, int len){
        for(int i = off; i < len; i++) buffer[i] = item;
    }

    public static <T> void memset(T buffer[], T item, int len){
        memset(buffer, item, 0, len);
    }
}
