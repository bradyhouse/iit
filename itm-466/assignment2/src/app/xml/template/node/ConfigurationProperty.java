package app.xml.template.node;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Class used to virtualize a sub-node (or property) of a Configuration Node.
 * 
 */
public class ConfigurationProperty {

	private String m_str_type;
	private String m_str_comment;
	private String m_str_value;

	/**
	 * Class Constructor
	 * 
	 * @param type
	 * @param comment
	 * @param value
	 */
	public ConfigurationProperty(String type, String comment, String value) {
		m_str_type = type;
		m_str_comment = comment;
		m_str_value = value;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return this.m_str_type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.m_str_type = type;
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
	 * @return the value
	 */
	public String getValue() {
		return this.m_str_value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.m_str_value = value;
	}

	/**
	 * Method used to generate an XML Element based on the type and value
	 * properties of the class.
	 * 
	 * @param org
	 *            .w3c.dom.Document
	 * @return org.w3c.dom.Element
	 */
	public Element getXmlElement(Document document) {
		Element _element = document.createElement(this.getType());
		_element.setTextContent(this.getValue());
		return _element;
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
