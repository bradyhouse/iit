package Tablets;
import java.util.*;

/**
 * Abstract super class that virtualizes the  
 * the generic characteristics of a tablet device.
 * 
 * @author Brady Houseknecht
 */
public abstract class Tablet implements Interfaces.TabletRelatable {
    
  
    private String m_str_nl;
    private String m_str_cr;
    private String m_str_tb;
    private UUID m_uid_Identifier;
    private String m_str_Manufacturer;
    private String m_str_ModelNumber;
    private double m_dbl_DisplaySize;
    private double m_dbl_Memory;
    private boolean m_boo_HasWifi;
    private boolean m_boo_HasMobileCarrier;
    private double m_dbl_Weight;
    private double m_dbl_Cost;
    
    
    /**
     * Default Constructor
     */
    public Tablet()
    {
        this.m_str_nl = new Character((char)10).toString();
        this.m_str_cr = new Character((char)13).toString();
        this.m_str_tb = new Character((char)9).toString();
        this.m_uid_Identifier = UUID.randomUUID();
        this.m_str_Manufacturer = "";
        this.m_str_ModelNumber = "";
        this.m_dbl_DisplaySize = 0.0;
        this.m_dbl_Memory = 0.0;
        this.m_boo_HasWifi = false;
        this.m_boo_HasMobileCarrier = false;
        this.m_dbl_Weight= 0.0;
        this.m_dbl_Cost = 0.0;
        
    } // end Tablet
    
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
    public Tablet(String manufacturer, String modelNumber, 
            double displaySize, double memory, boolean hasWifi,
            boolean hasMobileCarrier, double weight,
            double cost)
    {
        this.m_str_nl = new Character((char)10).toString();
        this.m_str_cr = new Character((char)13).toString();
        this.m_str_tb = new Character((char)9).toString();
        this.m_uid_Identifier= UUID.randomUUID();
        this.m_str_Manufacturer = manufacturer;
        this.m_str_ModelNumber = modelNumber;
        this.m_dbl_DisplaySize = displaySize;
        this.m_dbl_Memory = memory;
        this.m_boo_HasWifi = hasWifi;
        this.m_boo_HasMobileCarrier = hasMobileCarrier;
        this.m_dbl_Weight = weight;
        this.m_dbl_Cost = cost;
    
    } // end Tablet
    
    
    /**
    * Override of the class to string in order
    * to return the color and piece type attributes.
    */
    @Override
    public String toString() {
         return  this.m_str_nl + this.m_str_cr +
                 this.m_str_nl + this.m_str_cr +
                 this.m_str_tb + "Manufacturer: "+ 
                 this.m_str_tb + this.m_str_Manufacturer + 
                 this.m_str_nl + this.m_str_cr +
                 this.m_str_tb + "Model Number: " + this.m_str_tb + 
                 this.m_str_ModelNumber +  
                 this.m_str_nl + this.m_str_cr  +
                 this.m_str_tb + "Display Size: " + this.m_str_tb + 
                 this.m_dbl_DisplaySize +  
                 this.m_str_nl + this.m_str_cr  +
                 this.m_str_tb + "Memory: " + this.m_str_tb + 
                 this.m_dbl_Memory +  
                 this.m_str_nl + this.m_str_cr  +
                 this.m_str_tb + "Weight: " + this.m_str_tb + 
                 this.m_dbl_Weight +  
                 this.m_str_nl + this.m_str_cr  +
                 this.m_str_tb + "Wifi: " + this.m_str_tb + this.m_str_tb + 
               ((this.m_boo_HasWifi == true) ? "Yes" : "No") +  
                 this.m_str_nl + this.m_str_cr  +
                 this.m_str_tb + "Mobile Carrier: " +
               ((this.m_boo_HasMobileCarrier==true) ? "Yes" : "No" ) +  
                 this.m_str_nl + this.m_str_cr  +
                 this.m_str_tb + "Price: " + this.m_str_tb + 
                 this.m_str_tb + this.m_dbl_Cost +  
                 this.m_str_nl + this.m_str_cr +
                 this.m_str_nl + this.m_str_cr ;
    } // end toString
    
    /**
     * @return the Unique Identifier
     */
    public UUID getUniqueIdentifier() {
        return this.m_uid_Identifier;
    } // end getUniqueIdentifier

   
    /**
     * @return the Manufacturer
     */
    public String getManufacturer() {
        return this.m_str_Manufacturer;
    } // end getManufacturer

    /**
     * @param manufacturer the Manufacturer to set
     */
    public void setManufacturer(String manufacturer) {
        this.m_str_Manufacturer = manufacturer;
    } // end setManufacturer

