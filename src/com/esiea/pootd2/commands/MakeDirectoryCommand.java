package com.esiea.pootd2.commands;

public class MakeDirectoryCommand extends Command {

    private final String name;

    public MakeDirectoryCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}