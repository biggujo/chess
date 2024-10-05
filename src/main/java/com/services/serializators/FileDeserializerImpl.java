package com.services.serializators;

import java.io.*;

public class FileDeserializerImpl implements Deserializer {
    private final File file;

    public FileDeserializerImpl(File file) {
        this.file = file;
    }

    @Override
    public Object load() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Object object = objectInputStream.readObject();
        objectInputStream.close();

        return object;
    }
}
