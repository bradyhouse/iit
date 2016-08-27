/**
 * 
 */
package product;

import region.State;

/**
 * Class used to encapsulate product data.
 * 
 * @author Brady House
 */
public class Product {
	
	private String m_str_productId;
	private String m_str_description;
	private String m_str_productName;
	private double m_dbl_weight;
	private double m_dbl_cost;
	private State m_obj_state;
	
	/**
	 * Default class Constructor.
	 */
	public Product()
	{
		this.m_dbl_cost = 0.0;
		this.m_dbl_weight = 0.0;
		this.m_str_description = "";
		this.m_str_productId = "";
		this.m_str_productName = "";
		
	} // end:Constructor
	
	/**
	 * Overloaded Class constructor.
	 * 
	 * @param m_str_productId
	 * @param m_str_description
	 * @param m_str_productName
	 * @param m_dbl_weight
	 * @param m_dbl_cost
	 */
	public Product(String m_str_productId, String m_str_description,
			String m_str_productName, double m_dbl_weight, double m_dbl_cost) {
		super();
		this.m_str_productId = m_str_productId;
		this.m_str_description = m_str_description;
		this.m_str_productName = m_str_productName;
		this.m_dbl_weight = m_dbl_weight;
		this.m_dbl_cost = m_dbl_cost;

	} // end:overload
	
	/**
	 * @return the m_obj_state
	 */
	public synchronized State getState() {
		return m_obj_state;
	} // end:getter

	/**
	 * @param m_obj_state the m_obj_state to set
	 */
	public synchronized void setState(State m_obj_state) {
		this.m_obj_state = m_obj_state;
	} // end:setter

	/**
	 * @return the m_str_productId
	 */
	public synchronized String getProductId() {
		return m_str_productId;
	} // end:getter

	/**
	 * @param m_str_productId the m_str_productId to set
	 */
	public synchronized void setProductId(String m_str_productId) {
		this.m_str_productId = m_str_productId;
	} // end:getter

	/**
	 * @return the m_str_description
	 */
	public synchronized String getDescription() {
		return m_str_description;
	} // end:getter

	/**
	 * @param m_str_description the m_str_description to set
	 */
	public synchronized void setDescription(String m_str_description) {
		this.m_str_description = m_str_description;
	} // end:setter

	/**
	 * @return the m_str_productName
	 */
	public synchronized String getProductName() {
		return m_str_productName;
	} // end:getter

	/**
	 * @param m_str_productName the m_str_productName to set
	 */
	public synchronized void setProductName(String m_str_productName) {
		this.m_str_productName = m_str_productName;
	} // end:setter

	/**
	 * @return the m_dbl_weight
	 */
	public synchronized  double getWeight() {
		return m_dbl_weight;
	} // end:getter

	/**
	 * @param m_dbl_weight the m_dbl_weight to set
	 */
	public synchronized void setWeight(double m_dbl_weight) {
		this.m_dbl_weight = m_dbl_weight;
	} // end:setter

	/**
	 * @return the m_dbl_cost
	 */
	public synchronized double getCost() {
		return m_dbl_cost;
	} // end:getter

	/**
	 * @param m_dbl_cost the m_dbl_cost to set
	 */
	public synchronized void setCost(double m_dbl_cost) {
		this.m_dbl_cost = m_dbl_cost;
	} // end:setter
	
	/**
	 * Returns the attributes of the Product
	 * in Comma delimited format.
	 */
	@Override
	public String toString()
	{
		return this.getProductId() + ", " +
				this.getProductName() + ", " +
				this.getDescription() + ", " +
				Double.toString(this.getWeight()) + ", " +
				Double.toString(this.getCost());
		
	} // end:toString


} // end:class
