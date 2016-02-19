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
    }

    public void init() {

    }

    public void updateView() {
        Node currentNode = model.getCurrentNode();
        Label nodeLabel = new Label(currentNode.getLabel());

        GridPane pane = new GridPane();
        pane.add(nodeLabel, 0, 0);
        pane.add(controller, 1, 0);
    }



}
