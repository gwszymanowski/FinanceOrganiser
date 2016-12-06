package parser;

public class ParserFactory {

	public Parsing getParser(String parserName) {

		if (parserName.equalsIgnoreCase("CATEGORY"))
			return new CategoryParser();
		else if (parserName.equalsIgnoreCase("ITEM"))
			return new ItemParser();
		else if (parserName.equalsIgnoreCase("GENERAL"))
			return new GeneralParser();
		else if (parserName.equalsIgnoreCase("EXCEPTIONAL"))
			return new ExceptionalParser();

		return null;
	}

}
