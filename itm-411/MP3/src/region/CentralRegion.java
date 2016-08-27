package region;


import producer.CogsWellCogs;
import producer.SpacelySprockets;


/**
 * Product consumer for states belonging to "CST"
 * timezone.
 * 
 * @author brady house
 */
public class CentralRegion extends RegionBaseClass {

	/**
	 * Default class constructor
	 */
	public CentralRegion() {
		
	} // end:CentralRegion

	/**
	 * Overloaded constructor
	 * 
	 * @param sprocketQueue target sprocket product message queue.
	 * @param cogQueue target cog product message queue.
	 */
	public CentralRegion(SpacelySprockets sprocketQueue,
			CogsWellCogs cogQueue) {
		super(sprocketQueue, cogQueue);
	} // end:constructor

	/**
	 * @see regions.RegionBaseClass#getRegion()
	 */
	@Override
	public synchronized String getRegion() {
		return "CST";
	} // end:getter

} // end:class
