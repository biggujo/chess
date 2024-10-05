package com.view.components;

import com.github.weisj.jsvg.SVGDocument;
import com.helpers.ApplicationIcons;
import com.helpers.IconResolver;
import com.services.guilisteners.LoadFieldMouseListener;

import java.awt.event.MouseListener;

public class LoadButton extends IconButton {
    public LoadButton() {
        super(resolveIcon(), getListener());
        setToolTipText("Load the game from a file");
    }

    private static SVGDocument resolveIcon() {
        return IconResolver.resolve(ApplicationIcons.LOAD.getName());
    }

    private static MouseListener getListener() {
        return new LoadFieldMouseListener();
    }
}
