package madeexercises.classifier.ui;

import madeexercises.classifier.classifier.Node;

/**
 * Created by kevin on 19-2-2016.
 */
public class QuestionModel {
    Node root;

    public QuestionModel() {
    }

    public void setRoot(Node root){
        this.root = root;
        System.out.println(root);
    }

}
