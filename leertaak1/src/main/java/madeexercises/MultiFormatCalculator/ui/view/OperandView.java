package madeexercises.MultiFormatCalculator.ui.view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import madeexercises.MultiFormatCalculator.multiformat.Base;
import madeexercises.MultiFormatCalculator.multiformat.Format;
import madeexercises.MultiFormatCalculator.multiformat.Rational;
import madeexercises.MultiFormatCalculator.ui.model.CalculatorModel;

/**
 * Created by johankladder on 14-2-16.
 */
public class OperandView extends BorderPane implements ModelView {

    private CalculatorModel model;

    public OperandView(CalculatorModel model) {
        this.model = model;
        init();
    }

    private void init() {
        setMinHeight(0.0);
        setMaxHeight(80);
        setMaxWidth(Double.MAX_VALUE);
    }

    @Override
    public void updateView() {
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(5);
        Rational op1 = model.getCalc().firstOperandRat();
        Rational op2 = model.getCalc().secondOperandRat();

        Format format = model.getCalc().getFormat();

        if (op1 != null && op2 != null) {
            Label op1Label = new Label(model.getCalc().firstOperand());
            op1Label.setStyle("-fx-font-weight: bold;");
            Label op2Label = new Label(model.getCalc().secondOperand());
            op2Label.setStyle("-fx-font-weight: bold;");
            pane.add(op1Label, 0, 1);
            pane.add(op2Label, 0, 2);


            final int[] lastY = {0};
            final int[] lastX = {1};
            model.getBases().forEach((strinFormat, base) -> {
                lastY[0]++;
                Base foundBase = base;
                Label baseLabel = new Label(foundBase.getName());
                baseLabel.setStyle("-fx-font-weight: bold;");
                pane.add(baseLabel, lastX[0], 0);
                pane.add(new Label(format.toString(op1, foundBase)), lastX[0], 1);
                pane.add(new Label(format.toString(op2, foundBase)), lastX[0], 2);
                lastX[0]++;
            });


        }
        setCenter(pane);

    }
}
