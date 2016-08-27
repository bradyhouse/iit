package producer;

import java.util.Date;
import java.util.Random;
import product.Cog;
import product.ProductMessage;
import region.State;

/**
 * Producer class used to virtualize CogsWell Cogs.
 * 
 * @author brady house
 */
public class CogsWellCogs extends ProducerBaseClass {

	/**
	 * Default class constructor.
	 */
	public CogsWellCogs() {
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
	public CogsWellCogs(String stateTimeZoneMap)
	{
		super(stateTimeZoneMap);
	} // end:constructor

	/**
	 * @see producer.ProducerBaseClass#run()
	 */
	@Override
	public void run() {
		
	
			Random randomGenerator = new Random();
			
			int randomInt = randomGenerator.nextInt(47);
			
			State state = this.getTimeZoneMap().getStates().get(randomInt);
			
			State.TimeZoneType zone = State.TimeZoneType.valueOf(state.getTimeZone().trim().toUpperCase());
			
			Cog c = new Cog(Integer.toString(randomInt), "Cogswell Cogs",state.getState() + " Cog", 1.1, 5.50);
			
			Date timeStamp = new Date();
			
			ProductMessage msg;
			
			switch(zone)
			{
				case EST:
					msg = new ProductMessage(timeStamp,c,State.TimeZoneType.EST);
					break;
				case CST:
					msg = new ProductMessage(timeStamp,c,State.TimeZoneType.CST);
					break;
				case MT:
					msg = new ProductMessage(timeStamp,c,State.TimeZoneType.MT);
					break;
				default:
					msg = new ProductMessage(timeStamp,c,State.TimeZoneType.PT);
					break;
			} // end:switch
			
			
			this.add(msg, "CogsWellCogs");
			
			System.out.println(this.getClass().toString() + " Added "+ state.getState() + ", " + msg.getRegion() + " product message.");
		
	} // end:run
} // end:class
