package com.esiea.pootd2.commands;

public class ErrorCommand extends Command {

    private final String message;

    public ErrorCommand(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}