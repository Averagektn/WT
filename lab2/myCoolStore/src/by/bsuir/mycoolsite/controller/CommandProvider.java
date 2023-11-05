package by.bsuir.mycoolsite.controller;

import by.bsuir.mycoolsite.controller.command.Command;
import by.bsuir.mycoolsite.controller.command.CommandName;
import by.bsuir.mycoolsite.controller.command.impl.AddFilm;
import by.bsuir.mycoolsite.controller.command.impl.Register;
import by.bsuir.mycoolsite.controller.command.impl.SignIn;
import by.bsuir.mycoolsite.controller.command.impl.WrongRequest;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.REGISTER, new Register());
        repository.put(CommandName.ADD_FILM, new AddFilm());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
        //...
    }

    Command getCommand(String name) {
        CommandName commandName = null;
        Command command = null;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            //write log
            command = repository.get(CommandName.WRONG_REQUEST);
        }

        return command;
    }
}
