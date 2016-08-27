
package mpUtil;

import java.util.*;

/**
 * String Key/Value pair dictionary object.
 * 
 * @author Brady Houseknecht
 */
public class StringDictionary {
    private Map m_col_map;

    /**
    * Class Constructor.
    */
    public StringDictionary(){
      this.m_col_map = new HashMap();
    } // end StringDictionary

    /**
    * Function used to get a specific
    * key value.
    * @param key string corresponding to the desired dictionary key.
    * @return string mapped to the requested key.
    */
    public String get(String key) {
      return this.m_col_map.get(key).toString();
    } // end get
    
    /**
     * @return integer equal to the number items in the dictionary
     */
    public int count()
    {
        return this.m_col_map.size();
    } // end count
    
    /**
     * @param key String value to test for
     * @return boolean indicating whether the dictionary contains the key
     */
    public boolean contains(String key)
    {
       return this.m_col_map.containsKey(key); 
    }
    
    /**
     * @return Dictionary Iterator
     */
    public Iterator getIterator()
    {
        return this.m_col_map.entrySet().iterator();
    }
    
    /**
     * Function used to put or update a value
     * associated with a given key.
     * @param key string equal to the target dictionary key
     * @param value string equal to the value of target dictionary key
     */
    public void put(String key, String value) {
          this.m_col_map.put(key, value);
     
    } // end put
} // end StringDictionary