    /**
     * @return the ModelNumber
     */
    public String getModelNumber() {
        return this.m_str_ModelNumber;
    } // end getModelNumber

    /**
     * @param modelNumber the ModelNumber to set
     */
    public void setModelNumber(String modelNumber) {
        this.m_str_ModelNumber = modelNumber;
    } // end setModelNumber

    /**
     * @return the DisplaySize
     */
    public double getDisplaySize() {
        return this.m_dbl_DisplaySize;
    } // end getDisplaySize

    /**
     * @param displaySize the DisplaySize to set
     */
    public void setDisplaySize(double displaySize) {
        this.m_dbl_DisplaySize = displaySize;
    } // end setDisplaySize

    /**
     * @return the Memory
     */
    public double getMemory() {
        return this.m_dbl_Memory;
    } // end getMemory

    /**
     * @param memory the Memory to set
     */
    public void setMemory(double memory) {
        this.m_dbl_Memory = memory;
    } //end setMemory

    /**
     * @return the HasWifi
     */
    public boolean getHasWifi() {
        return this.m_boo_HasWifi;
    } // end getHasWifi

    /**
     * @param hasWifi the HasWifi to set
     */
    public void setHasWifi(boolean hasWifi) {
        this.m_boo_HasWifi = hasWifi;
    } // end setHasWifi

    /**
     * @return the HasMobileCarrier
     */
    public boolean getHasMobileCarrier() {
        return this.m_boo_HasMobileCarrier;
    } // end getHasMobileCarrier

    /**
     * @param hasMobileCarrier the HasMobileCarrier to set
     */
    public void setHasMobileCarrier(boolean hasMobileCarrier) {
        this.m_boo_HasMobileCarrier = hasMobileCarrier;
    } // end setHasMobileCarrier

    /**
     * @return the Weight
     */
    public double getWeight() {
        return this.m_dbl_Weight;
    } // end getWeight

    /**
     * @param weight the Weight to set
     */
    public void setWeight(double weight) {
        this.m_dbl_Weight= weight;
    } // end setWeight

    /**
     * @return the Cost
     */
    public Double getCost() {
        return this.m_dbl_Cost;
    } // end getCost

    /**
     * @param cost the Cost to set
     */
    public void setCost(double cost) {
        this.m_dbl_Cost = cost;
    } // end getCost
 
     /**
     * Implementation of the TabletRelatable Interface 
     * FindLargestDisplayTablet Method.
     * 
     * @param tablets Array of Tablet objects
     * @return string containing the manufacturer, model number and display size
     * of the tablet in the passed array with the largest display size. NOTE - 
     * If more then one tablet meets this criteria, then the entries are
     * delimited by comma.
     */
    @Override
    public String findLargestDisplayTablet(Tablets.Tablet[] tablets)
    {
        StringBuilder result = new StringBuilder();
        
        double largestDisplaySize = tablets[0].getDisplaySize();
        
        // Iterate through the tables and determine
        // the largest display size.
        for(Tablet tablet:tablets)
        {
            if(tablet.getDisplaySize() > largestDisplaySize)
            {
               largestDisplaySize = tablet.getDisplaySize();
            } // end if
        } // end for
        
        // Populate a string builder with
        // all tablets having that lightest
        // weight. Each entry should have
        // the format:
        // <Manufacturer> <Model Number>: <Display Size> inches
        // Note - If the number of tablets meeting
        // this criteria is greater than 1, then delimit
        // the entries by comma.
        
        int lineIndex = 1;
        int matchCount = 0;
        
        for(Tablet tablet:tablets)
        {
            if(tablet.getDisplaySize()==largestDisplaySize)
            {    
                matchCount++;
            } // end if
        } // end for
        
        for(Tablet tablet:tablets)
        {
            if(tablet.getDisplaySize()==largestDisplaySize)
            {
                String entry = tablet.getManufacturer()+" "+
                       tablet.getModelNumber()+":  " + this.m_str_tb + 
                        largestDisplaySize +
                        " inches" +
                        ( ( lineIndex >= 1 && 
                            lineIndex < matchCount ) ? 
                            "," : ""
                        ) +
                        this.m_str_nl + this.m_str_cr;
                result.append(entry);
                lineIndex++;
             } // end if
        } // end for
        
        return result.toString();
        
    } // end FindLargestDisplayTablet
    
