package madeexercises.classifier.ui;

import com.sun.org.apache.xpath.internal.operations.Bool;
import madeexercises.classifier.classifier.Node;

/**
 * Created by kevin on 19-2-2016.
 */
public class QuestionModel {
    Node root;
    Node currentNode;
    Node nextNode;

    public QuestionModel() {
    }

    public void setRoot(Node root){
        this.root = root;
        setAnswer(root, true);
    }

    public void setAnswer(Node node, Boolean answer) {
        if(answer) {
            currentNode = (Node) node.getChild().get("1");
        } else {
            currentNode = (Node) node.getChild().get("0");
        }
    }

    // getters
    public Node getCurrentNode() {
        return currentNode;
    }
}
