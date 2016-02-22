package madeexercises.opdracht17;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import madeexercises.classifier.classifier.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by johankladder on 19-2-16.
 */
public class CanvasDrawer {

    private Canvas canvas;

    public CanvasDrawer(Canvas canvas) {
        this.canvas = canvas;
    }

    public void draw(TreeView view) {
        view.paint();
    }



}
