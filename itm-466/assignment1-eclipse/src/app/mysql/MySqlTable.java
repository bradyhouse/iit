package app.mysql;

import java.sql.*;
import java.io.File;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Map;
import app.xsd.XsdMySqlColumnParser;


/**
 * Class used to virtualize a MySql Server
 * Table.
 *
 * @author Brady Houseknecht
 */
public class MySqlTable extends MySqlEntityBaseClass {

    private Map<Integer, MySqlColumn> m_map_dataColumns;

    /**
     * Default Class constructor
     */
    public MySqlTable() {
        super();
    }

    /**
     * @return the dataColumns
     */
    public Map<Integer, MySqlColumn> getDataColumns() {
        return this.m_map_dataColumns;
    }

    /**
     * Method used to verify that the target xml can be parsed
     * based on the target xsd file.
     *
     * @return boolean
     * @throws ParserConfigurationException
     */
    public boolean isXmlValid() throws ParserConfigurationException {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setNamespaceAware(true);
        docBuilderFactory.setValidating(true);
        try {
            docBuilderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
            if (this.getXsdFile() != null) {
                docBuilderFactory.setAttribute(JAXP_SCHEMA_SOURCE, new File(this.getXsdFile()));
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                if (this.getXmlFile() != null) {
                    Document _xmldoc = docBuilder.parse(new File(this.getXmlFile()));
                    if (_xmldoc.toString().length() > 0) {
                        return this.isXsdValid();
                    }
                }
            }
        } catch (IllegalArgumentException x) {
            System.err.println("Error: JAXP DocumentBuilderFactory attribute "
                    + "not recognized: " + JAXP_SCHEMA_LANGUAGE);
            System.err.println("Check to see if parser conforms to JAXP spec.");
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method used to verify that the target xsd can be parsed.
     *
     * @return boolean
     */
    public boolean isXsdValid() {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setNamespaceAware(true);
        docBuilderFactory.setValidating(true);
        try {
            docBuilderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
            if (this.getXsdFile() != null) {
                return this.areColumnsValid();
            }
        } catch (IllegalArgumentException x) {
            System.err.println("Error: JAXP DocumentBuilderFactory attribute "
                    + "not recognized: " + JAXP_SCHEMA_LANGUAGE);
            System.err.println("Check to see if parser conforms to JAXP spec.");
        }
        return false;
    }

    /**
     * Method used to verify that the target xsd can be used to populate the
     * columns map.
     * <p/>
     * NOTE - This method (as a by product) assigns the columns map property.
     *
     * @return
     */
    private boolean areColumnsValid() {
        SAXParserFactory _saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser _saxParser = _saxParserFactory.newSAXParser();

            XsdMySqlColumnParser _handler = new XsdMySqlColumnParser();
            _saxParser.parse(new File(this.getXsdFile()), _handler);
            this.m_map_dataColumns = _handler.getColumns();
            for (MySqlColumn _col : this.m_map_dataColumns.values()) {
                System.out.println(_col);
            }
            return true;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Private getter used to generate an SQL Create Table
     * statement from the table's name and columns
     * collection.
     */
    private String getSqlCreateStatement() {
        String _sqlStatement = "CREATE TABLE `" + this.getMySqlConnection().getDatabase() + "`.`" +
                this.getName() + "` (\n";
        Integer _lastColumn = this.getDataColumns().size() - 1,
                _currentColumn = 0;

        for (MySqlColumn _col : this.getDataColumns().values()) {
            _sqlStatement += _col.toSql() + (_currentColumn.equals(_lastColumn) ? "\n" : ",\n");
            _currentColumn++;
        }
        _sqlStatement += ");";

        return _sqlStatement;

    }

    /**
     * Private method used to create the table on
     * the database. It returns true if (a) the
     * connection object exists, (b) the isXmlValid
     * method returns true and (c) the create statement
     * sql executes successfully.  Otherwise, it returns
     * false.
     *
     * @return boolean value
     * @throws SQLException
     * @throws ParserConfigurationException
     */
    private boolean createDatabaseTable() throws SQLException, ParserConfigurationException {
        if (this.getMySqlConnection() != null) {
            if (this.isXmlValid()) {
                System.out.println("attempting to create " + this.getName() + " table ...");
                Statement _stmt = this.getMySqlConnection().getConnection().createStatement();
                String _sql = this.getSqlCreateStatement();
                System.out.println(_sql);
                _stmt.executeUpdate(_sql);
                return true;
            }
        }
        return false;
    }

    /**
     * Control method used to initiate the
     * table creation process on the
     * target database.
     */
    public boolean create() {
        if (this.getMySqlConnection() != null) {
            try {
                return this.createDatabaseTable();
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(1);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
                System.exit(2);
            }
        }
        return false;
    }

    /**
     * Override of the class to string in order
     * to return the Table's SQL Create Statement.
     */
    @Override
    public String toString() {
        return this.getSqlCreateStatement();
    }

}
