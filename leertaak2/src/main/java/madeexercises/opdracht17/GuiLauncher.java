package madeexercises.opdracht17;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
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

    private TreeView view;

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane pane = new BorderPane();
        ScrollPane scrollPane = new ScrollPane(pane);

        Scene scene = new Scene(scrollPane);
        primaryStage.setScene(scene);

        primaryStage.setFullScreen(true);
        primaryStage.setMinHeight(200);
        primaryStage.setMinWidth(200);
        primaryStage.show();
        initt(pane);
    }

    public void initt(BorderPane pane) throws InterruptedException {
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

        view  = new TreeView(model);
        drawer = new CanvasDrawer(view);
        drawer.draw(view);
        pane.setCenter(view);


    }
}
