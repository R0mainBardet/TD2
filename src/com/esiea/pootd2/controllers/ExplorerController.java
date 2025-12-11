package com.esiea.pootd2.controllers;

import com.esiea.pootd2.commands.*;
import com.esiea.pootd2.commands.parsers.ICommandParser;
import com.esiea.pootd2.commands.parsers.UnixLikeCommandParser;
import com.esiea.pootd2.models.FileInode;
import com.esiea.pootd2.models.FolderInode;
import com.esiea.pootd2.models.Inode;

public class ExplorerController implements IExplorerController {

    private final FolderInode root;
    private FolderInode currentFolder;
    private final ICommandParser parser;

    public ExplorerController() {
        this.root = new FolderInode("/");
        this.currentFolder = root;
        this.parser = new UnixLikeCommandParser();
    }

    @Override
    public String executeCommand(String commandStr) {
        Command command = parser.parse(commandStr);
        return doCommand(command);
    }

    private String doCommand(Command command) {
        if (command instanceof ListCommand c) {
            return doCommand(c);
        } else if (command instanceof ChangeDirectoryCommand c) {
            return doCommand(c);
        } else if (command instanceof MakeDirectoryCommand c) {
            return doCommand(c);
        } else if (command instanceof TouchCommand c) {
            return doCommand(c);
        } else if (command instanceof ErrorCommand c) {
            return doCommand(c);
        }
        return "Error: Unknown command type";
    }

    private String doCommand(ListCommand cmd) {
        StringBuilder output = new StringBuilder();
        for (Inode child : currentFolder.getChildren()) {
            output.append(child.getName())
                    .append(" ")
                    .append(child.getSize())
                    .append(System.lineSeparator());
        }
        return output.toString().stripTrailing();
    }

    private String doCommand(ChangeDirectoryCommand cmd) {
        String path = cmd.getPath();
        if (path == null || path.isEmpty()) {
            return "Path can't be empty";
        }
        if (".".equals(path)) {
            return "";
        }
        if ("..".equals(path)) {
            if (currentFolder.getParent() != null) {
                currentFolder = currentFolder.getParent();
                return "";
            }
            return "You are already at root";
        }

        String[] parts = path.split("/");
        FolderInode folderCursor = currentFolder;
        for (String part : parts) {
            if (part.isEmpty() || ".".equals(part)) {
                continue;
            }
            if ("..".equals(part)) {
                if (folderCursor.getParent() != null) {
                    folderCursor = folderCursor.getParent();
                } else {
                    return "Unknown folder: " + path;
                }
                continue;
            }
            FolderInode next = null;
            for (Inode child : folderCursor.getChildren()) {
                if (child instanceof FolderInode folder && child.getName().equals(part)) {
                    next = folder;
                    break;
                }
            }
            if (next == null) {
                return "Unknown folder: " + path;
            }
            folderCursor = next;
        }
        currentFolder = folderCursor;
        return "";
    }

    private String doCommand(MakeDirectoryCommand cmd) {
        String path = cmd.getName();
        if (path == null || path.isEmpty()) {
            return "Directory name can't be empty";
        }
        String[] parts = path.split("/");
        FolderInode folderCursor = currentFolder;

        for (String part : parts) {
            if (part.isEmpty() || ".".equals(part)) {
                continue;
            }
            if ("..".equals(part)) {
                if (folderCursor.getParent() != null) {
                    folderCursor = folderCursor.getParent();
                } else {
                    return "Unknown folder: " + path;
                }
                continue;
            }

            FolderInode existing = null;
            for (Inode child : folderCursor.getChildren()) {
                if (child instanceof FolderInode folder && child.getName().equals(part)) {
                    existing = folder;
                    break;
                }
            }

            if (existing != null) {
                folderCursor = existing;
            } else {
                FolderInode newFolder = new FolderInode(part);
                folderCursor.addInode(newFolder);
                folderCursor = newFolder;
            }
        }
        return "";
    }

    private String doCommand(TouchCommand cmd) {
        String name = cmd.getName();
        if (name == null || name.isEmpty()) {
            return "File name can't be empty";
        }
        FileInode file = new FileInode(name);
        currentFolder.addInode(file);
        return "";
    }

    private String doCommand(ErrorCommand cmd) {
        return "Error: " + cmd.getMessage();
    }
}