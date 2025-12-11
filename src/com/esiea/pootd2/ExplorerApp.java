package com.esiea.pootd2;

import com.esiea.pootd2.controllers.ExplorerController;
import com.esiea.pootd2.controllers.IExplorerController;
import com.esiea.pootd2.interfaces.IUserInterface;
import com.esiea.pootd2.interfaces.TextInterface;
import com.esiea.pootd2.interfaces.HttpInterface;

public class ExplorerApp {

    public static void main(String[] args) {
        IExplorerController controller = new ExplorerController();
        String mode = (args.length > 0) ? args[0] : "text";

        IUserInterface ui;
        switch (mode) {
            case "http" -> ui = new HttpInterface(controller);
            case "text" -> ui = new TextInterface(controller);
            default -> {
                System.out.println("Usage: java -cp <classpath> com.esiea.pootd2.ExplorerApp [text|http]");
                return;
            }
        }

        ui.run();
    }
}