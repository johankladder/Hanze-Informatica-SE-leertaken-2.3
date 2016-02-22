package madeexercises.opdracht17;

import java.util.Map;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import madeexercises.classifier.classifier.ClassifierModel;
import madeexercises.classifier.classifier.Node;

public class TreeView extends Canvas {


    private static final long serialVersionUID = 1L;

    private static final int Y_OFFSET = 50;

    private ClassifierModel model;

    public TreeView(ClassifierModel model){
        this.model = model;
        this.setHeight(300);
        this.setWidth(5000);
    }

    public void paint(){
        Node root = model.getRoot();

        GraphicsContext gc = this.getGraphicsContext2D();

        drawNode(root, this.getWidth()/2, TreeView.Y_OFFSET, this.getWidth()/4, 20, gc, "1");

    }

    public void drawNode(Node n, double x, int y, double xOffset, int yOffset, GraphicsContext gc, String direction){
        drawText(n, x, y, gc);
        Map<String,Node> children = n.getChild();

        double xPos = 0;
        double yPos = 0;

        for (String newDirection : children.keySet()) {
            Node childNode = children.get(direction);

            if (newDirection.equals("1")) {
                xPos = x - xOffset;
                yPos = y + yOffset;
            } else if (newDirection.equals("0")) {
                xPos = x + xOffset;
                yPos = y + yOffset;
            } else {
                throw new IllegalArgumentException("are you insane?");
            }

            gc.setStroke(Color.GRAY);
            gc.moveTo(x-1, y-1);
            gc.lineTo(xPos -1, yPos-1);
            gc.stroke();

            drawNode(childNode, xPos, (int) yPos, xOffset / 2, yOffset, gc, newDirection);
        }
    }

    private void drawText(Node node, double x, double y, GraphicsContext gc) {
        gc.setFont(new javafx.scene.text.Font(8));
        if (node.isLeaf()) {
            gc.setStroke(javafx.scene.paint.Color.RED);
        } else {
            gc.setStroke(Color.BLUE);
        }

        gc.strokeText(node.getLabel(), x, y);
    }




}