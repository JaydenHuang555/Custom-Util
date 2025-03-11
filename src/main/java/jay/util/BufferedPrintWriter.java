package jay.util;

import java.io.File;
import java.io.PrintWriter;

public class BufferedPrintWriter extends PrintWriter {

    public BufferedPrintWriter(File file) throws Exception {
        super(file);
    }


    public void append(Object o) {
        String buff = o.toString();
        for(int i = 0; i < buff.length(); i++)
            append(buff.charAt(i));
    }

    @Override
    public void flush() {
        try {
            super.flush();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw e;
        }
    }

}
