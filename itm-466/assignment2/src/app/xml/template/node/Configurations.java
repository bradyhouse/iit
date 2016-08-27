/**
 * 
 */
package app.xml.template.node;

import java.util.List;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Class used to virtualize a collection of Configuration nodes in the
 * Config.xml file.
 * 
 */
public class Configurations {

	private List<Configuration> m_lst_configurations = null;

	/**
	 * Class constructor
	 * 
	 * @param configurations
	 *            collection of app.xml.template.configuration
	 */
	public Configurations(List<Configuration> configurations) {
		this.m_lst_configurations = configurations;
	}

	/**
	 * @return the configurationNodes
	 */
	public List<Configuration> getConfigurations() {
		return this.m_lst_configurations;
	}

	/**
	 * @param configurationNodes
	 *            the configurationNodes to set
	 */
	public void setConfigurations(List<Configuration> configurations) {
		this.m_lst_configurations = configurations;
	}

	/**
	 * Method used to generate a "Configurations" XML Element based on the
	 * configurations collection.
	 * 
	 * @param org
	 *            .w3c.dom.Document
	 * @return org.w3c.dom.Element
	 */
	public Element toElement(Document document) {
		Element _configurations = document.createElement("Configurations");
		for (Configuration _configuration : this.m_lst_configurations) {
			Comment _configurationNodeComment = _configuration
					.getXmlComment(document);
			_configurations.appendChild(_configurationNodeComment);
			Element _configurationNode = _configuration.getXmlElement(document);
			_configurations.appendChild(_configurationNode);
		}
		return _configurations;
	}

}
