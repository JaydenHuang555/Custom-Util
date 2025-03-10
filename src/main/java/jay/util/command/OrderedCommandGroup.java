package jay.util.command;

import jay.util.OrderedList;

public class OrderedCommandGroup extends Command{

    private OrderedList<Command> commands = new OrderedList<>();

    public OrderedCommandGroup(Command ... commands) {
        this.commands = new OrderedList<>(commands);
    }

    @Override
    public void init() {
        for(Command command : commands) {
            command.init();
        }
    }

    @Override
    public void execute() {
        for(Command command : commands) {
            command.execute();
        }
        isFinished = true;
    }

    @Override
    public void end(boolean interrupted) {
        for(Command command : commands) {
            command.end(interrupted);
        }
    }
}