    /**
     * Implementation of the TabletRelatable Interface 
     * FindLowestCostTablet Method.
     * 
     * @param tablets Array of Tablet objects
     * @return string containing the manufacturer, model number and cost
     * of the tablet in the passed array with the lowest cost. NOTE - If 
     * more then one tablet meets this criteria, then the entries are
     * delimited by comma.
     */
    @Override
    public String findLowestCostTablet(Tablets.Tablet[] tablets)
    {
        StringBuilder result = new StringBuilder();
        
        double lowestCost = tablets[0].getCost();
        
        // Iterate through the tables and determine
        // the lowest cost.
        for(Tablet tablet:tablets)
        {
            if(tablet.getCost() < lowestCost)
            {
               lowestCost = tablet.getCost();
            } // end if
        } // end for
        
        // Populate a string builder instance with
        // all tablets with a cost equal to the lowest cost
        // weight. Each entry should have
        // the format:
        // <Manufacturer> <Model Number>: $<Cost>
        // Note - If the number of tablets meeting
        // this criteria is greater than 1, then delimit
        // the entries by comma.
        
        int matchCount = 0;
        
        for(Tablet tablet:tablets)
        {
            if(tablet.getCost()==lowestCost)
            {    
                matchCount++;
            } // end if
        } // end for
        
        int lineIndex = 1;
        
        for(Tablet tablet:tablets)
        {
            if(tablet.getCost()==lowestCost)
            {
                String entry = tablet.getManufacturer()+" "+
                       tablet.getModelNumber()+":" + this.m_str_tb + "$" 
                        + Double.toString(lowestCost) +
                       ( ( lineIndex >= 1 && 
                            lineIndex < matchCount ) ? 
                            "," : ""
                        ) +
                        this.m_str_nl + this.m_str_cr;
                result.append(entry);
                lineIndex++;
             } // end if
        } // end for
        
        return result.toString();

    
    } // end FindLowestCostTablet

     /**
     * Implementation of the TabletRelatable Interface 
     * FindNumberOfCreatedTabletTypes Method.
     * 
     * @param tablets Array of Tablet objects
     * @return string containing the manufacturer, model number and count
     * for each manufacturer model number combination occurring in the
     * array.
     */
    @Override
    public String findNumberOfCreatedTabletTypes(Tablets.Tablet[] tablets)
    {
        StringBuilder result = new StringBuilder();
        
        String line =  
                "Galaxy Tablets: " + this.m_str_tb + this.m_str_tb +
                Long.toString(Tablets.GalaxyTablet.getInstanceCount())+ ",";
        
        result.append(line);

        line =  this.m_str_nl + this.m_str_cr +
                "Nexus Tablets: " + this.m_str_tb + this.m_str_tb +
                Long.toString(Tablets.NexusTablet.getInstanceCount())+ ",";
        
        result.append(line);
        
        line =  this.m_str_nl + this.m_str_cr +
                "Ipad Tablets: " + this.m_str_tb + this.m_str_tb +
                Long.toString(Tablets.iPadTablet.getInstanceCount());
        
        result.append(line);
        
        return result.toString();

    } // end FindNumberOfCreatedTabletTypes

    /**
    * Implementation of the TabletRelatable Interface 
    * findLightestWeightTabletType Method.
    * 
    * @param tablets Array of Tablet objects
    * @return string containing the manufacturer, model number and weight
    * of the tablet or tablets in the passed array with the lowest cost. NOTE - 
    * If more then one tablet meets this criteria, then the entries are
    * delimited by comma.
    */
    @Override
    public String findLightestWeightTabletType(Tablets.Tablet[] tablets)
    {
        StringBuilder result = new StringBuilder();
        double lightestWeight = tablets[0].getWeight();
        
        // Iterate through the tablets
        // and find the lowest weight
        for(Tablet tablet:tablets)
        {
            if(tablet.getWeight() < lightestWeight)
            {
                lightestWeight=tablet.getWeight();
            } // end if
        } // end for
        
        
        // Populate a string builder with
        // all tablets having that lightest
        // weight. Each entry should have
        // the format:
        // <Manufacturer> <Model Number>: <Weight> Ounces
        // Note - If the number of tablets meeting
        // this criteria is greater than 1, then delimit
        // the entries by comma.
        
        int lineIndex = 1;
        
        for(Tablet tablet:tablets)
        {
            if(tablet.getWeight()==lightestWeight)
            {
                String entry = tablet.getManufacturer()+" "+
                       tablet.getModelNumber()+":  " + lightestWeight +
                        " Ounces" +
                        ( ( lineIndex >= 1 && 
                            lineIndex < result.length() ) ? 
                            "," : ""
                        ) +
                        this.m_str_nl + this.m_str_cr;
                result.append(entry);
                lineIndex++;
            } // end if
             
        } // end for
         return result.toString();
    } // end FindLightestWeightTabletType
} // end Tablet
