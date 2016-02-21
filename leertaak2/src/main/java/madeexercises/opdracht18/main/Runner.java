package madeexercises.opdracht18.main;

import madeexercises.classifier.classifier.ClassifierModel;

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
