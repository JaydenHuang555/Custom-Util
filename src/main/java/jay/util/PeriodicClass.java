package jay.util;

import java.util.concurrent.TimeUnit;

public abstract class PeriodicClass {

    private final double interval;

    /**
     *
     * @param interval interval in milliseconds
     */
    protected PeriodicClass(double interval) {
        this.interval = interval;
        new Thread(() -> {
            StopWatch stopWatch = new StopWatch();
           while(true) {
               stopWatch.startIfNotStarted();
               if(stopWatch.get() / 100 <= interval) {
                   periodic();
               }
           }
        }).start();
    }

    public PeriodicClass(){
        this(20);
    }

    public abstract void periodic();
}
