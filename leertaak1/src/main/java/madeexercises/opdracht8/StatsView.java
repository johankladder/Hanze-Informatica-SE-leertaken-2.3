package edited.opdracht8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by johankladder on 9-2-16.
 */
public class StatsView extends JPanel implements ActionListener {

    private DobbelsteenModel model;
    private GridLayout layout = new GridLayout(0, 2);
    private Map<Object, JLabel> fields = new LinkedHashMap<>();

    private JLabel totalView = new JLabel();


    public StatsView(DobbelsteenModel model) {
        this.model = model;
        init();
    }

    private void init() {
        setLayout(layout);

        // Fill the map with start values
        Map<Integer, Integer> map = model.getGooiStats();
        map.forEach((k, v) -> fields.put(k, new JLabel(v + " keer")));
        totalView.setText(String.valueOf(model.getCounter()));

        // Populate view:
        add(totalView);
        add(new JLabel("worp(en)"));

        fields.forEach((k, v) -> {
            add(new Label(k + " :"));
            add(v);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update all the labels to model's values
        model.getGooiStats().forEach((k, v) -> {
            fields.get(k).setText(v + " keer");
        });
        totalView.setText(String.valueOf(model.getCounter()));
    }
}
