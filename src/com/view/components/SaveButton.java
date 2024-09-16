package com.view.components;

import com.github.weisj.jsvg.SVGDocument;
import com.helpers.ApplicationIcons;
import com.helpers.IconResolver;
import com.services.listeners.LoadFieldMouseListener;
import com.services.listeners.SaveFieldMouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class SaveButton extends IconButton {
    public SaveButton() {
        super(resolveIcon(), getListener());
    }

    private static SVGDocument resolveIcon() {
        return IconResolver.resolve(ApplicationIcons.SAVE.getName());
    }

    private static MouseListener getListener() {
        return new SaveFieldMouseListener();
    }
}
