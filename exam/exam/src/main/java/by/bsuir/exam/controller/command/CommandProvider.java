package by.bsuir.exam.controller.command;

import by.bsuir.exam.controller.command.impl.DomParser;
import by.bsuir.exam.controller.command.impl.SaxParser;
import by.bsuir.exam.controller.command.impl.StaxParser;
import by.bsuir.exam.controller.command.impl.WrongRequest;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();
    private final Map<CommandName, Command> repository = new HashMap<>();

    private CommandProvider() {
        repository.put(CommandName.DOM, new DomParser());
        repository.put(CommandName.SAX, new SaxParser());
        repository.put(CommandName.STAX, new StaxParser());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String name) {
        CommandName commandName;
        Command command;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }

        return command;
    }
}
