package jay.util;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public final class Logger {

    private static File outputFile = null;

    private static void runChecks() {
        if(!outputFile.isFile())
            throw new RuntimeException("output is not an file");
        if(!outputFile.canWrite())
            throw new RuntimeException("unable to write to log file");
    }

    private static StringBuilder buildDatePrefix() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        Holder<Integer> day = new Holder<>(),  month = new Holder<>(), year = new Holder<>();
        Util.fillNumericDayMonthYear(day, month, year);
        Holder<String> raw[] = Util.fillHolderToString(day, month, year);
        Holder<String> dayS = raw[0], monthS = raw[1], yearS = raw[2];
        builder.append(dayS.get()+"/"+monthS.get()+"/"+yearS.get()+"}");
        return builder;
    }

    private static StringBuilder buildTimePrefix() {
        Holder<Integer> hour = new Holder<>(), min = new Holder<>();
        Util.fillHourMinuteSecond(hour, min, new Holder<>());
        Holder<String> raw[] = Util.fillHolderToString(hour, min);
        Holder<String> hourS = raw[0], minS = raw[1];
        StringBuilder builder = new StringBuilder();
        builder.append(hourS.get()+":"+minS.get());
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
        runChecks();
    }

    public static void logf(final String message, Object ... args) {
        Util.requireNonNull(outputFile);
        try (StreamWriter writer = new StreamWriter(outputFile)){
            int argsIndex = 0;
            for(int i = 0; i < message.length(); i++) {
                char c = message.charAt(i);
                if(c == '%') {
                    c = message.charAt(++i);
                    switch (c) {
                        case 'c':
                        case 'd':
                        case 'l':
                        case 'f':
                        case 's':
                            writer.write(args[argsIndex++]);
                            break;
                    }
                }
                else writer.write(c);
            }
            writer.flush();
        } catch (Exception e) {
            System.err.print("unable to log");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
