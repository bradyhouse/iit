package app.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.List;
import java.util.ArrayList;

/**
 * Implementation of the SAX DefaultHandler interface which is used to parse the
 * config.xml file for configuration node instances. For each instance, the
 * handler will add the element's id attribute to its configuration id array.
 * 
 */
public class XmlConfigurationHandler extends XmlConfigParseHandlerBaseClass {

	private List<String> m_arr_ids = null;

	/**
	 * Ids array accessor.
	 * 
	 * @return string value array
	 */
	public List<String> getIds() {
		return m_arr_ids;
	}

	/** {@inheritDoc} */
	@Override
	public void startDocument() throws SAXException {
		this.m_arr_ids = new ArrayList<String>();
	}

	/** {@inheritDoc} */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("configuration")) {
			String _id = attributes.getValue("id");
			if (!this.m_arr_ids.contains(_id)) {
				this.m_arr_ids.add(_id);
			}
		}
	}
}
