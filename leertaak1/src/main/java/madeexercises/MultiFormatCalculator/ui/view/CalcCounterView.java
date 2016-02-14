package madeexercises.MultiFormatCalculator.ui.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import madeexercises.MultiFormatCalculator.ui.model.CalculatorModel;

public class CalcCounterView extends GridPane implements ModelView {

    private static final String PREFIX = "Calculations: ";
    private static final Label PREFIX_LABEL = new Label(PREFIX);

    private CalculatorModel model;
    private Label viewLabel = new Label();

    public CalcCounterView(CalculatorModel model) {
        this.model = model;
        init();
    }

    private void init() {
        add(PREFIX_LABEL, 0, 0);
        add(viewLabel, 1, 0);
        setPadding(new Insets(5, 0, 5, 0));
    }

    @Override
    public void updateView() {

        viewLabel.setText(model.getCalcCounter() + "");
    }
}
