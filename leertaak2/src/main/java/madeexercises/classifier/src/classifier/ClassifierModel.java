package madeexercises.classifier.src.classifier;

import java.io.IOException;

/**
 * Created by kevin on 17-2-2016.
 */
public class ClassifierModel{

    private TextFileReader reader;

    private DecisionTree tree;

    public ClassifierModel()throws IOException {
        this.reader = new TextFileReader();
        this.tree = new DecisionTree(reader.getTrainingsSet(), reader.getFeaturesMap());
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
