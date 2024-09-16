package com.view.components;

import com.github.weisj.jsvg.SVGDocument;
import com.helpers.ApplicationIcons;
import com.helpers.IconResolver;
import com.services.listeners.SwapPlayerColorsMouseListener;

import java.awt.event.MouseListener;

public class SwapColorsButton extends IconButton {
    public SwapColorsButton() {
        super(SwapColorsButton.resolveIcon(), SwapColorsButton.getListener());
    }

    private static SVGDocument resolveIcon() {
        return IconResolver.resolve(ApplicationIcons.SWAP.getName());
    }

    private static MouseListener getListener() {
        return new SwapPlayerColorsMouseListener();
    }
}
