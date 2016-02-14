package madeexercises.MultiFormatCalculator.ui.controller;

import javafx.scene.control.ComboBox;
import madeexercises.MultiFormatCalculator.ui.model.CalculatorModel;

public class FormatController extends ComboBox {

    private CalculatorModel model;

    public FormatController(CalculatorModel model) {
        this.model = model;
        init();

    }

    private void init() {
        // Add empty entry:
        getItems().add("");

        setMaxWidth(Double.MAX_VALUE);

        model.getFormats().forEach((formatString, format) -> getItems().add(formatString));

        getSelectionModel().select(model.getCalc().getFormat().getName());

        setOnAction(event -> model.setFormat((String) getValue()));
    }

}

