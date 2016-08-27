package app.xml;

import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import app.xml.template.*;

/**
 * Class used to encapsulate the logic necessary to generate a template
 * config.xml.
 * 
 */
public class XmlConfigWriter {

	private DocumentBuilderFactory m_obj_domFactory = null;
	private DocumentBuilder m_obj_domBuilder = null;
	private ConfigFileTemplate m_obj_configFileTemplate = null;

	/**
	 * Default Constructor
	 */
	public XmlConfigWriter() {
		try {
			this.m_obj_domFactory = DocumentBuilderFactory.newInstance();
			this.m_obj_domBuilder = this.m_obj_domFactory.newDocumentBuilder();
			this.m_obj_configFileTemplate = new ConfigFileTemplate();
		} catch (FactoryConfigurationError exp) {
			System.err.println(exp.toString());
		} catch (ParserConfigurationException exp) {
			System.err.println(exp.toString());
		} catch (Exception exp) {
			System.err.println(exp.toString());
		}
	}

	/**
	 * Method used to initiate the file generation process.
	 */
	public void write() {
		this.writeConfigXml();
	}

	/**
	 * Method used to generate a template version of the config
	 */
	private void writeConfigXml() {

		try {
			Document _configXmlDocument = this.m_obj_domBuilder.newDocument();

			// Add Configurations (Root) element
			Element _configurations = this.m_obj_configFileTemplate.getRoot()
					.toElement(_configXmlDocument);

			_configXmlDocument.appendChild(_configurations);

			// Write the file
			TransformerFactory _transformerFactory = TransformerFactory
					.newInstance();
			Transformer _transformer = _transformerFactory.newTransformer();
			_transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			_transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "5");

			Source src = new DOMSource(_configXmlDocument);
			Result dest = new StreamResult(new File("config.xml"));
			_transformer.transform(src, dest);

		} catch (Exception exp) {
			System.err.println(exp.toString());
		}

	}

	/**
	 * @return the Document Builder Factory
	 */
	public DocumentBuilderFactory getDOMFactory() {
		return this.m_obj_domFactory;
	}

	/**
	 * @return the Document Builder
	 */
	public DocumentBuilder getDOMBuilder() {
		return this.m_obj_domBuilder;
	}

}
