package madeexercises.MultiFormatCalculator.ui.controller;

import javafx.scene.control.ComboBox;
import madeexercises.MultiFormatCalculator.ui.model.CalculatorModel;

/**
 * Created by johankladder on 12-2-16.
 */
public class OperatorController extends ComboBox {

    private CalculatorModel model;

    public OperatorController(CalculatorModel model) {
        this.model = model;
        init();
    }

    private void init() {
        setMaxWidth(Double.MAX_VALUE);
        model.getOperators().forEach((operatorString, method)-> {
            getItems().add(operatorString);
            getSelectionModel().select(operatorString);
        });

        setOnAction(event -> {
            model.setOperator((String) getValue());
        });
    }
}
