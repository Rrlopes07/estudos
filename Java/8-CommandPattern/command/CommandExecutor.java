package br.com.refatoracao.command;

public class CommandExecutor{

    public void executeCommand(Command command) {
        command.execute();
    }

}
