package madeexercises.classifier.ui;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * Created by johankladder on 19-2-16.
 */
public class QuestionController extends BorderPane implements View{

    private QuestionModel model;
    private Label trueLabel = new Label("T");
    private Label falseLabel = new Label("F");

    private CheckBox trueCheckBox = new CheckBox();
    private CheckBox falseCheckBox = new CheckBox();

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
    }

    public void updateView() {
        trueCheckBox.setSelected(false);
        falseCheckBox.setSelected(false);
    }
}
