package com.services.serializators;

import java.io.*;

public class FileSerializerImpl implements Serializer {
    private final File file;

    public FileSerializerImpl(File file) {
        this.file = file;
    }

    @Override
    public void save(Serializable object) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        objectOutputStream.close();
    }
}
