package Model;

import Interfaces.Command;

public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        try {
            command.execute();
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
}
