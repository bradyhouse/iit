/**
 * 
 */
package region;

import producer.CogsWellCogs;
import producer.SpacelySprockets;
import product.ProductMessage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TimeZone;


/**
 * Base class that encapsulates methods and
 * properties common to region objects.
 * 
 * @author Brady House
 */
public abstract class RegionBaseClass implements Runnable {

	
	private SpacelySprockets m_obj_inBoundSprockets;
	private CogsWellCogs m_obj_inBoundCogs;
	private Queue<ProductMessage> m_que_products;
	
	
	/**
	 * Class constructor.
	 */
	public RegionBaseClass()
	{
		this.m_obj_inBoundSprockets = null;
		this.m_obj_inBoundCogs = null;
		this.m_que_products = new LinkedList<ProductMessage>();
	} // end:constructor
	
	/**
	 * Overloaded class constructor.
	 *
	 * @param sprocketQueue target sprocket Product Message queue.
	 * @param cogQueue target cog Product Message queue.
	 */
	public RegionBaseClass(SpacelySprockets sprocketQueue, CogsWellCogs cogQueue)
	{
		this.m_obj_inBoundSprockets = sprocketQueue;
		this.m_obj_inBoundCogs = cogQueue;
		this.m_que_products = new LinkedList<ProductMessage>();
		
		System.out.println(this.getClass().toString()+ " started.");
		
	} // end:constructor
	
	/**
	 * @return the m_str_region
	 */
	public abstract String getRegion();
	
	/**
	 * Method used to convert a date to the
	 * classes time zone format.
	 * 
	 * @param date value equal to the target date.
	 * @return string in the time format of the configured
	 * time zone.
	 */
	public synchronized String toLocalTime(Date date)
	{
		  DateFormat format = new SimpleDateFormat();
		  TimeZone zone = TimeZone.getTimeZone(this.getRegion());
		  format.setTimeZone(zone);
		  return format.format(date);
	
	} // end:toLocalTime
	
	/**
	 * @return the In Bound Sprockets Queue
	 */
 	public synchronized SpacelySprockets getSprocketQueue() {
		return m_obj_inBoundSprockets;
	}  // end:getter

	/**
	 * @param sprocketQueue target Sprocket Product Message queue
	 */
	public synchronized void setSprocketQueue(SpacelySprockets sprocketQueue) {
		this.m_obj_inBoundSprockets = sprocketQueue;
	} // end:setter

	/**
	 * @return the current Cog Product Message queue
	 */
	public synchronized CogsWellCogs getCogQueue() {
		return m_obj_inBoundCogs;
	} // end:getter

	/**
	 * @param cogQueue target Cog Product Message queue
	 */
	public synchronized void setCogQueue(CogsWellCogs cogQueue) {
		this.m_obj_inBoundCogs = cogQueue;
	} // end:setter

	/**
	 * @return the m_que_products
	 */
	public synchronized Queue<ProductMessage> getProducts() {
		return m_que_products;
	}

	/**
	 * @param m_que_products the m_que_products to set
	 */
	public synchronized void setProducts(Queue<ProductMessage> m_que_products) {
		this.m_que_products = m_que_products;
	}
	
	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
	
			/// Use Case:::
			/// 1) Attempt to Obtain the Cog Queue Lock
			/// 2) If attempt to obtain lock failed --> go to step 7
			/// 3) Peek at the current item at the top of the sprocket producer's queue
			/// 4) If the item's time zone does not match the time zone
			/// defined by the class, then release the lock --> go to step 7
			/// 5) If the item's time zone matches the the time zone 
			/// defined by the class, then poll the message from the 
			/// queue
			/// 6) Add the sprocket carried by the message to the class'
			/// product queue--> go to step 3.
			/// 7) Attempt to Obtain the Sprocket producer's queue lock.
			/// 8) If attempt to obtain lock failed --> go to step 1
			/// 9) Peek at the current item at the top of the cog producer's queue
			/// 10) If the item's time zone does not match the time zone
			/// defined by the class, then release the lock --> go to step 1
			/// 11) Poll the message from the cog producer queue.
			/// 12) Add the cog carried by the message to the class'
			/// product queue--> go to step 9.
			
