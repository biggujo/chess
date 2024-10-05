package com.view.components;

import com.github.weisj.jsvg.SVGDocument;
import com.helpers.ApplicationIcons;
import com.helpers.IconResolver;
import com.services.guilisteners.ResetGameMouseListener;

import java.awt.event.MouseListener;

public class ResetButton extends IconButton {
    public ResetButton() {
        super(resolveIcon(), getListener());
        setToolTipText("Reset the game");
    }

    private static SVGDocument resolveIcon() {
        return IconResolver.resolve(ApplicationIcons.RESET.getName());
    }

    private static MouseListener getListener() {
        return new ResetGameMouseListener();
    }
}
