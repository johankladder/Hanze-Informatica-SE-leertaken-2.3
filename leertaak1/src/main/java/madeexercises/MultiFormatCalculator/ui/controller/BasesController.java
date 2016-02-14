package madeexercises.MultiFormatCalculator.ui.controller;

import javafx.scene.control.ComboBox;
import madeexercises.MultiFormatCalculator.ui.model.CalculatorModel;

/**
 * Created by johankladder on 12-2-16.
 */
public class BasesController extends ComboBox {

    private CalculatorModel model;

    public BasesController(CalculatorModel model) {
        this.model = model;
        init();
    }

    private void init() {
        setMaxWidth(Double.MAX_VALUE);
        model.getBases().forEach((baseString, base)-> {
            getItems().add(baseString);
            getSelectionModel().select(baseString);
        });

        setOnAction(event -> {
            model.setBase((String) getValue());
        });
    }

}
