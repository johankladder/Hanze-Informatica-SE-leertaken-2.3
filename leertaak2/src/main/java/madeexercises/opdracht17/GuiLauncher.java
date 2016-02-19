package madeexercises.opdracht17;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by johankladder on 19-2-16.
 */
public class GuiLauncher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        Canvas canvas = new Canvas();
        pane.setCenter(canvas);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);

        CanvasDrawer drawer = new CanvasDrawer(canvas);
        primaryStage.show();
    }
}
