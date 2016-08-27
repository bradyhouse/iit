package app;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import app.parameters.Arguments;
import app.xml.XmlConfigParser;


public class Program {
    private static Arguments m_map_parameters;

    /**
     * Main Program thread.
     *
     * @param args String array of command line parameters
     */
    public static void main(String[] args) {
        /// Process command line parameters
        Program.m_map_parameters = new Arguments(args);
        /// Parse the input XML file
        XmlConfigParser _xmlParser = new XmlConfigParser(Program.m_map_parameters.getParameters().get("xml"),
                Program.m_map_parameters.getParameters().get("xsd"));
        try {
            _xmlParser.parse();
        } catch (ParserConfigurationException _e) {
            _e.printStackTrace();
        } catch (SAXException _e) {
            _e.printStackTrace();
        } catch (IOException _e) {
            _e.printStackTrace();
        }

    }
}
