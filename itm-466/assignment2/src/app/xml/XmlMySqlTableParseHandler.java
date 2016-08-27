package app.xml;

import app.mysql.MySqlConnection;
import app.mysql.MySqlTable;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Implementation of the SAX DefaultHandler interface which is used to parse the
 * config.xml file for MySql Table creation detail.
 * 
 */
public class XmlMySqlTableParseHandler extends XmlConfigParseHandlerBaseClass {

	private MySqlTable m_cls_table = null;

	private ElementType m_enum_element;

	private MySqlConnection m_cls_connection = null;

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
	 * MySql Table accessor
	 * 
	 * @return MySqlTable class instance or null
	 */
	public MySqlTable getMySqlTable() {
		return this.m_cls_table;
	}

	/** {@inheritDoc} */
	@Override
	public void startDocument() throws SAXException {
		this.m_cls_table = new MySqlTable();
		this.m_cls_table.setMySqlConnection(m_cls_connection);
		this.m_enum_element = ElementType.UNKNOWN;
	}

	/** {@inheritDoc} */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (this.isMyElement(qName, attributes)) {
			try {
				this.m_enum_element = ElementType.valueOf(qName.toUpperCase()
						.trim());
			} catch (Exception err) {
				// Do Nothing ...
				this.m_enum_element = ElementType.UNKNOWN;
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (this.isMyElement(qName)) {
			this.m_enum_element = ElementType.UNKNOWN;
		}
	}

	/** {@inheritDoc} */
	@Override
	public void characters(char ch[], int start, int length)
			throws SAXException {
		if (this.isMyElement()) {
			String value = new String(ch, start, length);
			switch (this.m_enum_element) {
			case ENTITYNAME: {
				this.m_cls_table.setName(value);
				break;
			}
			case INPUTXMLFILE: {
				this.m_cls_table.setXmlFile(value);
				break;
			}
			case INPUTXSDFILE: {
				this.m_cls_table.setXsdFile(value);
				break;
			}
			default:
				break;
			}
		}
	}
}
