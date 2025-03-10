package jay.util.command;

public class InstantCommand extends Command {

    private Runnable runnable;
    private boolean isFinished = false;

    public InstantCommand(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void init() {

    }

    @Override
    public void execute() {
        runnable.run();
        isFinished = true;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
