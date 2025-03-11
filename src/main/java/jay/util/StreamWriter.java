package jay.util;

import java.io.*;

public class StreamWriter implements Closeable, Flushable {

    private final OutputStream os;
    private boolean autoFlush = false, autoAppend = false;
    private boolean osClosed = true, isClosed = true;
    private final InputStream is;

    public StreamWriter(File file) throws Exception {
        this(new FileOutputStream(file), new FileInputStream(file));
    }

    public StreamWriter(final OutputStream os, final InputStream is) throws Exception {
        this.os = os;
        this.is = is;
        this.autoAppend = is == null ? true : false;
        osClosed = false;
        isClosed = false;
    }

    public StreamWriter withAutoFlush(final boolean autoFlush) {
        setAutoFlush(autoFlush);
        return this;
    }

    public void setAutoFlush(final boolean autoFlush) {
        this.autoFlush = autoFlush;
    }

    public void write(final byte c) {
        try {
            os.write(c);
            if(autoFlush) os.flush();
        } catch (Exception e) {
            System.err.println("unable to write");
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void write(final char c) {
        write((byte)c);
    }

    public void write(final String s) {
        for(int i = 0; i < s.length(); i++) {
            write(s.charAt(i));
        }
    }

    public void write(final Object o) {
        write(String.valueOf(o));
    }

    public void endline() {
        write('\n');
    }

    @Override
    public void flush() {
        try {
            os.flush();
        } catch (Exception e) {
            System.err.println("unable to flush");
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void close() throws IOException {
        if(!osClosed) os.close();
        if(!isClosed) is.close();

    }
}
