package jay.util.command;

import jay.util.DelayedBoolean;
import jay.util.PeriodicClass;
import jay.util.StopWatch;

import java.io.Closeable;
import java.io.IOException;

public abstract class Command extends PeriodicClass implements Closeable {

    protected String name;
    private DelayedBoolean timeout;
    private double timeoutDelay = Double.POSITIVE_INFINITY;
    private boolean isRunning = false;
    private InterruptionBehavior interruptionBehavior = InterruptionBehavior.CANCEL_SELF;

    protected boolean isFinished = false;

    public Command() {
        super();
        init();
        timeout = new DelayedBoolean(() -> timeout.passed(), timeoutDelay);
    }

    public Command(final String name){
        this();
        this.name = name;
    }

    public abstract void init();
    public abstract void execute();
    public boolean isFinished(){
        return isFinished;
    }
    public abstract void end(boolean interrupted);

    public InterruptionBehavior getInterruptionBehavior() {
        return interruptionBehavior;
    }

    public Command withInterruptionBehavior(InterruptionBehavior behavior) {
        interruptionBehavior = behavior;
        return this;
    }

    public Command withName(final String name) {
        this.name = name;
        return this;
    }

    @Override
    public void periodic() {
        timeout.waitTillTimePassed();
        if(timeout.get()) end(true);
    }

    @Override
    public void close()  {
        end(true);
    }
}
