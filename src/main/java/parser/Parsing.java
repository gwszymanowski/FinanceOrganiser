package parser;

public interface Parsing {
	void parseToJSON(String fileDirectory);
	void parseToXML(String fileDirectory);
	void parseFromJSON();
	void parseFromXML();
}
