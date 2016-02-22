package madeexercises.opdracht18.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import madeexercises.opdracht18.classifier.Node;

/**
 * Created by kevin haitsma on 19-2-16.
 */
public class QuestionView extends BorderPane implements View {

    private QuestionModel model;
    private QuestionController controller;

    public QuestionView(QuestionModel model) {
        this.model = model;
        this.controller = new QuestionController(model);
        model.addView(controller);
    }

    public void updateView() {
        Node currentNode = model.getCurrentNode();

        GridPane pane = new GridPane();
        if(!currentNode.isLeaf()) {
            Label nodeLabel;
            if (currentNode.getLabel().equals("Color")) {
                nodeLabel = new Label("Kies de  " + currentNode.getLabel() + " van uw auto.");
            } else {
                nodeLabel = new Label("Heeft uw auto " + currentNode.getLabel() + "?");
            }
            pane.add(nodeLabel, 0, 0);
            pane.add(controller, 1, 0);
            controller.setNode(currentNode);
        } else {
            // No controller;
            Label nodeLabel = new Label("Uw auto valt in de categorie " + currentNode.getLabel());
            pane.add(nodeLabel, 0, 0);
        }
        pane.setHgap(10);
        setCenter(pane);
    }



}
