package madeexercises.opdracht17;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import madeexercises.classifier.classifier.ClassifierModel;

import java.io.IOException;

/**
 * Created by johankladder on 19-2-16.
 */
public class GuiLauncher extends Application {

    private ClassifierModel model;
    private CanvasDrawer drawer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        Canvas canvas = new Canvas();
        pane.setCenter(canvas);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);

        drawer = new CanvasDrawer(canvas);
        primaryStage.show();
        initt();
    }

    public void initt() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    model = new ClassifierModel();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();

        drawer.draw(model.getRoot());


    }
}
