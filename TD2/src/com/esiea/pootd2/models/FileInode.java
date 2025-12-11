package com.esiea.pootd2.models;

import java.util.concurrent.ThreadLocalRandom;

public class FileInode extends Inode {

    private final int fileSize;

    public FileInode(String name) {
        super(name);
        this.fileSize = ThreadLocalRandom.current().nextInt(1, 100_001);
    }

    @Override
    public int getSize() {
        return fileSize;
    }
}