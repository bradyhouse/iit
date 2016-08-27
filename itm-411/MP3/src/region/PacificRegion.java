package region;

import producer.CogsWellCogs;
import producer.SpacelySprockets;

/**
 * Product consumer for states
 * belonging to "EST" time zone.
 * 
 * @author brady house
 */
 public class PacificRegion extends RegionBaseClass {

	/**
	 * Default class constructor
	 */
	public PacificRegion() {
	
	} // end:constructor

	/**
	 * Overloaded constructor
	 * 
	 * @param sprocketQueue target sprocket product message queue.
	 * @param cogQueue target cog product message queue.
	 */
	public PacificRegion(SpacelySprockets sprocketQueue,
			CogsWellCogs cogQueue) {
		super(sprocketQueue, cogQueue);
	} // end:constructor

	/**
	 * @see regions.RegionBaseClass#getRegion()
	 */
	@Override
	public synchronized String getRegion() {
		// TODO Auto-generated method stub
		return "PT";
	} // end:getter
	
} // end:class
