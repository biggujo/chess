package com.view.components;

import com.github.weisj.jsvg.SVGDocument;
import com.helpers.ApplicationIcons;
import com.helpers.IconResolver;
import com.services.guilisteners.SaveFieldMouseListener;

import java.awt.event.MouseListener;

public class SaveButton extends IconButton {
    public SaveButton() {
        super(resolveIcon(), getListener());
        setToolTipText("Load the game into a file");
    }

    private static SVGDocument resolveIcon() {
        return IconResolver.resolve(ApplicationIcons.SAVE.getName());
    }

    private static MouseListener getListener() {
        return new SaveFieldMouseListener();
    }
}
