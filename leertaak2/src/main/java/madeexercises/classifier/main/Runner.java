package madeexercises.classifier.main;

import madeexercises.classifier.classifier.ClassifierModel;
import madeexercises.opdracht17.TreeView;

import java.io.IOException;

/**
 * Created by kevin on 17-2-2016.
 */
public class Runner {
    private static final long serialVersionUID = 1L;
    private ClassifierModel model;
    private TreeView treeView;

    public Runner() throws IOException {
        super();

        //Initialize model
        this.model = new ClassifierModel();
        this.treeView = new TreeView(model);
    }
}
