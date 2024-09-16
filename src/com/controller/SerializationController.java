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
    public static void serializeField(File file) {
        Serializer serializer = new FileSerializerImpl(file);
        try {
            serializer.save(PiecesFieldModel.getInstance());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void deserializeField(File file) {
        Deserializer deserializer = new FileDeserializerImpl(file);
        try {
            PiecesFieldModel piecesFieldModel = (PiecesFieldModel) deserializer.load();
            PiecesFieldModel.setInstance(piecesFieldModel);
            PiecesFieldController.updatePiecesPanel();
            AvailableMovesController.clearAvailableMovesPanel();
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
