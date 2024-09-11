package com.controller;

import com.models.piecesfield.Field;
import com.models.piecesfield.PiecesFieldModel;
import com.services.serializators.Deserializer;
import com.services.serializators.FileDeserializerImpl;
import com.services.serializators.FileSerializerImpl;
import com.services.serializators.Serializer;

import java.io.File;
import java.io.IOException;

public class SerializationController {
    public static void serializeField() {
        File file = new File("/Users/biggujo/Desktop/field.bin");
        Serializer serializer = new FileSerializerImpl(file);
        try {
            serializer.save(PiecesFieldModel.getInstance().getInstance());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void deserializeField() {
        File file = new File("/Users/biggujo/Desktop/field.bin");
        Deserializer deserializer = new FileDeserializerImpl(file);
        try {
            PiecesFieldModel piecesFieldModel = (PiecesFieldModel) deserializer.load();
            PiecesFieldModel.getInstance().setInstance(piecesFieldModel);
            PiecesFieldController.updatePiecesPanel();
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
