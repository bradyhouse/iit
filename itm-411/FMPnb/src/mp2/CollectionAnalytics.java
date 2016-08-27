package mp2;

import java.util.*;
/**
 * Interface that exposes a set of methods for performing analytic 
 * operations on a collection of Record objects.
 * @author Brady Houseknecht
 */
public interface CollectionAnalytics {
    
    /**
     * Count of states with estimated population increase.
     * @param year integer value equal to either 2010 or 2011.
     * @return long value equal to the count of states having a population
     * increase.
     */
     long getCountOfEstPopIncr(int year);
     /**
     * Count of states with estimated population decrease
     * @param year integer value equal to either 2010 or 2011
     * @return long value equal to the count of states having a population
     * decrease.
     */
     long getCountOfEstPopDecr(int year);
     /**
      * Get the State with the most population.
      * @param year integer value equal to either 2010 or 2011
      * @return String value containing the name of the state with the 
      * most population.
      */
     String getStateWithMostPop(int year);
     /**
      * Get the State with the least population.
      * @param year integer value equal to either 2010 or 2011
      * @return String value containing the name of the state with the 
      * least population.
      */
     String getStateWithLeastPop(int year);
    
}   //end class