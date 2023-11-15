package by.bsuir.mycoolsite.controller.command;

import by.bsuir.mycoolsite.controller.command.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton class for providing commands based on their names.
 */
public final class CommandProvider {
    private static final Logger logger = LogManager.getLogger(CommandProvider.class);
    private static final CommandProvider instance = new CommandProvider();
    private final Map<CommandName, Command> repository = new HashMap<>();

    private CommandProvider() {
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
        repository.put(CommandName.UNBAN, new Unban());
        repository.put(CommandName.EDIT_FILM, new EditFilm());
    }

    /**
     * Gets the instance of the CommandProvider.
     *
     * @return The CommandProvider instance.
     */
    public static CommandProvider getInstance() {
        return instance;
    }

    /**
     * Gets the command based on its name.
     *
     * @param name The name of the command.
     * @return The corresponding command.
     */
    public Command getCommand(String name) {
        CommandName commandName;
        Command command;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            logger.error("Command provider didn't find command: " + name, e);
            command = repository.get(CommandName.WRONG_REQUEST);
        }

        return command;
    }
}
