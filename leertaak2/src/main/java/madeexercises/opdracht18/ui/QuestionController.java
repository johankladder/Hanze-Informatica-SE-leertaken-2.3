package madeexercises.opdracht18.ui;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import madeexercises.classifier.classifier.Node;

/**
 * Created by johankladder on 19-2-16.
 */
public class QuestionController extends BorderPane implements View{

    private QuestionModel model;
    private Label trueLabel = new Label("Ja");
    private Label falseLabel = new Label("Nee");

    private CheckBox trueCheckBox = new CheckBox();
    private CheckBox falseCheckBox = new CheckBox();

    private Node node;

    public QuestionController(QuestionModel model) {
        this.model = model;
        init();
    }

    private void init() {
        GridPane gridPane = new GridPane();
        gridPane.add(trueLabel, 0, 0);
        gridPane.add(falseLabel, 1, 0);

        gridPane.add(trueCheckBox, 0, 1);
        gridPane.add(falseCheckBox, 1, 1);
        setCenter(gridPane);

        CheckBox[] boxes = {trueCheckBox, falseCheckBox};
        for(int i = 0; i < boxes.length; i++) {
            CheckBox box = boxes[i];
            box.setOnAction(event -> {
                model.setAnswer(node, getAnswer(box));
            });
        }
    }

    public void setNode(Node node) {
        this.node = node;
    }

    private boolean getAnswer(CheckBox box) {
        if(box == trueCheckBox) {
            return true;
        } else {
            return false;
        }
    }


    public void updateView() {
        trueCheckBox.setSelected(false);
        falseCheckBox.setSelected(false);
    }
}
