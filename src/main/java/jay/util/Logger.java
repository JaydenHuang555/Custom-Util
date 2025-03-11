package jay.util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import jay.util.Util.TimeAndDateUtil;

public final class Logger {

    private static File outputFile = null;

    private static final char VALID_FLAGS[] = new char[]{'c', 's', 'd', 'f', 'l'};

    private static void runChecks() {
        if(!outputFile.isFile())
            throw new RuntimeException("output is not an file");
        if(!outputFile.canWrite())
            throw new RuntimeException("unable to write to log file");
        if(outputFile.isDirectory())
            throw new RuntimeException("out file is an directory");
    }

    private static StringBuilder buildDatePrefix() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        Holder<Integer> day = new Holder<>(),  month = new Holder<>(), year = new Holder<>();
        TimeAndDateUtil.fillNumericDayMonthYear(day, month, year);
        String raw[] = Util.holderToStringArray(day, month, year);
        String dayS = raw[0], monthS = raw[1], yearS = raw[2];
        builder.append(dayS+"/"+monthS+"/"+yearS+"}");
        return builder;
    }

    private static StringBuilder buildTimePrefix() {
        Holder<Integer> hour = new Holder<>(), min = new Holder<>();
        TimeAndDateUtil.fillHourMinuteSecond(hour, min, new Holder<>());
        String raw[] = Util.holderToStringArray(hour, min);
        String hourS = raw[0], minS = raw[1];
        StringBuilder builder = new StringBuilder();
        builder.append(hourS+":"+minS);
        return builder;
    }

    private static StringBuilder buildPrefix() {
        String date = buildDatePrefix().toString();
        String time = buildTimePrefix().toString();
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(date);
        builder.append("-");
        builder.append(time+"]"+"::");
        return builder;
    }

    public static void setFile(final String fname) {
        setFile(new File(fname));
    }

    public static void setFile(final File file){
        Util.requireNonNull((outputFile = file));
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            if(!file.isFile()) file.createNewFile();
            for(int i = 0; i < 128; i++)
                writer.append('-');
            runChecks();
            writer.append('\n');
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static boolean isValidFormatFlag(final char c) {
        for(int i = 0; i < VALID_FLAGS.length; i++)
            if(c == VALID_FLAGS[i]) return true;
        return false;
    }


    public static void logf(final String message, Object ... args) {
        Util.requireNonNull(outputFile);
        try (StreamWriter writer = new StreamWriter(outputFile)){
            writer.write(buildPrefix());
            int argsIndex = 0;
            for(int i = 0; i < message.length(); i++) {
                char c = message.charAt(i);
                Object target = c;
                if(c == '%') {
                    c = message.charAt(++i);
                    if(!isValidFormatFlag(c)) {
                        System.err.println("invalid format found");
                        UnableToLogException ex = new UnableToLogException();
                        throw ex;
                    }
                    target = args[argsIndex++];
                }
                Util.writeToFile(outputFile, target);
            }
            writer.write('\n');
            writer.flush();
        } catch (Exception e) {
            System.err.print("unable to log");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
