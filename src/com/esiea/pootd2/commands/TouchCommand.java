package com.esiea.pootd2.commands;

public class TouchCommand extends Command {

    private final String name;

    public TouchCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}