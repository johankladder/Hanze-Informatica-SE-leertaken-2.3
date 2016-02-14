package madeexercises.MultiFormatCalculator.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import madeexercises.MultiFormatCalculator.ui.controller.BasesController;
import madeexercises.MultiFormatCalculator.ui.controller.FormatController;
import madeexercises.MultiFormatCalculator.ui.controller.OperatorController;
import madeexercises.MultiFormatCalculator.ui.controller.TextInputController;
import madeexercises.MultiFormatCalculator.ui.model.CalculatorModel;
import madeexercises.MultiFormatCalculator.ui.view.CalcCounterView;
import madeexercises.MultiFormatCalculator.ui.view.OutputView;

/**
 * Created by johankladder on 12-2-16.
 */
public class JavaFXGui extends Application {

    private Label baseLabel = new Label("Select your base");
    private Label formatLabel = new Label("Select your format");
    //private Label operatorLabel = new Label("Select your operator");


    @Override
    public void start(Stage primaryStage) throws Exception {
        CalculatorModel calcModel = new CalculatorModel();

        // Views:
        OutputView outputView = new OutputView(calcModel);
        CalcCounterView counterView = new CalcCounterView(calcModel);
        calcModel.addView(outputView);
        calcModel.addView(counterView);

        // Controllers:
        BasesController baseController = new BasesController(calcModel);
        FormatController formatController = new FormatController(calcModel);
        OperatorController operatorController = new OperatorController(calcModel);
        TextInputController op1 = new TextInputController(calcModel);

        StackPane root = new StackPane();
        GridPane pane = new GridPane();
        pane.setVgap(5);
        pane.setHgap(5);
        pane.add(baseLabel, 0, 0);
        pane.add(formatLabel, 0, 1);
        pane.add(baseController, 1, 0);
        pane.add(formatController, 1, 1);
        pane.add(op1, 2, 0,1,2);
        pane.add(op1.getController(), 3, 0,1,1);
        pane.add(operatorController, 3, 1,1,1);
        pane.add(outputView, 0, 2, 3, 1);
        pane.add( counterView, 3, 2);


        RowConstraints r1 = new RowConstraints();
        RowConstraints r2 = new RowConstraints();
        RowConstraints r3 = new RowConstraints();
        r1.setPercentHeight(25);
        r2.setPercentHeight(25);
        r3.setPercentHeight(50);
        pane.getRowConstraints().addAll(r1,r2, r3);

        ColumnConstraints c1 = new ColumnConstraints();
        ColumnConstraints c2 = new ColumnConstraints();
        ColumnConstraints c3 = new ColumnConstraints();
        ColumnConstraints c4 = new ColumnConstraints();
        c1.setPercentWidth(30);
        c2.setPercentWidth(20);
        c3.setPercentWidth(25);
        c4.setPercentWidth(25);
        pane.getColumnConstraints().addAll(c1,c2,c3);



        root.getChildren().add(pane);
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(50);
        primaryStage.show();

        calcModel.updateViews();

    }


}
