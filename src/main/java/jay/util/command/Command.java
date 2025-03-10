package jay.util.command;

import jay.util.PeriodicClass;
import jay.util.StopWatch;

public abstract class Command extends PeriodicClass {

    protected String name;
    protected double timeoutInSeconds = Double.POSITIVE_INFINITY;
    private StopWatch timeoutStopwatch = new StopWatch();
    private InterruptionBehavior interruptionBehavior = InterruptionBehavior.CANCEL_SELF;

    protected boolean isFinished = false;

    public Command() {
        init();
        timeoutStopwatch.startIfNotStarted();
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
        if(timeoutStopwatch.get() >= timeoutInSeconds) {
            end(false);
        }
    }

}
