package madeexercises.MultiFormatCalculator.ui.controller;

import javafx.scene.control.Button;
import madeexercises.MultiFormatCalculator.ui.model.CalculatorModel;


/**
 * Created by johankladder on 12-2-16.
 */
public class TextInputController extends javafx.scene.control.TextField {

    private CalculatorModel model;
    private Button addOpperandController = new Button("Add opperand");

    public TextInputController(CalculatorModel model) {
        this.model = model;
        init();
    }

    private void init() {
        addOpperandController.setMaxWidth(Double.MAX_VALUE);
        addOpperandController.setMaxHeight(Double.MAX_VALUE);

        setMaxWidth(Double.MAX_VALUE);
        setMaxHeight(Double.MAX_VALUE);

        addOpperandController.setOnAction(event -> model.setOperand(getText()));

        // For placing cursor in center of the field:
        setStyle("-fx-alignment: center");
    }

    public Button getController() {
        return addOpperandController;
    }
}