			this.processSprockets();
			this.processCogs();

	} // end:run
	
	/**
	 * Control function used to peek and pull
	 * sprockets from the configured SpacelySprockets producer
	 * class instance.
	 * @return Boolean value indicating whether process
	 * succeeded
	 */
	private synchronized Boolean processSprockets()
	{
		State.TimeZoneType timezone = State.TimeZoneType.valueOf(this.getRegion());
			ProductMessage nxtSprocket = this.getSprocketQueue().peek(this.getRegion());
			if(null!=nxtSprocket &&
					nxtSprocket.getRegion() == timezone )
			{
				nxtSprocket = this.getSprocketQueue().poll(this.getRegion());
				
				if(null!=nxtSprocket)
				{
					System.out.println(this.getClass().toString() + " polled sprocket @ " + 
							this.toLocalTime(nxtSprocket.getTimeStamp()));
						
					this.m_que_products.add(nxtSprocket);
					return true;
				} // end:if
				else
					return false;
			} // end:if
			else
				return false;
	} // end:processSprockets
	
	/**
	 * Control function used to peek and pull
	 * cogs from the configured CogsWellCogs producer
	 * class instance.
	 * @return Boolean value indicating whether process
	 * succeeded
	 */
	private synchronized Boolean processCogs()
	{
		State.TimeZoneType timezone = State.TimeZoneType.valueOf(this.getRegion());
		
		
			ProductMessage nxtCog = this.getCogQueue().peek(this.getRegion());
			if(null!=nxtCog &&
					nxtCog.getRegion() == timezone )
			{
				nxtCog = this.getCogQueue().poll(this.getRegion());
				
				if(null!=nxtCog)
				{
					System.out.println(this.getClass().toString() + " polled cog @ " + this.toLocalTime(nxtCog.getTimeStamp()));
					this.m_que_products.add(nxtCog);
					return true;
				} // end:if
				else
					return false;
			} // end:if
			else
				return false;
	} // end:processSprockets
	
	/**
	 * Override of the toString method, which
	 * used to display the total products in the
	 * Queue.
	 */
	@Override
	public synchronized String toString()
	{
		return this.getClass().toString() + 
				" Total Products:  " + 
				Integer.toString(this.m_que_products.size());
	} // end:override
	
	/**
	 * Write the contents of the product que
	 * to the console.
	 */
	public void out()
	{
		  System.out.println("");
		  System.out.println(this.getClass().toString() + " processed: ");
		  System.out.println("");
		  System.out.println("Id, Product Name, Description, Weight, Cost, TimeStamp ");
		  for (ProductMessage msg : this.m_que_products)
		  {
			  System.out.println( 
					  msg.getProduct().toString() +", " + this.toLocalTime(msg.getTimeStamp()) +
					  " " + this.getRegion());
		  } // end:for
		  System.out.println("");
	} // end:out
	
	
	/**
	 * Writes the contents of the product queue
	 * to file in the specified location.
	 *
	 * @param dir string value equal to the location
	 * where the product list should be written.
	 */
	public void write(String dir)
	{
		 try
		 {
			 /// If the file exists, then delete it
			 
			 File f = new File(dir + "\\" + this.getRegion().toLowerCase() + ".csv");
			 if(f.exists()) 
			 { 
				 f.delete();
			 } // end:if
			 
			  /// Create a buffered file stream
			  FileWriter fstream = new FileWriter(dir + "\\" + this.getRegion().toLowerCase() + ".csv");
			  BufferedWriter out = new BufferedWriter(fstream);
			  /// Add field name header
			  out.write("Id, Product Name, Description, Weight, Cost, TimeStamp " + (char)(10) );
			  /// Write the contents of the product queue to the buffer
			  for (ProductMessage msg : this.m_que_products)
			  {
				  out.write(msg.getProduct().toString() +", " + 
						  this.toLocalTime(msg.getTimeStamp()) + " " + 
						  this.getRegion() + (char)(10));
			  } // end:for
			  
			  /// Close the stream
			  out.close();
			  
		 }  // end:try
		 catch (Exception e)
		 {
			 System.err.println("Error: " + e.getMessage());
		 } // end:catch
	} // end:write
		
		
	
} // end:class
