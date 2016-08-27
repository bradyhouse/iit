package region;

import producer.CogsWellCogs;
import producer.SpacelySprockets;

/**
 * Product consumer for states
 * belonging to the "MST" time zone.
 * 
 * @author brady house
 */
public class MountainRegion extends RegionBaseClass {

	/**
	 * Default class constructor.
	 */
	public MountainRegion() {

	} // end:constructor

	/**
	 * Overloaded constructor
	 * 
	 * @param sprocketQueue target sprocket product message queue.
	 * @param cogQueue target cog product message queue.
	 */
	public MountainRegion(SpacelySprockets sprocketQueue,
			CogsWellCogs cogQueue) {
		super(sprocketQueue, cogQueue);
	
	} // end:constructor

	/**
	 * @see region.RegionBaseClass#getRegion()
	 */
	@Override
	public synchronized String getRegion() {
		return "MT";
	} // end:getter

} // end:class
