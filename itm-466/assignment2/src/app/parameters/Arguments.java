package app.parameters;

import java.util.HashMap;
import java.util.Map;

/**
 * Class used to virtualize a collection of Command line arguments.
 * 
 */
public class Arguments {

	Map<String, String> m_map_Parameters;

	/**
	 * Default class constructor.
	 */
	public Arguments() {
		this.m_map_Parameters = new HashMap<String, String>();
	}

	/**
	 * Overloaded class constructor that can be used to initialize the Parameter
	 * map using a string array.
	 * 
	 * @param args
	 *            String array instance
	 */
	public Arguments(String[] args) {
		this.m_map_Parameters = new HashMap<String, String>();
		for (int i = 0; i < args.length; i++) {
			String _str = args[i];
			Argument _newArg = new Argument(_str);
			this.m_map_Parameters.put(_newArg.getKey(), _newArg.getValue());
		}
	}

	/**
	 * @return the parameters
	 */
	public Map<String, String> getParameters() {
		return this.m_map_Parameters;
	}

	/**
	 * @param parameters
	 *            the parameters to set
	 */
	public void setParameters(Map<String, String> parameters) {
		this.m_map_Parameters = parameters;
	}

	/**
	 * Method that can be used to test if a parameter exists.
	 * 
	 * @param arg
	 *            String
	 * @return Boolean
	 */
	public Boolean exists(String arg) {
		return this.m_map_Parameters.containsKey(arg);
	}

}