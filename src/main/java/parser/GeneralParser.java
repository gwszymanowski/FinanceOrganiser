package parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.SheetRow;
import service.SheetRowService;

public class GeneralParser implements Parsing {

	private SheetRowService service;

	public GeneralParser() {
		this.service = new SheetRowService(false);
	}

	@Override
	public void parseToJSON(String fileDirectory) {
		ObjectMapper mapper = new ObjectMapper();

		List<SheetRow> list = service.getAll();

		StringBuilder sb = getFilePath(fileDirectory);
		sb.append(".json");

		try {
			mapper.writeValue(new File(sb.toString()), list);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void parseToXML(String fileDirectory) {
		System.out.println("GENERAL XML " + fileDirectory);
	}

	private StringBuilder getFilePath(String fileDirectory) {
		StringBuilder sb = new StringBuilder();

		sb.append(fileDirectory);
		sb.append("general");

		return sb;
	}

	@Override
	public String toString() {
		return "GeneralParser";
	}

}
