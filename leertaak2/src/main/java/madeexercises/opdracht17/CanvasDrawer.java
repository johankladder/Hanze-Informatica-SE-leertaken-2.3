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

        Node current = node;
        double middle = width / 2;
        double y = 20;
        Map<Node, Location> locationMap = new HashMap<>();

        Stack<Node> nodeStack = new Stack<>();

        while (current != null) {

            GraphicsContext gc = canvas.getGraphicsContext2D();

            Node lNode = (Node) current.getChild().get("1");
            Node rNode = (Node) current.getChild().get("0");

            if (lNode != null) {
                if(!current.isSecondPop()) {
                    drawText(current, middle, y, gc);
                    locationMap.put(current, new Location(middle, y));
                    current = lNode;
                    nodeStack.push(current);
                } else {
                    Location location = locationMap.get(current);
                    drawText(rNode, location.getX() + 50, location.getY() + 50, gc);
                    System.out.println(rNode.getLabel() + " " + rNode.isLeaf());
                    current = null;
                }
            } else {
                // Draw one right node:

                if(rNode != null) {
                    current = rNode;
                } else {
                    // Check if leaf:
                    drawText(current, middle, y, gc);
                    current = nodeStack.pop();
                    current.setSecondPop();
                }

            }
            y = y + 50;

            middle = middle / 2;
        }
    }

    private void drawText(Node node, double x, double y, GraphicsContext gc) {
        gc.setFont(new Font(8));
        if (node.isLeaf()) {
            gc.setStroke(Color.RED);
        } else {
            gc.setStroke(Color.BLACK);
        }
        gc.strokeText(node.getLabel(), x, y);
    }


}
