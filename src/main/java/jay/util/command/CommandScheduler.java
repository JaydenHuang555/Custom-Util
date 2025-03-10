package jay.util.command;

import jay.util.OrderedList;
import jay.util.hashtable.HashTableList;

public class CommandScheduler {

    private CommandScheduler instance;
    private HashTableList<Command, CommandInfo> scheduledCommands = new HashTableList<>();

    public CommandScheduler getInstance() {
        if(instance == null) instance = new CommandScheduler();
        return instance;
    }

    private CommandScheduler() {

    }

}
