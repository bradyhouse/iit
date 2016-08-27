package app.xml;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Base class that defines the attributes common to Config file parsers. All
 * class that extend from this base class should provide implementation for the
 * SAX DefaultHandler Interface.
 * 
 */
public abstract class XmlConfigParseHandlerBaseClass extends DefaultHandler {

	private String m_str_id = "0";
	private Boolean m_boo_parse = false;

	/**
	 * Id attribute accessor.
	 * 
	 * @return integer value
	 */
	public String getId() {
		return this.m_str_id;
	}

	/**
	 * Id attribute setter.
	 * 
	 * @param id
	 *            integer value
	 */
	public void setId(String id) {
		this.m_str_id = id;
	}

	protected boolean isMyElement(String qName, Attributes attributes) {
		if (qName.equalsIgnoreCase("configuration")
				&& attributes.getValue("id").equalsIgnoreCase(this.getId())) {
			this.m_boo_parse = true;
		}
		return this.m_boo_parse;
	}

	protected boolean isMyElement(String qName) {
		if (qName.equalsIgnoreCase("configuration") && this.m_boo_parse) {
			this.m_boo_parse = false;
		}
		return this.m_boo_parse;
	}

	/**
	 * Getter for property 'myElement'.
	 * 
	 * @return Value for property 'myElement'.
	 */
	protected boolean isMyElement() {
		return this.m_boo_parse;
	}

	/**
	 * Enumeration defining the Elements comprising the config.xml file.
	 */
	public enum ElementType {
		CONFIGURATION, DATABASETYPE, SERVER, DATABASE, PORT, USERNAME, PASSWORD, ENTITYTYPE, INPUTXMLFILE, INPUTXSDFILE, ENTITYNAME, UNKNOWN
	}

	/**
	 * Enumeration defining template types supported by the application.
	 */
	public enum TemplateType {
		DATABASE, TABLE
	}

}
