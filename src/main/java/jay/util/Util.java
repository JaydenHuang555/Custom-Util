package jay.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Util {



    public static class TimeAndDateUtil {

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
         * @param month month date
         * @param day day date
         * @param year year date
         */
        public static void fillNumericMonthDayYear(Holder<Integer> month, Holder<Integer> day, Holder<Integer> year) {
            fillNumericDayMonthYear(day, month, year);
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

    }

    public static Holder<?>[] holderToArray(Holder<?> ... holders) {
        Holder<?> buffer[] = new Holder[holders.length];
        for(int i = 0; i < holders.length; i++){
            buffer[i] = holders[i];
        }
        return buffer;
    }

    public static String[] holderToStringArray(Holder<?> ... holders) {
        String buffer[] = new String[holders.length];
        for(int i = 0; i < holders.length; i++) {
            requireNonNull(holders[i]);
            buffer[i] = holders[i].toString();
        }
        return buffer;
    }

    public static void writeToFile(File output, final String message) {
        try (FileOutputStream stream = new FileOutputStream(output)) {
            for(int i = 0; i < message.length(); i++)
                stream.write(message.charAt(i));
        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new RuntimeException("unable to write to file");
        }
    }

    public static void writeToFile(File output, Object o) {
        writeToFile(output, o.toString());
    }

    public static void requireNonNull(Object ... o){
        if(o == null) throw new NonNullException();
        for(int i = 0; i < o.length; i++)
            if(o[i] == null)
                throw new NonNullException();
    }

    public static boolean containsNull(Object ... o) {
        if(o == null) return true;
        try {
            for(int i = 0; i < o.length; i++)
                requireNonNull(o[i]);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static void requireNonNaN(Double ... nums) {
        for(int i = 0; i < nums.length; i++) {
            requireNonNull(nums[i]);
            if (nums[i].isNaN())
                throw new NonNaNException();
        }
    }

    public static String format(final String message, Object ... args) {
        int argsIndex = 0;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < message.length(); i++) {
            Object target = message.charAt(i);
            if(message.charAt(i) == '%') {
                switch(message.charAt(++i)){
                    case '%':
                        break;
                    case 'd':
                    case 's':
                    case 'f':
                    case 'c':
                    case 'l':
                        target = args[argsIndex++];
                }
            }
            builder.append(target);
        }
        return builder.toString();
    }

    public static String formatln(final String message, Object ... args) {
        return format(message, args) + '\n';
    }

    /**
     * less long names
    * */
    public static void printf(final String message, Object ... args) {
        String format = format(message, args);
        System.out.printf(format);
    }

    public static void printfln(final String message, Object ... args) {
        System.out.printf(Util.formatln(message, args));
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

    public static <T> void memset(T buffer[], T item) {
        memset(buffer, item, buffer.length);
    }

}
