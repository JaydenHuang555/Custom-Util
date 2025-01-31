package jay.util;

import java.io.File;
import java.io.PrintWriter;

/**
 * @info meant to be an class that can do basic simple logging that supports more advanced method
 */
public abstract class AbstractLogger {

    public final String outputFile;
    public final String prefix;

    private File output;

    private final PrintWriter writer;

    public AbstractLogger(final String ouputFile, final String prefix) throws Exception {
        this.prefix = prefix;
        this.outputFile = ouputFile;
        output = new File(ouputFile);
        writer = new PrintWriter(ouputFile);
    }

    public void close(){
        writer.close();
    }

    public abstract void log(final String output);

    public void logf(final String format, Object ... args){
        StringBuilder builder = new StringBuilder();
        int argsOff = 0;
        for(int i = 0; i < format.length(); i++){
            final char c = format.charAt(i);
            switch(c){
                case '%':
                    builder.append(args[argsOff++]);
                    break;
                default: builder.append(c);
            }
        }
        log(builder.toString());
    }

}
