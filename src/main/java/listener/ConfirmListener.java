package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import service.CrudServiceI;

@SuppressWarnings("rawtypes")
public class ConfirmListener implements ActionListener {

	private CrudServiceI service;
	private Object o;
	
	public ConfirmListener(CrudServiceI service, Object o) {
		this.service = service;
		this.o = o;	
	}
	
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent arg0) {
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(null, 
				"Are you sure you want to add it?"
        		, "Confirm", dialogButton);

        if(dialogResult == JOptionPane.YES_OPTION)
        	this.service.add(o);
  
        JOptionPane.showMessageDialog(null, "Action finished");
		
	}

}
