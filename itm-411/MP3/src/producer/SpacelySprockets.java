
package producer;

import java.util.Date;
import java.util.Random;

import product.ProductMessage;
import product.Sprocket;
import region.State;


/**
 * Producer class used to virtualize Spacely Sprockets.
 *
 * @author brady house
 */
public class SpacelySprockets extends ProducerBaseClass {

	/**
	 * Default class constructor.
	 */
	public SpacelySprockets() {
		super();
	
	} // end:constructor

	/**
	 * Overloaded constructor that 
	 * accepts the path of the state time zone
	 * mapping csv file.
	 * 
	 * @param stateZoneMapFilePath string value equal to the
	 * name and path of the state / time zone mapping file.
	 */
	public SpacelySprockets(String stateZoneMapFilePath)
	{
		super(stateZoneMapFilePath);
	} // end:constructor
	
	/**
	 * @see producer.ProducerBaseClass#run()
	 */
	@Override
	public void run() {
		 
	
				Random randomGenerator = new Random();
				
				int randomInt = randomGenerator.nextInt(46);
				
				State state = this.getTimeZoneMap().getStates().get(randomInt);
				
				State.TimeZoneType zone = State.TimeZoneType.valueOf(state.getTimeZone().trim().toUpperCase());
				
				Sprocket s = new Sprocket(Integer.toString(randomInt), "Spacely Sprockets",state.getState() + " Sprocket", 1.1, 5.50);
				
				Date timeStamp = new Date();
				
				ProductMessage msg;
				
				switch(zone)
				{
					case EST:
						msg = new ProductMessage(timeStamp,s,State.TimeZoneType.EST);
						break;
					case CST:
						msg = new ProductMessage(timeStamp,s,State.TimeZoneType.CST);
						break;
					case MT:
						msg = new ProductMessage(timeStamp,s,State.TimeZoneType.MT);
						break;
					default:
						msg = new ProductMessage(timeStamp,s,State.TimeZoneType.PT);
						break;
				} // end:switch
				
				this.add(msg, "SpacelySprockets");
				
				System.out.println(this.getClass().toString() + " Added "+ state.getState() + ", " + msg.getRegion() + " product message.");
			
	} // end:run
	
	

} // end:class
