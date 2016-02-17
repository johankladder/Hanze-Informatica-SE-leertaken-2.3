package madeexercises.classifier.src.main;

import madeexercises.classifier.src.classifier.ClassifierModel;

import java.io.IOException;

/**
 * Created by kevin on 17-2-2016.
 */
public class Runner {
    private static final long serialVersionUID = 1L;
    private ClassifierModel model;

    public Runner() throws IOException {
        super();

        //Initialize model
        this.model = new ClassifierModel();
    }
}
