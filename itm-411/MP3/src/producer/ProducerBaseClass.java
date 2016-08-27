package producer;

import java.util.Queue;
import java.util.LinkedList;

import product.ProductMessage;


/**
 * Base class that encapsulates methods and
 * properties common to region objects.
 * 
 * @author Brady House
 */
public abstract class ProducerBaseClass implements Runnable  {
	
	private Queue<ProductMessage> m_que_products;
	private Timezone2State m_cls_timeZoneMap;
	
	/**
	 * Default class constructor.
	 */
	public ProducerBaseClass()
	{
		this.m_que_products = new LinkedList<ProductMessage>();
		this.m_cls_timeZoneMap = new Timezone2State();
		System.out.println(this.getClass().toString() + " started.");
	} // end:constructor

	/**
	 * Overloaded constructor that accepts
	 * the the file path of the state / time zone
	 * mapping file.
	 * 
	 * @param stateZoneMapFilePath string value equal to the
	 * name and path of the state / time zone mapping file.
	 */
	public ProducerBaseClass(String stateZoneMapFilePath)
	{
		this.m_que_products = new LinkedList<ProductMessage>();
		this.m_cls_timeZoneMap = new Timezone2State(stateZoneMapFilePath);
		System.out.println(this.getClass().toString() + " started.");
		
	} // end:constructor
	
	
	/**
	 * @return the m_que_products
	 */
	public synchronized Queue<ProductMessage> getProducts() {
		return m_que_products;
	} // end:getter

	/**
	 * @param m_que_products the m_que_products to set
	 */
	public synchronized void setProducts(Queue<ProductMessage> m_que_products) {
		this.m_que_products = m_que_products;
	} // end:setter
	
	/**
	 * Method used to add a Product Message to the 
	 * Message Que once the lock is obtained.
	 * 
	 * @param msg product message object that is to 
	 * be added to the message queue.
	 * @param lockOwner string value equal to the current lock owner.
	 * if this value doesn't equal the current lock owner, then the
	 * method fails.
	 * @return boolean indicating whether the message was successfully
	 * added to the queue.
	 */
	public synchronized void add(ProductMessage msg, String lockOwner)
	{
			this.m_que_products.add(msg);
	} // end:add
	
	/**
	 * Method used to poll an object out of the
	 * queue.
	 * @param lockOwner string value equal to the current lock owner.
	 * if this value doesn't equal the current lock owner, then the
	 * @return Product message object pulled from the queue. If the requested
	 * lockOwner doesn't match the current LockOwner, then the method
	 * returns null.
	 */
	public synchronized ProductMessage poll(String lockOwner)
	{
			return this.m_que_products.poll();
	} // end:poll
	
	/**
	 * Method used to see the current message that is
	 * at the top of the queue. 
	 * @return Product message object currently at the top of the queue. 
	 * If the requested lockOwner doesn't match the current LockOwner, 
	 * then the method returns null.
	 */
	public synchronized ProductMessage peek(String lockOwner)
	{
		return this.m_que_products.peek();
	} // end:peek
	
	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public abstract void run();

	/**
	 * @return the m_cls_timeZoneMap
	 */
	public synchronized Timezone2State getTimeZoneMap() {
		return m_cls_timeZoneMap;
	} // end:getter

	/**
	 * @param m_cls_timeZoneMap the m_cls_timeZoneMap to set
	 */
	public synchronized void setTimeZoneMap(Timezone2State m_cls_timeZoneMap) {
		this.m_cls_timeZoneMap = m_cls_timeZoneMap;
	} // end:setter

} // end:class
