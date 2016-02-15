package madeexercises.MultiFormatCalculator.ui.controller;

import javafx.scene.control.ComboBox;
import madeexercises.MultiFormatCalculator.ui.model.CalculatorModel;

public class OperatorController extends ComboBox {

    private CalculatorModel model;

    public OperatorController(CalculatorModel model) {
        this.model = model;
        init();
    }

    private void init() {
        // Add empty entry:
        getItems().add("");

        setMaxWidth(Double.MAX_VALUE);
        model.getOperators().forEach((operatorString, method) -> {
            getItems().add(operatorString);
        });

        getSelectionModel().select("");

        setOnAction(event -> {
            model.setOperator((String) getValue());
        });
    }
}
