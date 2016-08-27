package region;

import producer.CogsWellCogs;
import producer.SpacelySprockets;

/**
 * Product consumer for states
 * belonging to "EST" time zone.
 * 
 * @author brady house
 */
public class EasternRegion extends RegionBaseClass {


	/**
	 * Default Class constructor
	 */
	public EasternRegion() {
		
	} // end:constructor

	/**
	 * Overloaded constructor
	 * 
	 * @param sprocketQueue target sprocket product message queue.
	 * @param cogQueue target cog product message queue.
	 */
	public EasternRegion(SpacelySprockets sprocketQueue,
			CogsWellCogs cogQueue) {
		super(sprocketQueue, cogQueue);
	} // end:constructor

	/**
	 * @see regions.RegionBaseClass#getRegion()
	 */
	@Override
	public synchronized String getRegion() {
		
		return "EST";
	} // end:getter
	
} // end:class
