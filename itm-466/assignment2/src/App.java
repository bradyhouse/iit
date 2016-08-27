
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import app.parameters.Arguments;
import app.xml.XmlConfigParser;
import app.xml.XmlConfigWriter;

public class App {
	private static Arguments m_map_parameters;

	/**
	 * Main Program thread. This is the entry point.
	 * 
	 * @param args
	 *            String array of command line parameters
	 */
	public static void main(String[] args) {
		App.printHeader();
		if (args != null && args.length > 0) {
			// / Process command line parameters
			App.m_map_parameters = new Arguments(args);
			if (App.m_map_parameters.exists("help")) {
				App.help();
			} else {
				// / Parse the input XML file
				try {
					XmlConfigParser _xmlParser = new XmlConfigParser(
							App.m_map_parameters.getParameters().get("xml"),
							App.m_map_parameters.getParameters().get("xsd"));
					_xmlParser.parse();
				} catch (ParserConfigurationException _e) {
					_e.printStackTrace();
				} catch (SAXException _e) {
					_e.printStackTrace();
				} catch (IOException _e) {
					_e.printStackTrace();
				}
			}
		} else {
			App.assume();
		}
	}

	/**
	 * "assume" the current directory contains a "config.xml" and "config.xsd"
	 * file. Verify that these files exist and use them to startup the app.
	 */
	private static void assume() {

		File _xmlFile = new File("config.xml");
		File _xsdFile = new File("config.xsd");

		if (_xmlFile.exists() && !_xmlFile.isDirectory() && _xsdFile.exists()
				&& !_xsdFile.isDirectory()) {
			XmlConfigParser _xmlParser = new XmlConfigParser("config.xml",
					"config.xsd");
			try {
				_xmlParser.parse();
			} catch (ParserConfigurationException _e) {
				_e.printStackTrace();
			} catch (SAXException _e) {
				_e.printStackTrace();
			} catch (IOException _e) {
				_e.printStackTrace();
			}
		} else {
			App.generateConfig();
		}
	}

	/**
	 * Print the application's header (aka title).
	 * 
	 * Note - This "art" was generated using http://ascii.mastervb.net/.
	 */
	private static void printHeader() {
		System.out.println("");
		System.out
				.println("                               #                                                    #       ####");
		System.out
				.println("    #                          #                                                    #      #    #");
		System.out
				.println("    #                                                                               #           #");
		System.out
				.println("   ###     #####    #####    ###      ######  # ####   ### ##    #####   # ####   ######       #");
		System.out
				.println("   # #    #        #           #     #     #  ##    #  #  #  #  #     #  ##    #    #         #");
		System.out
				.println("  #####    ####     ####       #     #     #  #     #  #  #  #  #######  #     #    #        #");
		System.out
				.println("  #   #        #        #      #     #     #  #     #  #  #  #  #        #     #    #       #");
		System.out
				.println(" ##   ##  #####    #####     #####    ######  #     #  #     #   #####   #     #     ###   ######");
		System.out
				.println("                                           #                                                       ");
		System.out
				.println("                                      #####                                                        ");
		System.out.println("");
		System.out.println("Illinois Institute of Technology");
		System.out.println("ITM 466 | Spring 2015 | Group 7");
		System.out.println("Brady Houseknecht / Christopher Hernandez");
	}

	/**
	 * Show usage information. This method is invoked if the jar is started with
	 * the "help" parameter passed.
	 */
	private static void help() {
		System.out.println("ITM 466 | Assignment 1");
		System.out.println("");
		System.out.println("Usage:");
		System.out.println("");
		System.out.println("java jar=\"assignment1.jar\" xml=[x] xsd=[s]");
		System.out.println("");
		System.out.println("[x] - config.xml file");
		System.out.println("[s] - config.xsd file");
		System.out.println("");
	}

	/**
	 * Generate a template version of the config.xml file. This method handles
	 * the scenario where --
	 * 
	 * (1) The application is started with no command line arguments (2) The
	 * startup directory does not contain a config.xml file
	 * 
	 * Note - The config.xsd file should maintained. If the config.xsd file is
	 * deleted, then the application will stop working.
	 */
	private static void generateConfig() {
		System.out.println("");
		System.out.println("Generating config.xml file...");
		XmlConfigWriter _writer = new XmlConfigWriter();
		_writer.write();
		System.out.println("template config.xml file generated.");
	}

}
