package com.esiea.pootd2.models;

public abstract class Inode {

    private final String name;
    protected FolderInode parent;

    public Inode(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Inode name can't be empty");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public FolderInode getParent() {
        return parent;
    }

    public abstract int getSize();
}