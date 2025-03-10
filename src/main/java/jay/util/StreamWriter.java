package jay.util;

import java.io.*;

public class StreamWriter implements Closeable {

    private final OutputStream os;
    private boolean autoFlush = false;

    public StreamWriter(File file) throws Exception {
        this(new FileOutputStream(file));
    }

    public StreamWriter(final OutputStream os) {
        this.os = os;
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
        os.close();
    }

}
