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
			System.out.println(prop.getProperty("category"));
			System.out.println(prop.getProperty("item"));
			System.out.println(prop.getProperty("exceptional"));
			System.out.println(prop.getProperty("general"));

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
		System.out.println("WRITING");
		System.out.println(content);
		System.out.println();
		System.out.println(file.exists());
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file);
			writer.print("dupa");
			writer.print(content);
			writer.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
