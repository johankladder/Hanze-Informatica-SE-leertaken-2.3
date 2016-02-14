package madeexercises.MultiFormatCalculator.ui.controller;

import javafx.scene.control.ComboBox;
import madeexercises.MultiFormatCalculator.ui.model.CalculatorModel;

public class BasesController extends ComboBox {

    private CalculatorModel model;

    public BasesController(CalculatorModel model) {
        this.model = model;
        init();
    }

    private void init() {
        // Add empty entry:
        getItems().add("");

        setMaxWidth(Double.MAX_VALUE);

        model.getBases().forEach((baseString, base) -> getItems().add(baseString));

        getSelectionModel().select(model.getCalc().getBase().getName());

        setOnAction(event -> model.setBase((String) getValue()));
    }

}
