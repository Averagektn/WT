package by.bsuir.mycoolsite.controller.command.impl;

import by.bsuir.mycoolsite.controller.command.Command;

public class WrongRequest implements Command {

    @Override
    public String execute(String request) {
        return "WRONG COMMAND";
    }
}
