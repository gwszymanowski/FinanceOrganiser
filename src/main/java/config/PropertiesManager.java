package config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

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

	public void writeExportProperties(String content) {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("export.properties").getFile());

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file);
			writer.print("");
			writer.print(content);
			writer.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
