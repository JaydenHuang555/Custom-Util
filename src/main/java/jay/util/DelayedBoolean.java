package jay.util;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class DelayedBoolean {

    private final double timeout;
    private Supplier<Boolean> supplier;

    private final StopWatch stopWatch;

    public DelayedBoolean(Supplier<Boolean> supplier, double timeInSeconds) {
        Util.requireNonNull(supplier);
        Util.requireNonNaN(timeInSeconds);
        if(timeInSeconds == Double.POSITIVE_INFINITY) {
            System.err.println("unable to set timeout to infinite");
            throw new RuntimeException();
        }
        this.timeout = timeInSeconds;
        this.supplier = supplier;
        stopWatch = new StopWatch();
    }

    public DelayedBoolean(BooleanSupplier boolSupplier, double timeInSeconds) {
        this((Supplier<Boolean>) () -> boolSupplier.getAsBoolean(), timeInSeconds);
    }

    public void start() {
        stopWatch.startIfNotStarted();
    }

    public void waitTillTimePassed() {
        double loopTimeout = 512 * timeout;
        double ll = loopTimeout * 1024;
        int i = 0;
        while(i++ != ll) {
            // TODO: tune
            if(i++ >= ll) {
                System.err.println("stuck in infinite loop");
                throw new RuntimeException();
            }
            if(stopWatch.get() >= loopTimeout) {
                System.err.println("error is happening again with stopwatch, please send info to creator");
                RuntimeException ex = new RuntimeException();
                ex.printStackTrace();
                throw ex;
            }
            if(stopWatch.get() >= timeout) break;
        }
    }

    public boolean passed() {
        return stopWatch.get() >= timeout;
    }

    public boolean get() {
        return supplier.get();
    }
}
