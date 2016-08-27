package Tablets;

/**
 * Virtual version of a Google Nexus Tablet.
 * @author Brady Houseknecht
 */

public class NexusTablet extends Tablet {

     protected static long NexusCounter=0;
  
    /**
     * Default Constructor
     */
    public NexusTablet()
    {
       super();
       NexusCounter++;
    } // end GalaxyTablet
    
    /**
     * Overloaded Constructor
     * @param manufacturer string defining the tablet's manufacturer
     * @param modelNumber string defining the tablet's model number
     * @param displaySize double defining the tablet's display size
     * @param memory double defining the tablet's memory
     * @param hasWifi boolean defining whether the tablet's has wifi support
     * @param hasMobileCarrier boolean defining whether the tablet's has 
     * mobile carrier
     * @param weight double defining the weight of the tablet
     * @param cost double defining the price (or cost) of the tablet
     */
    public NexusTablet(String manufacturer, String modelNumber, 
            double displaySize, double memory, boolean hasWifi,
            boolean hasMobileCarrier, double weight,
            double cost)
    {
        super(manufacturer,modelNumber,displaySize,memory,
                hasWifi,hasMobileCarrier,weight,cost);
       
         NexusCounter++;
       
        
    } // end GalaxyTablet
    
    /**
     * @return long value corresponding to the instance count
     * of the GalaxyTablet Class.
     */
    public static long getInstanceCount()
    {
        return NexusCounter;
    } // end getInstanceCount
    
  
}
