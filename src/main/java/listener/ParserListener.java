package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import gui.ExportFileChooser;
import parser.Parsing;

public class ParserListener implements ActionListener {

	private Parsing parser;
	
	public ParserListener(Parsing parser) {
		this.parser = parser;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		ExportFileChooser chooser = new ExportFileChooser();

		int result = chooser.showOpenDialog(null);
		
		if (result == JFileChooser.APPROVE_OPTION)		
			parser.parseTo(chooser.getCurrentDirectory().getAbsolutePath());
		
	
		
	}

}
