package app.xml.template;

import java.util.ArrayList;
import java.util.List;
import app.xml.template.node.*;

/**
 * Class used to virtualize the Config.xml template file.
 * 
 */
public class ConfigFileTemplate {

	private Configurations m_cls_root = null;

	/**
	 * Class Constructor
	 */
	public ConfigFileTemplate() {

		List<Configuration> _configurationTemplates = new ArrayList<Configuration>();

		Configuration _databaseConfiguration = this
				.buildDatabaseConfiguration(), _tableConfiguration = this
				.buildTableConfiguration();

		_configurationTemplates.add(_databaseConfiguration);
		_configurationTemplates.add(_tableConfiguration);

		this.m_cls_root = new Configurations(_configurationTemplates);

	}

	/**
	 * Method used to generate the configuration table template.
	 * 
	 * @return app.xml.template.node.Configuration
	 */
	private Configuration buildTableConfiguration() {

		List<ConfigurationProperty> _properties = this.buildCommonProperties();

		// Schema
		ConfigurationProperty _database = new ConfigurationProperty(
				"Database",
				"The name of the database schema where the object will reside or the object creations statements should be executed from.",
				"assignment2");
		_properties.add(_database);

		// Entity Type
		ConfigurationProperty _entityType = new ConfigurationProperty(
				"EntityType",
				"The type of database object that the configuration defines.  Valid values include: "
						+ "\"database\" or \"table\"", "table");
		_properties.add(_entityType);

		// Entity Name
		ConfigurationProperty _entityName = new ConfigurationProperty(
				"EntityName",
				"The name of the entity object that is to be created.", "users");
		_properties.add(_entityName);

		// Input Xml File
		ConfigurationProperty _inputXmlFile = new ConfigurationProperty(
				"InputXmlFile",
				"File path and name of the XML file defining the data that should be loaded into the target table. "
						+ "Note - Currently this setting is not actually used. In other words it will be implemented in subsequent iteration.",
				"users.xml");
		_properties.add(_inputXmlFile);

		// Input Xsd File
		ConfigurationProperty _inputXsdFile = new ConfigurationProperty(
				"InputXsdFile",
				"File path and name of the XSD file defining the schema of the new table. ",
				"users.xsd");
		_properties.add(_inputXsdFile);

		return new Configuration("2",
				"Configuration template for creating and loading a table.",
				_properties);
	}

	/**
	 * Method used to generate the configuration database template.
	 * 
	 * @return app.xml.template.node.Configuration
	 */
	private List<ConfigurationProperty> buildCommonProperties() {

		List<ConfigurationProperty> _properties = new ArrayList<ConfigurationProperty>();

		// Database Type
		ConfigurationProperty _databaseType = new ConfigurationProperty(
				"DatabaseType",
				"The type of the database connection. Valid values include: \"MySql\", \"SqlServer\", \"sqlite\" or \"Oracle\".  NOTE - Currently only MySql is supported.",
				"MySql");
		_properties.add(_databaseType);

		// Server
		ConfigurationProperty _server = new ConfigurationProperty(
				"Server",
				"The database server domain or ip address. For example, \"127.0.0.1\" or \"localhost\".",
				"127.0.0.1");
		_properties.add(_server);

		// Port
		ConfigurationProperty _port = new ConfigurationProperty(
				"Port",
				"The database server port number.  Note - for MySql, 3306 is the default (suggested).",
				"3306");
		_properties.add(_port);

		// Username
		ConfigurationProperty _username = new ConfigurationProperty(
				"Username",
				"The user name that should be used to connect to the database server.",
				"root");
		_properties.add(_username);

		// Password
		ConfigurationProperty _password = new ConfigurationProperty(
				"Password",
				"The password that should be used to access the database server",
				"root");
		_properties.add(_password);
		return _properties;

	}

	/**
	 * Method used to define the Database Configuration template.
	 * 
	 * @return app.xml.template.node.Configuration
	 */
	private Configuration buildDatabaseConfiguration() {
		List<ConfigurationProperty> _properties = this.buildCommonProperties();

		// Schema
		ConfigurationProperty _database = new ConfigurationProperty(
				"Database",
				"The name of the database schema where the object will reside or the object creations statements should be executed from.",
				"mysql");
		_properties.add(_database);

		// Entity Type
		ConfigurationProperty _entityType = new ConfigurationProperty(
				"EntityType",
				"The type of database object that the configuration defines.  Valid values include: "
						+ "\"database\" or \"table\"", "database");
		_properties.add(_entityType);

		// Entity Name
		ConfigurationProperty _entityName = new ConfigurationProperty(
				"EntityName",
				"The name of the entity object that is to be created.",
				"assignment2");
		_properties.add(_entityName);

		return new Configuration("1",
				"Configuration template for creating a database.", _properties);
	}

	/**
	 * @return the root
	 */
	public Configurations getRoot() {
		return this.m_cls_root;
	}

	/**
	 * @param root
	 *            the root to set
	 */
	public void setRoot(Configurations root) {
		this.m_cls_root = root;
	}

}
