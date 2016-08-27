package app.mysql;

import app.mysql.MySqlConnection;

/**
 * Base class that defines the attributes common to all MySql Entity objects.
 * 
 */
public abstract class MySqlEntityBaseClass {

	static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
	static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

	private MySqlConnection m_cls_connection;
	private String m_str_name, m_str_xsdFile, m_str_xmlFile;

	/**
	 * Default Class constructor
	 */
	public MySqlEntityBaseClass() {
		this.m_cls_connection = null;
		this.m_str_name = "";
		this.m_str_xsdFile = "";
		this.m_str_xmlFile = "";
	}

	/**
	 * Overloaded constructor which can be used to initialize the attributes of
	 * the class when the class is constructed.
	 * 
	 * @param connection
	 *            mysql connection class instance
	 * @param name
	 *            string value
	 * @param xsdFile
	 *            string xsd file name and path
	 * @param xmlFile
	 *            string xml file name and path
	 */
	public MySqlEntityBaseClass(MySqlConnection connection, String name,
			String xsdFile, String xmlFile) {
		this.m_str_name = name;
		this.m_cls_connection = connection;
		this.m_str_xsdFile = xsdFile;
		this.m_str_xmlFile = xmlFile;
	}

	/**
	 * Overloaded constructor used to initialize the a connection object and a
	 * name attribute.
	 * 
	 * @param connection
	 * @param name
	 */
	public MySqlEntityBaseClass(MySqlConnection connection, String name) {
		this.m_str_name = name;
		this.m_cls_connection = connection;
		this.m_str_xsdFile = "";
		this.m_str_xmlFile = "";
	}

	/**
	 * Name getter
	 * 
	 * @return string name attribute value
	 */
	public String getName() {
		return this.m_str_name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.m_str_name = name;
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
	 * MySql connection getter
	 * 
	 * @return mysql connection class instance
	 */
	public MySqlConnection getMySqlConnection() {
		return this.m_cls_connection;
	}

	/**
	 * Setter used to assign the connection
	 * 
	 * @param connection
	 *            mysql connection class instance
	 */
	public void setMySqlConnection(MySqlConnection connection) {
		this.m_cls_connection = connection;
	}

	/**
	 * Value function that will return a boolean to indicate whether or not the
	 * connection can be established using configured attributes of the class
	 * instance.
	 * 
	 * @return boolean value indicating whether connection could be established.
	 */
	public boolean TestConnection() {
		if (this.m_cls_connection != null) {
			return this.m_cls_connection.test();
		} else {
			return false;
		}
	}

	/**
	 * Override of the class to string in order to return the name of the entity
	 * and the details of the entity's database connection.
	 */
	@Override
	public String toString() {
		return "Entity Name: " + this.m_str_name + "\n" + "Connection String: "
				+ this.m_cls_connection.toString();
	}
}
