package jay.util.stopwatch;

import jay.util.Util;

public abstract class StopWatch {

    public static boolean wantsOptimized = true;

    public StopWatch() {

    }

    public abstract void start();
    public abstract boolean hasStarted();

    public void startIfNotStarted() {
        if(!hasStarted()) start();
    }

    public abstract long getTimeInMilliSeconds();

    public double getTimeInSeconds() {
        return 0;
    }

}
