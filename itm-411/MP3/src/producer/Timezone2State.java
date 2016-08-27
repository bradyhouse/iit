/**
 * Communication adapters and utilities
 */
package producer;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

import region.State;

/**
 * @ Utility class referenced by each producer which maps the time zone to a list of states
 * in that zone.  If the state has two time zones, then the class will return the larger of
 * the two.  This class also exposes  an array of the continental US states. 
 * 
 * @author Brady House
 *
 */
public class Timezone2State {

	private ArrayList<State> m_col_states;
	
	/**
	 * Class constructor.
	 */
	public Timezone2State()
	{
		/// Initialize the state array
		this.m_col_states = new ArrayList<State>();
		System.out.println("parsing CSV...");       
		this.loadCSV("..\\data\\stateTimeZoneMap.csv");
	} // end:constructor

	/**
	 * Overloaded Class constructor.
	 */
	public Timezone2State(String mapFilePath)
	{
		/// Initialize the state array
		this.m_col_states = new ArrayList<State>();
		System.out.println("parsing CSV...");       
		this.loadCSV(mapFilePath);
	} // end:constructor
		
	/**
     * Control function that can be used populate the
     * State collection using a CSV file.
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
                        State rec = new State(values);
                        if(!this.m_col_states.contains(rec))
                            this.m_col_states.add(rec);
                    } // end if
                    line++;
                } // end while
            } // end try
            catch (IOException ex) {
                Logger.getLogger(
                        State.class.getName()).log(Level.SEVERE, 
                        null, ex);
            } // end catch
        } // end try 
        catch (FileNotFoundException ex) {
            Logger.getLogger(
                    State.class.getName()).log(Level.SEVERE, 
                    null, ex);
        } // end catch
    } // end void
    
    /**
     * Method used to get an ArrayList of all states
     * based on a given time zone.
     * 
     * @param timeZone string value equal to the target time zone.
     * @return ArrayList<String> containing the names of all states belonging to
     * the requested time zone.
     */
    public ArrayList<String> getStatesByTimeZone(String timeZone)
    {
    	ArrayList<String> result = new ArrayList<String>();
    	
    	for(State s : this.m_col_states)
    	{
    		if(s.getTimeZone() == timeZone)
    		{
    			result.add(s.getState());
    		} // end:if
    		
    	} // end:for
    	
    	return result;
    } // end:getStatesByTimeZone
    
    /**
     * Method used to lookup a state's time zone given
     * the name.
     * @param state string value equal to the name of the target state.
     * @return string value equal to the time zone of the requested state.
     */
    public String getStateTimeZone(String state)
    {
    	String result = "";
    	
    	for(State s : this.m_col_states)
    	{
    		if(s.getState() == state)
    		{
    			return s.getTimeZone();
    		} // end:if
    	} // end:for
    	return result;
    } // end:getStateTimeZone
    
	/**
	 * @return the m_col_states
	 */
	public ArrayList<State> getStates() {
		return m_col_states;
	} // end:getter

	/**
	 * @param m_col_states the m_col_states to set
	 */
	public void setStates(ArrayList<State> m_col_states) {
		this.m_col_states = m_col_states;
	} // end:setter

}  // end:class
