package product;

import java.util.Date;

import region.State;


/**
 * Class used to encapsulates a product object, time stamp
 * and region.
 * 
 * @author Brady House
 */
public class ProductMessage {

	private Date m_dte_timestamp;
	private Product m_obj_product;
	private State.TimeZoneType m_enum_region;
	
	/**
	 * @return m_dte_timestamp
	 */
	public synchronized Date getTimeStamp() {
		return m_dte_timestamp;
	} // end:getter

	/**
	 * @param m_dte_timestamp to set
	 */
	public synchronized void setTimeStamp(Date m_dte_timestamp) {
		this.m_dte_timestamp = m_dte_timestamp;
	}// end:setter
	
	/**
	 * @return the m_obj_product
	 */
	public synchronized Product getProduct() {
		return m_obj_product;
	} //end:getter

	/**
	 * @param m_obj_product the m_obj_product to set
	 */
	public synchronized void setProduct(Product m_obj_product) {
		this.m_obj_product = m_obj_product;
	} //end:setter

	/**
	 * @return the m_cls_region
	 */
	public synchronized State.TimeZoneType getRegion() {
		return m_enum_region;
	} // end:getter

	/**
	 * @param m_cls_region the m_cls_region to set
	 */
	public synchronized void setRegion(State.TimeZoneType m_enum_region) {
		this.m_enum_region = m_enum_region;
	} // end:setter

	/**
	 * Class Constructor
	 */
	public ProductMessage()
	{
		this.m_dte_timestamp = new Date();
		this.m_obj_product = null;
		this.m_enum_region = State.TimeZoneType.NA;
	} // end:constructor
	
	/**
	 * Overloaded constructor.
	 * @param m_dte_timestamp
	 * @param m_obj_product
	 * @param m_cls_region
	 */
 	public ProductMessage(Date m_dte_timestamp, Product m_obj_product,
			State.TimeZoneType m_enum_region) {
		super();
		this.m_dte_timestamp = m_dte_timestamp;
		this.m_obj_product = m_obj_product;
		this.m_enum_region = m_enum_region;
	} // end:overload
	
 	@Override
	public String toString()
	{
		return this.m_dte_timestamp.toString()+ ", " +
				this.m_obj_product.getProductName();
	} // end:override
 	
} // end:class
