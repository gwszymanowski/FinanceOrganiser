package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.swing.JFileChooser;

import config.PropertiesManager;
import gui.ExportFileChooser;
import parser.ParserFactory;
import parser.Parsing;

public class CompositeParserListener implements ActionListener {

	private Map<String, Parsing> parsers;
	private boolean isXML;

	public CompositeParserListener(boolean isXML) {
		this.parsers = new TreeMap<String, Parsing>();
		this.isXML = isXML;
		ParserFactory fc = new ParserFactory();
		parsers.put("category", fc.getParser("CATEGORY"));
		parsers.put("item", fc.getParser("ITEM"));
		parsers.put("exceptional", fc.getParser("EXCEPTIONAL"));
		parsers.put("general", fc.getParser("GENERAL"));
	}

	private List<Parsing> receiveParsers() {

		PropertiesManager mg = new PropertiesManager();
		List<String> values = mg.getPropertiesValues();

		List<Parsing> parserList = parsers.entrySet().stream().filter(x -> values.contains(x.getKey()) == true)
				.map(x -> x.getValue()).collect(Collectors.toList());

		return parserList;
	}

	public void actionPerformed(ActionEvent e) {
		
		ExportFileChooser chooser = new ExportFileChooser();

		int result = chooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {

			String fileDirectory = chooser.getSelectedFile().getAbsolutePath();
			
			for (Parsing p : this.receiveParsers())
				new ParserListener(p, fileDirectory, isXML).actionPerformed(e);
			
		}

		

	}

}
