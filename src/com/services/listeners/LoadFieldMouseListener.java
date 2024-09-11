package com.services.listeners;

import com.controller.SerializationController;
import com.models.piecesfield.Field;
import com.models.piecesfield.PiecesFieldModel;
import com.services.serializators.Deserializer;
import com.services.serializators.FileDeserializerImpl;
import com.services.serializators.FileSerializerImpl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoadFieldMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        SerializationController.deserializeField();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
