package madeexercises.MultiFormatCalculator.ui.controller;

import javafx.scene.control.ComboBox;
import madeexercises.MultiFormatCalculator.ui.model.CalculatorModel;

/**
 * Created by johankladder on 12-2-16.
 */
public class FormatController extends ComboBox {

    private CalculatorModel model;

    public FormatController(CalculatorModel model) {
        this.model = model;
        init();
    }

    private void init() {
        setMaxWidth(Double.MAX_VALUE);
        model.getFormats().forEach((format)-> {
            getItems().add(format);
            getSelectionModel().select(format);
        });
    }
}
