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

import model.Item;
import model.Items;
import model.SheetRows;
import service.ItemService;

public class ItemParser implements Parsing {

	private ItemService service;

	public ItemParser() {
		this.service = new ItemService();
	}

	@Override
	public void parseToJSON(String fileDirectory) {
		ObjectMapper mapper = new ObjectMapper();

		List<Item> list = this.service.getAll();

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

		Items items = new Items();
		items.setItems(this.service.getAll());

		try {

			File file = new File(sb.toString());
			JAXBContext jaxbContext = JAXBContext.newInstance(SheetRows.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(items, file);
			jaxbMarshaller.marshal(items, System.out);

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
		sb.append("\\item");

		return sb;
	}

	@Override
	public String toString() {
		return "ItemParser";
	}

}
