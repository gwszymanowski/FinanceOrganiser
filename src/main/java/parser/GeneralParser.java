package parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.SheetRow;
import model.SheetRows;
import service.SheetRowService;

public class GeneralParser implements Parsing {

	private SheetRowService service;

	public GeneralParser() {
		this.service = new SheetRowService(false);
	}

	@Override
	public void parseToJSON(String fileDirectory) {
		ObjectMapper mapper = new ObjectMapper();

		List<SheetRow> list = this.service.getAll();
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
		StringBuilder sb = getFilePath(fileDirectory);
		sb.append(".xml");

		SheetRows rows = new SheetRows();
		rows.setSheetrows(this.service.getAll());

		try {

			File file = new File(sb.toString());
			JAXBContext jaxbContext = JAXBContext.newInstance(SheetRows.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(rows, file);
			jaxbMarshaller.marshal(rows, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void parseFromJSON() {
		// TODO Auto-generated method stub

	}

	@Override
	public void parseFromXML() {
		// TODO Auto-generated method stub

	}

	private StringBuilder getFilePath(String fileDirectory) {
		StringBuilder sb = new StringBuilder();

		sb.append(fileDirectory);
		sb.append("\\general");

		return sb;
	}

	@Override
	public String toString() {
		return "GeneralParser";
	}

}
