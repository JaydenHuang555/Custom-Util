package jay.util.command;

public class EmptyCommand extends Command {

    public EmptyCommand() {

    }

    @Override
    public void init() {

    }

    @Override
    public void execute(){
        isFinished = true;
    }

    @Override
    public void end(boolean interrupted) {

    }

}
