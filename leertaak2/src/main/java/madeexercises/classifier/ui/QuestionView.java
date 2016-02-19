package madeexercises.classifier.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import madeexercises.classifier.classifier.Node;

/**
 * Created by johankladder on 19-2-16.
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
        Label nodeLabel = new Label(currentNode.getLabel());
        pane.add(nodeLabel, 0, 0);
        if(!currentNode.isLeaf()) {
            pane.add(controller, 1, 0);
            controller.setNode(currentNode);
        } else {
           // No controller;
        }
        pane.setHgap(10);
        setCenter(pane);
    }



}
