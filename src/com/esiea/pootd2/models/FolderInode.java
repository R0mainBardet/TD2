package com.esiea.pootd2.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FolderInode extends Inode {

    private final List<Inode> children = new ArrayList<>();

    public FolderInode(String name) {
        super(name);
    }

    public void addInode(Inode child) {
        if (child == null) {
            throw new IllegalArgumentException("Child inode can't be null");
        }
        child.parent = this;
        children.add(child);
    }

    public List<Inode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (Inode child : children) {
            totalSize += child.getSize();
        }
        return totalSize;
    }
}