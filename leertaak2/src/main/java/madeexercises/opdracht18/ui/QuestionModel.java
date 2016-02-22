package madeexercises.opdracht18.ui;

import madeexercises.opdracht18.classifier.Node;

import java.util.ArrayList;

/**
 * Created by kevin on 19-2-2016.
 */
public class QuestionModel {

    private Node root;
    private Node currentNode;
    private ArrayList<View> views = new ArrayList<View>();

    public QuestionModel() {
    }

    public void addView(View view) {
        views.add(view);
    }

    public void setRoot(Node root){
        this.root = root;
        currentNode = root;
    }

    public void setAnswer(Node node, Boolean answer, String label) {
        if(answer) {
            if (label.equals("Ja")) {
                currentNode = (Node) node.getChild().get("1");
            } else if (label.equals("Nee ")) {
                currentNode = (Node) node.getChild().get("0");
            } else {
                currentNode = (Node) node.getChild().get(label);
            }
        }
        updateViews();
    }

    public void reset() {
        currentNode = root;
        updateViews();
    }

    // getters
    public Node getCurrentNode() {
        return currentNode;
    }

    public void updateViews() {
        for(View v : views) {
            v.updateView();
        }
    }
}
