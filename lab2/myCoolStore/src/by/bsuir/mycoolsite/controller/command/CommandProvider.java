package by.bsuir.mycoolsite.controller.command;

import by.bsuir.mycoolsite.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();
    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.REGISTER, new Register());
        repository.put(CommandName.ADD_FILM, new AddFilm());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
        repository.put(CommandName.SIGN_OUT, new SignOut());
        repository.put(CommandName.ADD_FEEDBACK, new AddFeedback());
        repository.put(CommandName.ADD_TO_CART, new AddToCart());
        repository.put(CommandName.REMOVE_FROM_CART, new RemoveFromCart());
        repository.put(CommandName.BUY, new BuyFilms());
        repository.put(CommandName.BAN, new Ban());
        //...
    }

    public static CommandProvider getInstance(){
        return instance;
    }

    public Command getCommand(String name) {
        CommandName commandName;
        Command command;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            //LOG
            System.out.println("Command provider didn't find command: " + name);
            command = repository.get(CommandName.WRONG_REQUEST);
        }

        return command;
    }
}
