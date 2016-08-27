package app.xsd;

import app.mysql.MySqlColumn;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Class used to virtualize a single column of a MySql Table column.
 * 
 */
public class XsdMySqlColumnParser extends DefaultHandler {

	private Map<Integer, MySqlColumn> m_map_dataColumns = null;
	private MySqlColumn m_cls_column = null;
	private Integer m_int_columnIndex = 0;

	/**
	 * Column list getter
	 * 
	 * @return list of sql server columns
	 */
	public Map<Integer, MySqlColumn> getColumns() {
		return this.m_map_dataColumns;
	}

	private String parseType(String value) {
		String[] _valuePieces = value.split(":");
		return _valuePieces.length > 1 ? _valuePieces[1] : value;
	}

	/** {@inheritDoc} */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("xs:element")) {
			String _xsdTypeValue = attributes.getValue("type");
			String _xsdNameValue = attributes.getValue("name");
			if (_xsdTypeValue != null && _xsdNameValue != null) {
				String _parsedTypeValue = this.parseType(attributes
						.getValue("type"));
				this.m_cls_column = new MySqlColumn(_xsdNameValue,
						_parsedTypeValue, (m_int_columnIndex + 1));
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (this.m_map_dataColumns == null) {
			this.m_map_dataColumns = new HashMap<Integer, MySqlColumn>();
		}
		if (qName.equalsIgnoreCase("xs:element") && this.m_cls_column != null) {
			this.m_map_dataColumns.put(this.m_int_columnIndex,
					this.m_cls_column);
			this.m_int_columnIndex++;
		}
	}
}
