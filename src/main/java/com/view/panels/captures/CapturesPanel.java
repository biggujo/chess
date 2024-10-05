package com.view.panels.captures;

import javax.swing.*;
import java.util.List;

public interface CapturesPanel {
    void updateCapturesWith(List<JComponent> components);

    void setScore(int score);
}
