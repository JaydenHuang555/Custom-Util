package jay.util;

import java.io.OutputStream;
import java.util.function.BooleanSupplier;

public class StopWatch {

    private final long startTimeStamp;
    private long currentTimeStamp;

    public StopWatch() {
        startTimeStamp = System.currentTimeMillis();
        currentTimeStamp = -1;
    }

    public void start() {
        new Thread(() -> {
            while(true) currentTimeStamp = System.currentTimeMillis();
        });
    }

    public void startIfNotStarted() {
        if(currentTimeStamp == -1) {
            start();
        }
    }

    public double get() {
        return (double)currentTimeStamp / 100d;
    }

    public void reset() {
        currentTimeStamp = -1;
    }

}
