package config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertiesManager {

	public Properties readExportProperties() {
		Properties prop = new Properties();

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream input = null;

		try {

			input = classloader.getResourceAsStream("export.properties");
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return prop;
	}

	public Map<String, String> getPropertiesMap() {
		Map<String, String> propertiesMap = new HashMap<String, String>();

		Properties props = this.readExportProperties();

		propertiesMap.put("category", props.getProperty("category"));
		propertiesMap.put("item", props.getProperty("item"));
		propertiesMap.put("exceptional", props.getProperty("exceptional"));
		propertiesMap.put("general", props.getProperty("general"));

		return propertiesMap;
	}

	public List<String> getPropertiesValues() {

		Map<String, String> valuesMap = this.getPropertiesMap();

		List<String> trueValues = valuesMap.entrySet().stream().filter(x -> "true".equals(x.getValue()))
				.map(x -> x.getKey()).collect(Collectors.toList());

		return trueValues;
	}

	public void writeExportProperties(String content) {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("export.properties").getFile());

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file);
			writer.print(content);
			writer.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}

}
