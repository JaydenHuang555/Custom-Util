package jay.util;

import java.io.OutputStream;
import java.util.function.BooleanSupplier;

public class StopWatch extends PeriodicClass {

    private double passed;
    private boolean paused = false;
    private BooleanSupplier started;

    public StopWatch(){
        passed = Double.POSITIVE_INFINITY;
        started = () -> Double.isInfinite(passed);
    }

    public void startIfNotStarted(){
        if(started.getAsBoolean()) {
            passed = 0;
        }
    }

    /**
     * @return time passed in seconds
     * */
    public double get() {
        return passed;
    }

    public void pause(){
        paused = true;
    }

    public void unpause(){
        paused = false;
    }

    public void stop(){
        passed = Double.POSITIVE_INFINITY;
        paused = false;
    }

    @Override
    public void periodic() {
        try {
            if(started.getAsBoolean() && !paused) {
                Thread.sleep(1000);
                passed++;
            }
        } catch (Exception e){}
    }
}
