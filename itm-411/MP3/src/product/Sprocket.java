package product;



/**
 * Class used to encapsulate the attributes of a
 * Spacely Sprockets' product.
 * 
 * @author brady house
 *
 */
public class Sprocket extends Product {

	/**
	 * Class constructor.
	 */
	public Sprocket() {

	} // end:constructor

	/**
	 * Overloaded constructor.
	 * 
	 * @param m_str_productId
	 * @param m_str_description
	 * @param m_str_productName
	 * @param m_dbl_weight
	 * @param m_dbl_cost
	 */
	public Sprocket(String m_str_productId, String m_str_description,
			String m_str_productName, double m_dbl_weight, double m_dbl_cost) {
		super(m_str_productId, m_str_description, m_str_productName,
				m_dbl_weight, m_dbl_cost);
		// TODO Auto-generated constructor stub
	} // end:constructor

} // end:constructor
