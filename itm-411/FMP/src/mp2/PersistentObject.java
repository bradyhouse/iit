package mp2;

import java.io.*;
import java.util.*;
import java.text.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Comparator;

import mp2.Record.SumLevCode;



/**
 * Serializable class that can be used 
 * to store a collection of PopulationRecords.
 *
 * @author Brady Houseknecht
 */
public class PersistentObject implements Serializable, 
        mp2.CollectionAnalytics {

    
    
    private ArrayList<mp2.PopulationRecord> m_col_populationRecords;
    private Date m_dte_date;
    
    /**
     * Default class constructor.
     */
    public PersistentObject()
    {
        this.m_dte_date = new Date();
    } // end constructor
    /**
     * Overloaded constructor that can be used to 
     * load the PopulationRecords.
     * @param inputFile string value equal to the path of source CSV file.
     */
    public PersistentObject(String inputFile)
    {
        this.m_dte_date = new Date();
        this.m_col_populationRecords = new ArrayList<mp2.PopulationRecord>();
        
        this.loadCSV(inputFile);
        
    } // end constructor
    /**
     * Initialization Date Getter.
     * @return Date equal to the timestamp when the class was constructed.
     */
    public Date getDate()
    {
      return this.m_dte_date;  
    } // end getter
    
    /**
     * Control function that can be used populate the
     * PopulationRecords collection using a CSV file.
     * @param inputFile string value equal to the path of a CSV file.
     */
    public void loadCSV(String inputFile)
    {
        FileReader input;
        BufferedReader bufRead; 
        String row;
        
        try {
            input = new FileReader(inputFile);
            bufRead = new BufferedReader(input);
            try {
                int line = 1;
                while ( (row = bufRead.readLine()) != null) 
                {    
                    if (line > 1)
                    {
                        String[] values = row.split(",");
                        PopulationRecord rec = new PopulationRecord(values);
                        if(!this.m_col_populationRecords.contains(rec))
                            this.m_col_populationRecords.add(rec);
                    } // end if
                    line++;
                } // end while
            } // end try
            catch (IOException ex) {
                Logger.getLogger(
                        PersistentObject.class.getName()).log(Level.SEVERE, 
                        null, ex);
            } // end catch
        } // end try 
        catch (FileNotFoundException ex) {
            Logger.getLogger(
                    PersistentObject.class.getName()).log(Level.SEVERE, 
                    null, ex);
        } // end catch
    } // end void
   
    /**
     * PopulationRecords Getter.
     * @return PopulationRecords instance.
     */
    public ArrayList<PopulationRecord> getPopulationRecords()
    {
        return this.m_col_populationRecords;
    } // end getter
   /**
    * Override of the Object.ToString() to provide the object's
    * state. Output the data contained in the PopulationRecords collection
    * object.
    */
    @Override
    public String toString()
    {
        return this.m_col_populationRecords.toString();
    } // end Override
    
   /**
    * Implementation of the CollectionAnalytics.getCountOfEstPopIncr method.
    */
   @Override
    public long getCountOfEstPopIncr(int year) {
	long result = 0;
        for(PopulationRecord rec : m_col_populationRecords)
        {
              if( 
               rec.getState()!=mp2.Record.StateFIPCode.NA &&
                      rec.popPerIncr(year)> 0)
                result++;
        } // end for
	return result;	
     } // end function
   /**
    * Implementation of the CollectionAnalytics.getCountOfEstPopDecr method.
    */
    @Override
    public long getCountOfEstPopDecr(int year) {
		long result = 0;
        for(PopulationRecord rec : m_col_populationRecords)
        {
            if(
                    rec.getState()!=mp2.Record.StateFIPCode.NA &&
                    rec.popPerIncr(year) < 0)
                result++;
        } // end for
		return result; 
    } // end function
   
    /**
     * Implementation of the CollectionAnlytics.getStateWithMostPop method.
     */
    @Override
    public String getStateWithMostPop(int year) {
		
	for(PopulationRecord rec : m_col_populationRecords)
        {
            rec.setComparisonYear(year);
        } // end for	
        Comparator comparator = Collections.reverseOrder();
        Collections.sort(m_col_populationRecords,comparator);
        for(PopulationRecord rec : m_col_populationRecords)
        {
            if(rec.getState()!=mp2.Record.StateFIPCode.NA)
                    return rec.getState().name();
        }// end for
        return m_col_populationRecords.get(0).getState().name();
		
    } // end function
    /**
     * Implementation with the CollectionAnalytics.getStateWithLeastPop method.
     */
    @Override
    public String getStateWithLeastPop(int year) {
        
        for(PopulationRecord rec : m_col_populationRecords)
        {
           rec.setComparisonYear(year);
        } // end for
        Collections.sort(m_col_populationRecords);
        for(PopulationRecord rec : m_col_populationRecords)
        {
            if(rec.getState()!=mp2.Record.StateFIPCode.NA)
                    return rec.getState().name();
        }// end for
	return m_col_populationRecords.get(0).getState().name();
    } // end function
   
} // end class