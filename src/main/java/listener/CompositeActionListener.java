package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class CompositeActionListener implements ActionListener {

	private Map<Integer, ActionListener> listeners = new TreeMap<Integer, ActionListener>();

	public void actionPerformed(ActionEvent e) {
		TreeSet<Integer> t = new TreeSet<Integer>();
		t.addAll(listeners.keySet());
		Iterator<Integer> it = t.iterator();
		while (it.hasNext()) {
			int x = it.next();
			ActionListener a = listeners.get(x);
			a.actionPerformed(e);
		}

	}

	public void addActionListener(ActionListener a, int priority) {
		if (!listeners.containsKey(priority)) {
			listeners.put(priority, a);
		}
	}

}
