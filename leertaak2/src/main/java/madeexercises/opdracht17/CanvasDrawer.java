package madeexercises.opdracht17;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import madeexercises.classifier.classifier.Node;

/**
 * Created by johankladder on 19-2-16.
 */
public class CanvasDrawer {

    private Canvas canvas;
    private static double orW;
    private static double orH;

    public CanvasDrawer(Canvas canvas) {
        this.canvas = canvas;
        init();
    }

    private void init() {
        canvas.setHeight(700);
        canvas.setWidth(1000);
        orW = canvas.getWidth();
        orH = canvas.getHeight();
    }

    public void draw(Node node) {
        drawNode(node, canvas.getWidth(), 0);
    }

    public void drawNode(Node node, double width, int lastY) {
        if(node != null) {
            double v = width / 2;
            final GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.strokeOval(v, lastY + 70, 20,20);
            drawNode((Node) node.getChild().get("1"), v, lastY + 70);
        }
    }



}
