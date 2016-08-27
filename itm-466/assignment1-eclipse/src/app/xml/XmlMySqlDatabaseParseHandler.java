package app.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import app.mysql.MySqlDatabase;
import app.mysql.MySqlConnection;


/**
 * Implementation of the SAX DefaultHandler
 * interface which is used to parse
 * the config.xml file for MySql Database
 * creation details.
 *
 * @author Brady Houseknecht
 */
public class XmlMySqlDatabaseParseHandler extends XmlConfigParseHandlerBaseClass {

    private MySqlDatabase m_cls_database = null;
    private MySqlConnection m_cls_connection = null;
    private Boolean m_boo_parseName = false;

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
     * @param connection mysql connection class instance
     */
    public void setMySqlConnection(MySqlConnection connection) {
        this.m_cls_connection = connection;
    }

    /**
     * MySql Database getter
     *
     * @return MySql Database class instance or null
     */
    public MySqlDatabase getMySqlDatabase() {
        return this.m_cls_database;
    }

    /** {@inheritDoc} */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (this.isMyElement(qName, attributes) && qName.equalsIgnoreCase("EntityName")) {
            this.m_boo_parseName = true;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (this.isMyElement() && this.m_boo_parseName && this.m_cls_connection != null) {
            this.m_cls_database = new MySqlDatabase();
            this.m_cls_database.setMySqlConnection(m_cls_connection);
            this.m_cls_database.setName(new String(ch, start, length));
        }
    }

    /** {@inheritDoc} */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (this.isMyElement(qName) && qName.equalsIgnoreCase("EntityName")) {
            this.m_boo_parseName = false;
        }
    }
}
