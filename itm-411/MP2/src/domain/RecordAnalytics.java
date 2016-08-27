
package domain;

/**
 * Interface that exposes a set of methods for performing 
 * analytic operation on a single Record object.
 * @author Brady Houseknecht
 */
public interface RecordAnalytics {
    /**
     * Population increase percentage.
     * @return double equal to the population percent increase
     */
    public double popPerIncr(int year);
    /**
     * Max births in a given year.
     * @param year integer value equal to either 2010 or 2011.
     * @return long value equal to maximum number of births in the 
     * given year.
     */
    long maxBirthPerYear(int year);
    /**
     * Min births in a given years.
     * @param year integer value equal to either 2010 or 2011.
     * @return long value equal to minimum number of births in the 
     * given year.
     */
    long minBirthPerYear(int year);
    /**
     * Max deaths in a given year.
     * @param year integer value equal to either 2010 or 2011
     * @return long value equal to max number of deaths in the 
     * given year.
     */
    long maxDeathPerYear(int year);
    /**
     * Min deaths in a given year.
     * @param year integer value equal to either 2010 or 2011
     * @return long value equal to min number of deaths in the 
     * given year.
     */
    long minDeathPerYear(int year);
} // end interface
