package app.xml;

import java.io.File;
import java.io.IOException;
import app.mysql.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.SAXException;
import java.util.List;

/**
 * Class used to virtualize the parsing of an Xml file.
 * 
 */
public class XmlConfigParser {

	private String m_str_xsdFile, m_str_xmlFile;

	static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
			W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema",
			JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

	/**
	 * Enumeration defining the types of database that are supported and can be
	 * returned by an instance of the parser class.
	 */
	public enum DatabaseType {
		SQLSERVER, MYSQL
	}

	/**
	 * Enumeration defining the type of the component targeted by the Source
	 * File Class instance.
	 */
	public enum ComponentType {
		TABLE, DATABASE
	}

	/**
	 * Default class constructor.
	 */
	public XmlConfigParser() {
		this.m_str_xsdFile = "";
		this.m_str_xmlFile = "";
	}

	/**
	 * Overloaded constructor that can be used to set the target filename when
	 * the class is constructed.
	 * 
	 * @param filename
	 *            string value equal to the name of the target xml file.
	 */
	public XmlConfigParser(String xml, String xsd) {
		this.m_str_xmlFile = xml;
		this.m_str_xsdFile = xsd;
	}

	/**
	 * @return the xsd file
	 */
	public String getXsdFile() {
		return m_str_xsdFile;
	}

	/**
	 * Xsd file setter
	 * 
	 * @param file
	 *            path of the target xsd file
	 */
	public void setXsdFile(String file) {
		this.m_str_xsdFile = file;
	}

	/**
	 * @return the xml file
	 */
	public String getXmlFile() {
		return m_str_xmlFile;
	}

	/**
	 * Xml File setter
	 * 
	 * @param file
	 *            path of the target xml file
	 */
	public void setXmlFile(String file) {
		this.m_str_xmlFile = file;
	}

	/**
	 * Control function which acts as the entry point for the parsing process.
	 * 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void parse() throws ParserConfigurationException, SAXException,
			IOException {
		if (this.isXmlValid()) {
			List<String> _configs = this.parseConfigurationIds();
			for (String _config : _configs) {
				MySqlConnection _connection = this
						.parseMySqlConnection(_config);
				switch (_connection.getEntityType()) {
				case DATABASE: {
					MySqlDatabase _database = this.parseMySqlDatabaseConfig(
							_connection, _config);
					_database.create();
					break;
				}
				case TABLE: {
					MySqlTable _table = this.parseMySqlTableConfig(_connection,
							_config);
					_table.create();
					break;
				}
				}
			}

		}
	}

	/**
	 * Private value function used to parse the XML configuration file and
	 * return a MySql connection object.
	 */
	private MySqlConnection parseMySqlConnection(String configId)
			throws ParserConfigurationException, IOException, SAXException {
		SAXParserFactory _saxParserFactory = SAXParserFactory.newInstance();
		SAXParser _saxParser = _saxParserFactory.newSAXParser();
		XmlMySqlConnectionParseHandler _handler = new XmlMySqlConnectionParseHandler();
		_handler.setId(configId);
		_saxParser.parse(new File(this.getXmlFile()), _handler);
		return _handler.getConnection();
	}

	/**
	 * Private control function used to parse the XML configuration file for
	 * MySql Table specific tags.
	 * 
	 * @param xmlElement
	 *            xml element object
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	private MySqlDatabase parseMySqlDatabaseConfig(MySqlConnection connection,
			String configId) throws ParserConfigurationException, IOException,
			SAXException {
		SAXParserFactory _saxParserFactory = SAXParserFactory.newInstance();
		SAXParser _saxParser = _saxParserFactory.newSAXParser();
		XmlMySqlDatabaseParseHandler _handler = new XmlMySqlDatabaseParseHandler();
		_handler.setId(configId);
		_handler.setMySqlConnection(connection);
		_saxParser.parse(new File(this.getXmlFile()), _handler);
		return _handler.getMySqlDatabase();
	}

	/**
	 * Private control function used to parse the XML configuration file for
	 * MySql Table specific tags.
	 * 
	 * @param xmlElement
	 *            xml element object
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	private MySqlTable parseMySqlTableConfig(MySqlConnection connection,
			String configId) throws ParserConfigurationException, IOException,
			SAXException {
		SAXParserFactory _saxParserFactory = SAXParserFactory.newInstance();
		SAXParser _saxParser = _saxParserFactory.newSAXParser();
		XmlMySqlTableParseHandler _handler = new XmlMySqlTableParseHandler();
		_handler.setId(configId);
		_handler.setMySqlConnection(connection);
		_saxParser.parse(new File(this.getXmlFile()), _handler);
		return _handler.getMySqlTable();
	}

	/**
	 * Private control function used to parse the XML configuration file for all
	 * configuration Ids.
	 * 
	 * @return ArrayList of id strings
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	private List<String> parseConfigurationIds()
			throws ParserConfigurationException, IOException, SAXException {
		SAXParserFactory _saxParserFactory = SAXParserFactory.newInstance();
		SAXParser _saxParser = _saxParserFactory.newSAXParser();
		XmlConfigurationHandler _handler = new XmlConfigurationHandler();
		_saxParser.parse(new File(this.getXmlFile()), _handler);
		return _handler.getIds();
	}

	/**
	 * Method used to verify that the target xml can be parsed based on the
	 * target xsd file.
	 * 
	 * @return boolean
	 * @throws javax.xml.parsers.ParserConfigurationException
	 */
	public boolean isXmlValid() throws ParserConfigurationException {
		if (!this.m_str_xmlFile.isEmpty() && !this.m_str_xsdFile.isEmpty()) {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			docBuilderFactory.setNamespaceAware(true);
			docBuilderFactory.setValidating(true);
			try {
				docBuilderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE,
						W3C_XML_SCHEMA);
				if (this.m_str_xsdFile != null) {
					docBuilderFactory.setAttribute(JAXP_SCHEMA_SOURCE,
							new File(this.m_str_xsdFile));
					DocumentBuilder docBuilder = docBuilderFactory
							.newDocumentBuilder();
					if (this.m_str_xmlFile != null) {
						Document _xmldoc = docBuilder.parse(new File(
								this.m_str_xmlFile));
						if (_xmldoc.toString().length() > 0) {
							return true;
						}
					}
				}
			} catch (IllegalArgumentException x) {
				System.err
						.println("Error: JAXP DocumentBuilderFactory attribute "
								+ "not recognized: " + JAXP_SCHEMA_LANGUAGE);
				System.err
						.println("Check to see if parser conforms to JAXP spec.");
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
