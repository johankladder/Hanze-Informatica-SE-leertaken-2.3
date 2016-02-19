package madeexercises.classifier.ui;

import madeexercises.classifier.classifier.Node;

import java.util.ArrayList;

/**
 * Created by kevin on 19-2-2016.
 */
public class QuestionModel {
    Node root;
    Node currentNode;
    ArrayList<View> views = new ArrayList<View>();

    public QuestionModel() {
    }

    public void addView(View view) {
        views.add(view);
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

    public void updateViews() {
        for(View v : views) {
            updateViews();
        }
    }
}
