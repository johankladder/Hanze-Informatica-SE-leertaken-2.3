package madeexercises.classifier.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import madeexercises.classifier.classifier.ClassifierModel;

import java.io.IOException;

/**
 * Created by kevin on 19-2-2016.
 */
public class GUILauncher extends Application{

    BorderPane borderPane;
    ClassifierModel model;
    QuestionModel  questionModel = new QuestionModel();

    @Override
    public void start(Stage primaryStage) throws Exception {
        borderPane = new BorderPane();
        Scene scene =  new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setWidth(100);
        primaryStage.setHeight(100);
        primaryStage.show();
        initModels();
    }

    public void initModels() throws InterruptedException {
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
        questionModel.setRoot(model.getRoot());

        QuestionView view = new QuestionView(questionModel);
        borderPane.setCenter(view);

        questionModel.addView(view);

        questionModel.updateViews();
    }
}
