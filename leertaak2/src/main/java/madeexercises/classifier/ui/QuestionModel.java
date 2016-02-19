package madeexercises.classifier.ui;

import madeexercises.classifier.classifier.Node;

/**
 * Created by kevin on 19-2-2016.
 */
public class QuestionModel {
    Node root;
    Node currentNode;

    public QuestionModel() {
    }

    public void setRoot(Node root){
        this.root = root;
    }

    public void getAnswer(Node node, Boolean answer) {
        currentNode = node;
    }

    public Node getCurrentNode() {
        return currentNode;
    }
}
