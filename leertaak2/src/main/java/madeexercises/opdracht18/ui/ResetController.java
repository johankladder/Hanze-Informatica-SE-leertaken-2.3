package madeexercises.opdracht18.ui;

import javafx.scene.control.Button;

/**
 * Created by johankladder on 19-2-16.
 */
public class ResetController extends Button {

    private QuestionModel model;

    public ResetController(QuestionModel model) {
        this.model = model;
        init();
    }

    private void init() {
        setOnAction(event-> {
            model.reset();
        });
    }
}
