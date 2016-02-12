package madeexercises.MultiFormatCalculator.ui.controller;

import madeexercises.MultiFormatCalculator.ui.model.CalculatorModel;



/**
 * Created by johankladder on 12-2-16.
 */
public class TextInputController extends javafx.scene.control.TextField {

    private CalculatorModel model;

    public TextInputController(CalculatorModel model) {
        this.model = model;
        init();
    }

    private void init() {
        setMaxWidth(Double.MAX_VALUE);
        setMaxHeight(Double.MAX_VALUE);
    }
}
