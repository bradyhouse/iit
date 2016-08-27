/**
 * 
 */
package app.xml.template.node;

import java.util.List;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Class used to virtualize a single Configuration Node of the Config.xml file.
 * 
 */
public class Configuration {

	private String m_str_id = null;
	private String m_str_comment = null;
	private List<ConfigurationProperty> m_lst_properties = null;

	/**
	 * Class constructor.
	 * 
	 * @param id
	 * @param comment
	 * @param properties
	 */
	public Configuration(String id, String comment,
			List<ConfigurationProperty> properties) {
		this.m_str_id = id;
		this.m_str_comment = comment;
		this.m_lst_properties = properties;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.m_str_id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.m_str_id = id;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return this.m_str_comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.m_str_comment = comment;
	}

	/**
	 * @return the properties
	 */
	public List<ConfigurationProperty> getProperties() {
		return this.m_lst_properties;
	}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(List<ConfigurationProperty> properties) {
		this.m_lst_properties = properties;
	}

	/**
	 * Method used to generate a Configuration XML Element based on the instance
	 * of the class.
	 * 
	 * @param org
	 *            .w3c.dom.Document
	 * @return org.w3c.dom.Element
	 */
	public Element getXmlElement(Document document) {
		Element _configuration = document.createElement("Configuration");
		_configuration.setAttribute("id", this.m_str_id);
		for (ConfigurationProperty _property : this.m_lst_properties) {
			Comment _propertyNodeComment = _property.getXmlComment(document);
			_configuration.appendChild(_propertyNodeComment);
			Element _propertyNode = _property.getXmlElement(document);
			_configuration.appendChild(_propertyNode);
		}
		return _configuration;
	}

	/**
	 * Method used to generate an XML Comment based on the comment property of
	 * the class instance.
	 * 
	 * @param org
	 *            .w3c.dom.Document
	 * @return org.w3c.dom.Comment
	 */
	public Comment getXmlComment(Document document) {
		Comment _comment = document.createComment(this.getComment());
		return _comment;
	}

}
