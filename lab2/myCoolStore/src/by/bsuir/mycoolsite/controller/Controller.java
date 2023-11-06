package by.bsuir.mycoolsite.controller;

import by.bsuir.mycoolsite.controller.command.Command;

public final class Controller {
    private final CommandProvider provider = new CommandProvider();
    private final char paramDelimeter = ' ';

    public String executeTask(String request) {
        String commandName;
        Command executionCommand;
        String response;

        commandName = request.substring(0, request.indexOf(paramDelimeter));
        executionCommand = provider.getCommand(commandName);
        response = executionCommand.execute(request);

        return response;
    }
}