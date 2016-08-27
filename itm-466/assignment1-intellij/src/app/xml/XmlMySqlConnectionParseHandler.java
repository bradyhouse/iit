package app.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import app.mysql.MySqlConnection;

/**
 * Implementation of the SAX DefaultHandler
 * interface which is used to parse
 * the config.xml file for MySql Connection
 * details.
 *
 * @author Brady Houseknecht
 */
public class XmlMySqlConnectionParseHandler extends XmlConfigParseHandlerBaseClass {

    private MySqlConnection m_cls_connection = null;

    private ElementType m_enum_element;

    /**
     * MySql Connection getter
     *
     * @return MySqlConnection class instance
     */
    public MySqlConnection getConnection() {
        return this.m_cls_connection;
    }

    /** {@inheritDoc} */
    @Override
    public void startDocument() throws SAXException {
        this.m_cls_connection = new MySqlConnection();
        this.m_enum_element = ElementType.UNKNOWN;
    }

    /** {@inheritDoc} */
    @Override
    public void endDocument() throws SAXException {
        System.out.println(this.m_cls_connection.toString());
        System.out.println("testing connectivity...");
        if (this.m_cls_connection.test()) {
            System.out.println("can connect!");
        } else {
            System.out.println("can't connect! Houston, we have a problem.");
        }
    }

    /** {@inheritDoc} */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (this.isMyElement(qName, attributes)) {
            try {
                this.m_enum_element = ElementType.valueOf(qName.toUpperCase().trim());
            } catch (Exception err) {
                // Do Nothing ...
                this.m_enum_element = ElementType.UNKNOWN;
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (this.isMyElement(qName)) {
            this.m_enum_element = ElementType.UNKNOWN;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (this.isMyElement()) {
            switch (this.m_enum_element) {
                case SERVER: {
                    this.m_cls_connection.setServer(new String(ch, start, length));
                    break;
                }
                case PORT: {
                    this.m_cls_connection.setPortNumber(new String(ch, start, length));
                    break;
                }
                case ENTITYNAME: {
                    break;
                }
                case ENTITYTYPE: {
                    String type = new String(ch, start, length);
                    if (!type.isEmpty()) {
                        this.m_cls_connection.setEntityType(type);
                    }
                    break;
                }
                case DATABASE: {
                    this.m_cls_connection.setDatabase(new String(ch, start, length));
                    break;
                }
                case USERNAME: {
                    this.m_cls_connection.setUsername(new String(ch, start, length));
                    break;
                }
                case PASSWORD: {
                    this.m_cls_connection.setPassword(new String(ch, start, length));
                    break;
                }
            }
        }
    }
}
