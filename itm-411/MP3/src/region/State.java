/**
 * 
 */
package region;

/**
 * Class used to encapsulate the 
 * attributes of a state.
 * 
 * @author brady house
 *
 */
public class State {

	private String m_str_State;
	private String m_str_TimeZone;

	/**
	 * Enumeration describing the timezones
	 * that a state can belong too.
	 */
	public enum TimeZoneType
	{
		NA,
		EST,
		CST,
		PT,
		MT
	} // end:enum

	/**
	 * @return the m_str_State
	 */
	public String getState() {
		return m_str_State;
	} // end:getter

	/**
	 * @param m_str_State the m_str_State to set
	 */
	public void setState(String m_str_State) {
		this.m_str_State = m_str_State;
	} // end:setter
	
	/**
	 * @return the m_str_TimeZone
	 */
	public String getTimeZone() {
		return m_str_TimeZone;
	} // end:getter

	/**
	 * @param m_str_TimeZone the m_str_TimeZone to set
	 */
	public void setTimeZone(String m_str_TimeZone) {
		this.m_str_TimeZone = m_str_TimeZone;
	} // end:setter

	/**
	 * Default class constructor
	 */
	public State()
	{
		this.m_str_State = "";
		this.m_str_TimeZone = "";
	} // end:constructor
	
	/**
	 * Overloaded class constructor
	 * @param state
	 * @param timeZone
	 */
	public State(String state, String timeZone)
	{
		this.m_str_State = state;
		this.m_str_TimeZone = timeZone;
		
	} // end:overload
	
	/**
	 * Overloaded class constructor, which
	 * accepts the parameter necessary 
	 * initialize the class as a string array.
	 * 
	 * @param pair single dimensional string array 
	 * where 0=State, 1=TimeZone
	 */
	public State(String[] pair)
	{
		this.m_str_State = pair[0];
		this.m_str_TimeZone = pair[1];
	} // end:overload
	
} // end:class
