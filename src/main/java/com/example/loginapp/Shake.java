package com.example.loginapp;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.w3c.dom.Text;

public class Shake {
    private final TranslateTransition tt;

    public Shake(Node node) {
        tt = new TranslateTransition(Duration.millis(70), node);
        tt.setFromX(0f);
        tt.setByX(10f);
        tt.setCycleCount(3);
        tt.setAutoReverse(true);

    }

    public void playAnim() {
        tt.playFromStart();
    }
}
