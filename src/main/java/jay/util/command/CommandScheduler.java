package jay.util.command;

import jay.util.Pair;
import jay.util.hashtable.HashTable;
import jay.util.hashtable.VersatileList;

public class CommandScheduler {

    private CommandScheduler instance;
    private VersatileList<Command, CommandInfo> scheduledCommands = new VersatileList<>();

    public CommandScheduler getInstance() {
        if(instance == null) instance = new CommandScheduler();
        return instance;
    }

    private CommandScheduler() {

    }

    private Command getNextScheduled() {
        for(HashTable.Entry<Command, CommandInfo> entry : scheduledCommands) {
            CommandInfo info = entry.val();
        }
    }

    public void run() {
        Pair<Command, CommandInfo> next;
    }

}
