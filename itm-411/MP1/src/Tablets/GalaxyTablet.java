package Tablets;

/**
 * Virtual version of a Samsung Galaxy Tablet.
 * 
 * @author brady houseknecht
 */
public class GalaxyTablet extends Tablet {
    
    protected static long GalaxyCounter=0;
    /**
     * Default Constructor
     */
    public GalaxyTablet()
    {
        super();
        GalaxyCounter++;
    } // end GalaxyTablet
    
    /**
     * Overloaded Constructor
     * @param manufacturer string defining the tablet's manufacturer
     * @param modelNumber string defining the tablet's model number
     * @param displaySize double defining the tablet's display size
     * @param memory double defining the tablet's memory
     * @param hasWifi boolean defining whether the tablet's has wifi support
     * @param hasMobileCarrier boolean defining whether the tablet's has mobile
     * carrier
     * @param weight double defining the weight of the tablet
     * @param cost double defining the price (or cost) of the tablet
     */
    public GalaxyTablet(String manufacturer, String modelNumber, 
            double displaySize, double memory, boolean hasWifi,
            boolean hasMobileCarrier, double weight,
            double cost)
    {
        super(manufacturer,modelNumber,displaySize,memory,
                hasWifi,hasMobileCarrier,weight,cost);
        
        GalaxyCounter++;
        
    } // end GalaxyTablet
    
    /**
     * @return long value corresponding to the instance count
     * of the GalaxyTablet Class.
     */
    public static long getInstanceCount()
    {
        return GalaxyCounter;
    } // end getInstanceCount
    
} // end GalaxyTablet
