package parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import service.CrudServiceI;

@SuppressWarnings("rawtypes")
public class GeneralParser implements Parsing {

	private CrudServiceI service;
	private String filename;

	public GeneralParser(CrudServiceI service, String filename) {
		this.service = service;
		this.filename = filename;
	}

	@Override
	public void parseToJSON(String fileDirectory) {
		ObjectMapper mapper = new ObjectMapper();

		List<?> list = service.getAll();
		
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
		// TODO Auto-generated method stub

	}
	
	private StringBuilder getFilePath(String fileDirectory) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(fileDirectory);
		sb.append(filename);
		
		return sb;
	}

}
