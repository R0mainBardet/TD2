package com.esiea.pootd2.interfaces;

import com.esiea.pootd2.controllers.IExplorerController;

import java.util.Scanner;

public class TextInterface implements IUserInterface {

    private final IExplorerController controller;

    public TextInterface(IExplorerController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                if (!scanner.hasNextLine()) {
                    break;
                }
                String commandLine = scanner.nextLine();
                if (commandLine == null) {
                    break;
                }
                commandLine = commandLine.trim();
                if ("exit".equalsIgnoreCase(commandLine)) {
                    break;
                }
                if (commandLine.isEmpty()) {
                    continue;
                }
                String result = controller.executeCommand(commandLine);
                if (result != null && !result.isEmpty()) {
                    System.out.println(result);
                }
            }
        }
    }
}