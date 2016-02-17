package madeexercises.classifier.src.classifier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by kevin on 17-2-2016.
 */
public class ClassifierModel{

    private TextFileReader reader;

    private ArrayList<ActionListener> actionListeners;
    private DecisionTree tree;

    public ClassifierModel()throws IOException {
        this.reader = new TextFileReader();
        this.tree = new DecisionTree(this.reader.getTrainingsSet(), this.reader.getFeaturesMap());
    }

    /**
     * Get root from the DecisionTree
     *
     * @return the root
     */
    public Node getRoot() {
        //return tree;
        return this.tree.getRoot();
    }




}
