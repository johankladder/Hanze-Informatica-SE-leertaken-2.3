package edited;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by johankladder on 9-2-16.
 */
public class StatsView extends JPanel implements ActionListener {

    private DobbelsteenModel model;
    private GridLayout layout = new GridLayout(0, 2);
    private Map<Integer, JLabel> fields = new LinkedHashMap<>();

    public StatsView(DobbelsteenModel model) {
        this.model = model;
        init();
    }

    private void init() {
        setLayout(layout);
        Map<Integer, Integer> map = model.getGooiStats();

        // Fill the map with start values
        map.forEach((k, v) -> fields.put(k, new JLabel(v + " keer")));

        // Populate view:
        fields.forEach((k, v) -> {
            add(new Label(k + " :"));
            add(v);
        });

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        model.getGooiStats().forEach((k,v) -> {
            fields.get(k).setText(v + " keer");
        });
    }
}
