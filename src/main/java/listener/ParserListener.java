package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import parser.Parsing;

public class ParserListener implements ActionListener {

	private Parsing parser;
	private boolean isXML;
	private String fileDirectory;

	public ParserListener(Parsing parser, String fileDirectory, boolean isXML) {
		this.fileDirectory = fileDirectory;
		this.parser = parser;
		this.isXML = isXML;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (isXML == true)
			parser.parseToXML(fileDirectory);
		else
			parser.parseToJSON(fileDirectory);

	}

}
