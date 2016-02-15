package madeexercises.MultiFormatCalculator.ui.view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import madeexercises.MultiFormatCalculator.multiformat.Format;
import madeexercises.MultiFormatCalculator.multiformat.Rational;
import madeexercises.MultiFormatCalculator.ui.model.CalculatorModel;


public class OperandView extends BorderPane implements ModelView {

    private CalculatorModel model;
    private GridPane viewPane;

    public OperandView(CalculatorModel model) {
        this.model = model;
        init();
    }

    private void init() {
        setMaxWidth(Double.MAX_VALUE);
    }

    @Override
    public void updateView() {
        viewPane = new GridPane();
        viewPane.setHgap(10);
        viewPane.setVgap(5);

        Rational op1 = model.getCalc().firstOperandRational();
        Rational op2 = model.getCalc().secondOperandRational();

        Format format = model.getCalc().getFormat();

        if (op1 != null && op2 != null) {
            Label op1Label = new Label("OP 1: " + model.getCalc().firstOperand());
            op1Label.setStyle("-fx-font-weight: bold;");
            Label op2Label = new Label("OP 2: " + model.getCalc().secondOperand());
            op2Label.setStyle("-fx-font-weight: bold;");
            viewPane.add(op1Label, 0, 1);
            viewPane.add(op2Label, 0, 2);


            final int[] lastY = {0};
            final int[] lastX = {1};
            model.getBases().forEach((strinFormat, foundBase) -> {
                lastY[0]++;
                Label baseLabel = new Label(foundBase.getName());
                baseLabel.setStyle("-fx-font-weight: bold;");
                viewPane.add(baseLabel, lastX[0], 0);
                viewPane.add(new Label(format.toString(op1, foundBase)), lastX[0], 1);
                viewPane.add(new Label(format.toString(op2, foundBase)), lastX[0], 2);
                lastX[0]++;
            });

        }
        setCenter(viewPane);

    }
}
