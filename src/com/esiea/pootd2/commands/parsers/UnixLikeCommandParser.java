package com.esiea.pootd2.commands.parsers;

import com.esiea.pootd2.commands.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnixLikeCommandParser implements ICommandParser {

    @Override
    public Command parse(String input) {
        if (input == null || input.isBlank()) {
            return new ErrorCommand("Command is empty");
        }
        List<String> args = splitArguments(input.trim());
        return mapCommand(args);
    }

    private List<String> splitArguments(String input) {
        String[] parts = input.split("\\s+");
        return new ArrayList<>(Arrays.asList(parts));
    }

    private Command mapCommand(List<String> args) {
        String cmd = args.get(0);

        switch (cmd) {
            case "ls":
                if (args.size() != 1) {
                    return new ErrorCommand("ls doesn't take any argument");
                }
                return new ListCommand();

            case "cd":
                if (args.size() != 2) {
                    return new ErrorCommand("cd needs exactly one path");
                }
                return new ChangeDirectoryCommand(args.get(1));

            case "mkdir":
                if (args.size() != 2) {
                    return new ErrorCommand("mkdir needs a directory name");
                }
                return new MakeDirectoryCommand(args.get(1));

            case "touch":
                if (args.size() != 2) {
                    return new ErrorCommand("touch needs a file name");
                }
                return new TouchCommand(args.get(1));

            default:
                return new ErrorCommand("Unknown command '" + cmd + "'");
        }
    }
}