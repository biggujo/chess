package com.view.frames.promotion;

import com.services.guilisteners.DisposeFrameListener;

import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

class PromotionMouseListeners implements Function<Window, List<MouseListener>> {
    @Override
    public List<MouseListener> apply(Window container) {
        List<MouseListener> listeners = new ArrayList<>();

        listeners.add(new EnableFieldAdapter());
        listeners.add(new DisposeFrameListener(container));

        return listeners;
    }
}
