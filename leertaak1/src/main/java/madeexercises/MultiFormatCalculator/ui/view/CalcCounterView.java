package madeexercises.MultiFormatCalculator.ui.view;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import madeexercises.MultiFormatCalculator.ui.model.CalculatorModel;

/**
 * Created by johankladder on 14-2-16.
 */
public class CalcCounterView extends Label implements ModelView {

    private static final String PREFIX = "Calculations: ";

    private CalculatorModel model;


    public CalcCounterView(CalculatorModel model) {
        this.model = model;
        init();
    }

    private void init() {
        setText(PREFIX + model.getCalcCounter());
    }

    @Override
    public void updateView() {
        setText(PREFIX + model.getCalcCounter());
    }
}
