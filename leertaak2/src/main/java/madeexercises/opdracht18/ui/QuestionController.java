package madeexercises.opdracht18.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import madeexercises.opdracht18.classifier.Node;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by kevin haitsma on 19-2-16.
 */
public class QuestionController extends BorderPane implements View {

    private QuestionModel model;
    private Node node;
    private HashMap<CheckBox, String> checkBoxes;

    public QuestionController(QuestionModel model) {
        this.model = model;
        init();
        addListeners();
    }

    private void init() {
        GridPane gridPane = new GridPane();
        checkBoxes = new HashMap<CheckBox, String>();
        CheckBox box;
        int count =0;

        if(node == null) {
            node = model.getCurrentNode();
        }
        Iterator it = node.getChild().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String name = (String)pair.getKey();
            if (name.equals("1")) {
                name = "Ja";
            } else if(name.equals("0")) {
                name = "Nee ";
            }
            Label label = new Label(name);
            gridPane.add(label, count, 0);
            box = new CheckBox();
            gridPane.add(box, count, 1);
            setCenter(gridPane);

            checkBoxes.put(box, name);
            count++;
        }
    }

    private void addListeners() {
        Iterator it = checkBoxes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            CheckBox cb = (CheckBox)pair.getKey();
            cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> ov,
                                    Boolean old_val, Boolean new_val) {
                    model.setAnswer(node, new_val, (String) pair.getValue());
                }
            });
        }
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void updateView() {
        init();
        addListeners();
    }
}
