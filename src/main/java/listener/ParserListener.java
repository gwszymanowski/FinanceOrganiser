package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import gui.ExportFileChooser;
import parser.Parsing;

public class ParserListener implements ActionListener {

	private Parsing parser;
	private boolean isXML;

	public ParserListener(Parsing parser, boolean isXML) {
		this.parser = parser;
		this.isXML = isXML;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		ExportFileChooser chooser = new ExportFileChooser();

		int result = chooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {

			String fileDirectory = chooser.getSelectedFile().getAbsolutePath();

			if (isXML == true)
				parser.parseToXML(fileDirectory);
			else
				parser.parseToJSON(fileDirectory);
		}

	}

}
