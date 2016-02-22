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
    private Stack<Node> nodes = new Stack<>();

    private int counter = 0;

    private HashMap<Node, Location> locationMap = new HashMap<>();

    public CanvasDrawer(Canvas canvas) {
        this.canvas = canvas;
        init();
    }

    private void init() {
        canvas.setHeight(700);
        canvas.setWidth(1000);
    }

    public void draw(Node node) {
        drawNodes(node, canvas.getWidth());
    }


    public void drawNodes(Node node, double width) {

    }

    private Location drawText(Node node, double x, double y, GraphicsContext gc) {
        gc.setFont(new Font(8));
        if (node.isLeaf()) {
            gc.setStroke(Color.RED);
        } else {
            gc.setStroke(Color.BLACK);
        }

        gc.strokeText(node.getLabel(), x, y);
        return new Location(x,y);
    }


}
