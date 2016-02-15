package madeexercises.MultiFormatCalculator.ui.view;

import javafx.scene.control.TextField;
import madeexercises.MultiFormatCalculator.ui.model.CalculatorModel;


/**
 * Created by johankladder on 14-2-16.
 */
public class OutputView extends TextField implements ModelView {

    private CalculatorModel model;

    public OutputView(CalculatorModel model) {
        this.model = model;
        init();
    }

    private void init() {
        setMaxWidth(Double.MAX_VALUE);
        setEditable(false);
    }



    @Override
    public void updateView() {
        setText(model.getValues());
    }
}
