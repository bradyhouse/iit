package bo;

import java.util.HashSet;
import java.util.Set;

/**
 * Sub class that extends the abstract Record class.
 * @author Brady Houseknecht
 */
public class PopulationRecord extends Record {
    
    /**
     * Class constructor.
     */
    public PopulationRecord()
    {
        super();
    } // end constructor
    
    /**
     * Overloaded class constructor that
     * accepts a string array of values.
     * The string array should consist of 31 values
     * in the following sequence based on field name: SUMLEV, REGION, 
     * DIVISION, STATE, NAME, CENSUS2010POP, ESTIMATESBASE2010, 
     * POPESTIMATE2010, POPESTIMATE2011, NPOPCHG_2010, 
     * NPOPCHG_2011, BIRTHS2010, BIRTHS2011, DEATHS2010, DEATHS2011, 
     * NATURALINC2010, NATURALINC2011, INTERNATIONALMIG2010, 
     * INTERNATIONALMIG2011, DOMESTICMIG2010, DOMESTICMIG2011, NETMIG2010, 
     * NETMIG2011, RESIDUAL2010, RESIDUAL2011, RBIRTH2011, RDEATH2011, 
     * RNATURALINC2011, RINTERNATIONALMIG2011, RDOMESTICMIG2011, RNETMIG2011.
     * @param csv string array of field values
     */
    public PopulationRecord(String[] csv)
    {
    	super();
    	
    	System.out.println(csv);
        
        this.setSumlev(csv[0]);
        this.setRegion(csv[1]);
        this.setDivision(csv[2]);
        this.setState(csv[3]);
        String name = csv[4];
        this.setName(name);
        long census2010pop = Long.parseLong(csv[5]);
        this.setCensus2010pop(census2010pop);
        long estimatesbase2010 = Long.parseLong(csv[6]);
        this.setEstimatesbase2010(estimatesbase2010);
        long popestimate2010 = Long.parseLong(csv[7]);
        this.setPopestimate2010(popestimate2010);
        long popestimate2011 = Long.parseLong(csv[8]);
        this.setPopestimate2011(popestimate2011);
        long npopchg_2010 = Long.parseLong(csv[9]);
        this.setNPopChg2010(npopchg_2010);
        long npopchg_2011 = Long.parseLong(csv[10]);
        this.setNPopChg2011(npopchg_2011);
        long births2010 = Long.parseLong(csv[11]);
        this.setBirths2010(births2010);
        long births2011 = Long.parseLong(csv[12]);
        this.setBirths2011(births2011);
        long deaths2010 = Long.parseLong(csv[13]);
        this.setDeaths2010(deaths2010);
        long deaths2011 = Long.parseLong(csv[14]);
        this.setDeaths2011(deaths2011);
        long naturalinc2010 = Long.parseLong(csv[15]);
        this.setNaturalinc2010(naturalinc2010);
        long naturalinc2011 = Long.parseLong(csv[16]);
        this.setNaturalinc2011(naturalinc2011);
        long internationalmig2010 = Long.parseLong(csv[17]);
        this.setInternationalmig2010(internationalmig2010);
        long internationalmig2011 = Long.parseLong(csv[18]);
        this.setInternationalmig2011(internationalmig2011);
        long domesticmig2010 = Long.parseLong(csv[19]);
        this.setDomesticmig2010(domesticmig2010);
        long domesticmig2011 = Long.parseLong(csv[20]);
        this.setDomesticmig2011(domesticmig2011);
        long netmig2010 = Long.parseLong(csv[21]);
        this.setNetmig2010(netmig2010);
        long netmig2011 = Long.parseLong(csv[22]);
        this.setNetmig2011(netmig2011);
        long residual2010 = Long.parseLong(csv[23]);
        this.setResidual2010(residual2010);
        long residual2011 = Long.parseLong(csv[24]);
        this.setResidual2011(residual2011);
        double rbirth2011 = Double.parseDouble(csv[25]);
        this.setRbirth2011(rbirth2011);
        double rdeath2011 = Double.parseDouble(csv[26]);
        this.setRdeath2011(rdeath2011);
        double rnaturalinc2011 = Double.parseDouble(csv[27]);
        this.setRnaturalinc2011(rnaturalinc2011);
        double rinternationalmig2011 = Double.parseDouble(csv[28]);
        this.setRinternationalmig2011(rinternationalmig2011);
        double rdomesticmig2011 = Double.parseDouble(csv[29]);
        this.setRdomesticmig2011(rdomesticmig2011);
        double rnetmig2011 = Double.parseDouble(csv[30]);
        this.setRnetmig2011(rnetmig2011);
    } // end overload
} // end class