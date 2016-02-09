package edited;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class DobbelsteenModel {
    private int waarde;
    private ArrayList<ActionListener> actionListenerList = new ArrayList<ActionListener>();
    private static Map<Integer, Integer> gooiStats = new LinkedHashMap<>();

    // Fill stats with default values
    static {
        gooiStats.put(1, 0);
        gooiStats.put(2, 0);
        gooiStats.put(3, 0);
        gooiStats.put(4, 0);
        gooiStats.put(5, 0);
        gooiStats.put(6, 0);
    }

    public DobbelsteenModel() {
        waarde = (int) (Math.random() * 6 + 1);
    }

    public int getWaarde() {
        return waarde;
    }

    public void verhoog() {
        waarde++;
        if (waarde > 6) waarde = 1;
        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
    }

    public void verlaag() {
        waarde--;
        if (waarde < 1) waarde = 6;
        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
    }

    public void gooi() {
        waarde = (int) (Math.random() * 6 + 1);
        processGooi(waarde);
        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
    }

    public void addActionListener(ActionListener l) {
        actionListenerList.add(l);
    }

    public void removeActionListener(ActionListener l) {
        if (actionListenerList.contains(l))
            actionListenerList.remove(l);
    }

    public Map getGooiStats() {
        return gooiStats;
    }


    private void processEvent(ActionEvent e) {
        for (ActionListener l : actionListenerList)
            l.actionPerformed(e);
    }

    private void processGooi(int waarde) {
        int lastCount = gooiStats.get(waarde);
        lastCount = lastCount + 1; // Increment lastCount
        gooiStats.put(waarde, lastCount);
    }
}
