package bo;
import java.io.Serializable;
import java.util.*;

/**
 * Abstract super class that virtualizes the  
 * the generic characteristics of a single row.
 * 
 * @author Brady Houseknecht
 */
public abstract class Record implements Serializable, 
        bo.RecordAnalytics, Comparable<Record>
{
    private int m_int_comparisonyear;
    private String m_str_sumlev;
    private String m_str_region;
    private String m_str_division;
    private String m_str_state;
    private String m_str_name;
    private long m_long_census2010pop;
    private long m_long_estimatesbase2010;
    private long m_long_popestimate2010;
    private long m_long_popestimate2011;
    private long m_long_npopchg_2010;
    private long m_long_npopchg_2011;
    private long m_long_births2010;
    private long m_long_births2011;
    private long m_long_deaths2010;
    private long m_long_deaths2011;
    private long m_long_naturalinc2010;
    private long m_long_naturalinc2011;
    private long m_long_internationalmig2010;
    private long m_long_internationalmig2011;
    private long m_long_domesticmig2010;
    private long m_long_domesticmig2011;
    private long m_long_netmig2010;
    private long m_long_netmig2011;
    private long m_long_residual2010;
    private long m_long_residual2011;
    private double m_dbl_rbirth2011;
    private double m_dbl_rdeath2011;
    private double m_dbl_rnaturalinc2011;
    private double m_dbl_rinternationalmig2011;
    private double m_dbl_rdomesticmig2011;
    private double m_dbl_rnetmig2011;

    /**
     * Enumeration containing State FIP code values.
     */
    public enum StateFIPCode {
        NA, AL, AK, AZ, AR, CA, CO, CT, DE, DC, FL, GA, HI, ID, 
        IL, IN, IA, KS, KY, LA, ME, MD, MA, MI, MN, MS, MO, 
        MT, NE, NV, NH, NJ, NM, NY, NC, ND, OH, OK, OR, PA, 
        RI, SC, SD, TN, TX, UT, VT, VA, WA, WV, WI, WY, PR, VI
    } // end enum
    /**
     * Enumeration containing Census Region code values.
     */
    public enum RegionCode {
      Total, Northeast, Midwest, South, West, NA
    } // end enum
    /**
     * Enumeration containing Geographic Summary Level values.
     */
    public enum SumLevCode {
        Nation, Region, State, NA
    } // end enum
    /**
     * Enumeration containing Census Division Code values.
     */
    public enum DivisionCode {
       United_States_Total, New_England,  Middle_Atlantic,  
       East_North_Central,  West_North_Central,  South_Atlantic,  
       East_South_Central,  West_South_Central,  Mountain,  Pacific,  
       Not_Applicable 
    } // end enum
    /**
     * Default Constructor
     */
    public Record()
    {
        this.m_str_sumlev ="NA";
        this.m_str_region ="NA";
        this.m_str_division = "NA";
        this.m_str_state = "NA";
        this.m_str_name = "NA";
        this.m_long_census2010pop = 0;
        this.m_long_estimatesbase2010 = 0;
        this.m_long_popestimate2010 = 0;
        this.m_long_popestimate2011 = 0;
        this.m_long_npopchg_2010 = 0;
        this.m_long_npopchg_2011 = 0;
        this.m_long_births2010 = 0;
        this.m_long_births2011 = 0;
        this.m_long_deaths2010 = 0;
        this.m_long_deaths2011 = 0;
        this.m_long_naturalinc2010 = 0;
        this.m_long_naturalinc2011 = 0;
        this.m_long_internationalmig2010 = 0;
        this.m_long_internationalmig2011 = 0;
        this.m_long_domesticmig2010 = 0;
        this.m_long_domesticmig2011 = 0;
        this.m_long_netmig2010 = 0;
        this.m_long_netmig2011 = 0;
        this.m_long_residual2010 = 0;
        this.m_long_residual2011 = 0;
        this.m_dbl_rbirth2011  = 0.0;
        this.m_dbl_rdeath2011  = 0.0;
        this.m_dbl_rnaturalinc2011  = 0.0;
        this.m_dbl_rinternationalmig2011  = 0.0;
        this.m_dbl_rdomesticmig2011  = 0.0;
        this.m_dbl_rnetmig2011  = 0.0;
        
    } // end constructor
    /**
     * Overloaded Constructor
     * @param  sumlev  Geographic Summary Level
     * @param  region  Census Region code
     * @param  division  Census Division code
     * @param  state  State FIPS code
     * @param  name  State name
     * @param  census2010pop  4/1/2010 resident total Census 2010 population
     * @param  estimatesbase2010  4/1/2010 resident total population estimates base
     * @param  popestimate2010  7/1/2010 resident total population estimate
     * @param  popestimate2011  7/1/2011 resident total population estimate
     * @param  npopchg_2010  Numeric Change in resident total population 4/1/2010 to 7/1/2010
     * @param  npopchg_2011  Numeric Change in resident total population 7/1/2010 to 7/1/2011
     * @param  births2010  Births in period 4/1/2010 to 6/30/2010
     * @param  births2011  Births in period 7/1/2010 to 6/30/2011
     * @param  deaths2010  Deaths in period 4/1/2010 to 6/30/2010
     * @param  deaths2011  Deaths in period 7/1/2010 to 6/30/2011
     * @param  naturalinc2010  Natural increase in period 4/1/2010 to 6/30/2010
     * @param  naturalinc2011  Natural increase in period 7/1/2010 to 6/30/2011
     * @param  internationalmig2010  Net international migration in period 4/1/2010 to 6/30/2010
     * @param  internationalmig2011  Net international migration in period 7/1/2010 to 6/30/2011
     * @param  domesticmig2010  Net domestic migration in period 4/1/2010 to 6/30/2010
     * @param  domesticmig2011  Net domestic migration in period 7/1/2010 to 6/30/2011
     * @param  netmig2010  Net migration in period 4/1/2010 to 6/30/2010
     * @param  netmig2011  Net migration in period 7/1/2010 to 6/30/2011
     * @param  residual2010  Residual for period 4/1/2010 to 6/30/2010
     * @param  residual2011  Residual for period 7/1/2010 to 6/30/2011
     * @param  rbirth2011  Birth rate in period 7/1/2010 to 6/30/2011
     * @param  rdeath2011  Death rate in period 7/1/2010 to 6/30/2011
     * @param  rnaturalinc2011  Natural increase rate in period 7/1/2010 to 6/30/2011
     * @param  rinternationalmig2011  Net international migration rate in period 7/1/2010 to 6/30/2011
     * @param  rdomesticmig2011  Net domestic migration rate in period 7/1/2010 to 6/30/2011
     * @param  rnetmig2011  Net migration rate in period 7/1/2010 to 6/30/2011
     */
    public Record(String sumlev, String region, String division, 
            String state, String name, long census2010pop, 
            long estimatesbase2010, long popestimate2010, long popestimate2011, 
            long npopchg_2010, long npopchg_2011, long births2010, 
            long births2011, long deaths2010, long deaths2011, 
            long naturalinc2010, long naturalinc2011, long internationalmig2010, 
            long internationalmig2011, long domesticmig2010, 
            long domesticmig2011, long netmig2010, long netmig2011, 
            long residual2010, long residual2011, double rbirth2011, 
            double rdeath2011, double rnaturalinc2011, 
            double rinternationalmig2011, double rdomesticmig2011, 
            double rnetmig2011) 
    {
        this.m_str_sumlev =sumlev;
        this.m_str_region =region;
        this.m_str_division =division;
        this.m_str_state =state;
        this.m_str_name = name;
        this.m_long_census2010pop = census2010pop;
        this.m_long_estimatesbase2010 = estimatesbase2010;
        this.m_long_popestimate2010 = popestimate2010;
        this.m_long_popestimate2011 = popestimate2011;
        this.m_long_npopchg_2010 = npopchg_2010;
        this.m_long_npopchg_2011 = npopchg_2011;
        this.m_long_births2010 = births2010;
        this.m_long_births2011 = births2011;
        this.m_long_deaths2010 = deaths2010;
        this.m_long_deaths2011 = deaths2011;
        this.m_long_naturalinc2010 = naturalinc2010;
        this.m_long_naturalinc2011 = naturalinc2011;
        this.m_long_internationalmig2010 = internationalmig2010;
        this.m_long_internationalmig2011 = internationalmig2011;
        this.m_long_domesticmig2010 = domesticmig2010;
        this.m_long_domesticmig2011 = domesticmig2011;
        this.m_long_netmig2010 = netmig2010;
        this.m_long_netmig2011 = netmig2011;
        this.m_long_residual2010 = residual2010;
        this.m_long_residual2011 = residual2011;
        this.m_dbl_rbirth2011  = rbirth2011;
        this.m_dbl_rdeath2011  = rdeath2011;
        this.m_dbl_rnaturalinc2011  = rnaturalinc2011;
        this.m_dbl_rinternationalmig2011  = rinternationalmig2011;
        this.m_dbl_rdomesticmig2011  = rdomesticmig2011;
        this.m_dbl_rnetmig2011  = rnetmig2011;

    } // end constructor
    /**
     * Summary Level Code Getter.
     * @return Enumerated SumLevCode value.
     */
    public String getSumlev() {
        return m_str_sumlev;
    } // end void
    /**
     * Summary Level Code Setter.
     * @param sumlev SumLevCode enumerated value.
     */
    public void setSumlev(String sumlev) {
        this.m_str_sumlev = sumlev;
    } // end void
    /**
     * Region Code Getter.
     * @return Enumerated RegionCode value.
     */
    public String getRegion() {
        return m_str_region;
    } // end void
    /**
     * Region Code Setter.
     * @param region RegionCode enumerated value.
     */
    public void setRegion(String region) {
        this.m_str_region = region;
    } // end void
    /**
     * Division Code setter.
     * @return Enumerated RegionCode value.
     */
    public String getDivision() {
        return m_str_division;
    } // end void
    /**
     * Division Code Setter.
     * @param division DivisionCode Enumerated value.
     */
    public void setDivision(String division) {
          this.m_str_division = division;
    } // end void
    /**
     * State FIP Code Getter.
     * @return Enumerated StateFIPCode value.
     */
    public String getState() {
        return m_str_state;
    } // end void
    /**
     * State FIP Code Setter.
     * @param state Enumerated StateFIPCode value.
     */
    public void setState(String state) {
    	this.m_str_state = state;
    } // end void
    public void setComparisonYear(int year)
    {
        this.m_int_comparisonyear = year;
    } // end void
    /**
     * Name field value getter.
     * @return String equal to the Name field value.
     */
    public String getName() {
        return m_str_name;
    } // end void
    /**
     * Name field value setter.
     * @param name String equal to the new Name field value.
     */
    public void setName(String name) {
        this.m_str_name = name;
    } // end void
    /**
     * CENSUS2010POP field value getter. 
     * This value is defined as
     * "4/1/2010 resident total Census 2010 population."
     * @return long value.
     */
    public long getCensus2010pop() {
        return m_long_census2010pop;
    } // end void
    /**
     * CENSUS2010POP field value setter.
     * This field is defined as
     * "4/1/2010 resident total Census 2010 population."
     * @param census2010pop long equal to the new value of the field.
     */
    public void setCensus2010pop(long census2010pop) {
        this.m_long_census2010pop = census2010pop;
    } // end void
    /**
     * ESTIMATESBASE2010 field value getter.
     * This field is defined as "4/1/2010 resident total 
     * population estimates base."
     * @return long value equal to the value of the field.
     */
    public long getEstimatesbase2010() {
        return m_long_estimatesbase2010;
    } // end void
    /**
     * ESTIMATESBASE2010 field value setter.
     * This field is defined as "4/1/2010 resident total 
     * population estimates base."
     * @param estimatesbase2010 long value equal to the new value of the field.
     */
    public void setEstimatesbase2010(long estimatesbase2010) {
        this.m_long_estimatesbase2010 = estimatesbase2010;
    } // end void
    /**
     * POPESTIMATE2010 field value getter.
     * This field is defined as " 7/1/2010 resident total population estimate."
     * @return long value equal to the value of the field.
     */
    public long getPopestimate2010() {
        return m_long_popestimate2010;
    } // end void
    /**
     * POPESTIMATE2010 field value setter.
     * This field is defined as "7/1/2010 resident total population estimate."
     * @param popestimate2010 long value equal to the new value of the field.
     */
    public void setPopestimate2010(long popestimate2010) {
        this.m_long_popestimate2010 = popestimate2010;
    } // end void
    /**
     * POPESTIMATE2011 field value getter.
     * This field is defined as "7/1/2011 resident total population estimate."
     * @return long value equal to the value of the field.
     */
    public long getPopestimate2011() {
        return m_long_popestimate2011;
    } // end void
    /**
     * POPESTIMATE2011 field value setter.
     * This field is defined as "7/1/2011 resident total population estimate."
     * @param popestimate2011 long value equal to the new value of the field.
     */
    public void setPopestimate2011(long popestimate2011) {
        this.m_long_popestimate2011 = popestimate2011;
    } // end void
    /**
     * NPOPCHG_2010 field value getter.
     * This field is defined as "Numeric Change in resident total 
     * population 4/1/2010 to 7/1/2010."
     * @return long value equal to the value of the field.
     */
    public long getNPopChg2010() {
        return m_long_npopchg_2010;
    } // end void
    /**
     * NPOPCHG_2010 field value setter.
     * This field is defined as "Numeric Change in resident total 
     * population 4/1/2010 to 7/1/2010."
     * @param npopchg2010 long value equal to the new value of the field.
     */
    public void setNPopChg2010(long npopchg2010) {
        this.m_long_npopchg_2010 = npopchg2010;
    } // end void
    /**
     * NPOPCHG_2011 field value getter.
     * This field is defined as "Numeric Change in resident total population
     * 7/1/2010 to 7/1/2011."
     * @return long value equal to the value of the field.
     */
    public long getNPopChg2011() {
        return m_long_npopchg_2011;
    } // end void
    /**
     * NPOPCHG_2011 field value setter.
     * This field is defined as "Numeric Change in resident total population
     * 7/1/2010 to 7/1/2011."
     * @param npopchg2011 long value equal to the new value of the field.
     */
    public void setNPopChg2011(long npopchg2011) {
        this.m_long_npopchg_2011 = npopchg2011;
    } // end void
    /**
     * BIRTHS2010 field value getter.
     * This field is defined as "Births in period 4/1/2010 to 6/30/2010."
     * @return long value equal to the value of the field.
     */
    public long getBirths2010() {
        return m_long_births2010;
    } // end void
    /**
     * BIRTHS2010 field value setter.
     * This field is defined as "Births in period 4/1/2010 to 6/30/2010."
     * @param births2010 long value equal to the new value of the field.
     */
    public void setBirths2010(long births2010) {
        this.m_long_births2010 = births2010;
    } // end void
    /**
     * BIRTHS2011 field value getter.
     * This field is defined as "Births in period 7/1/2010 to 6/30/2011."
     * @return long value equal to the value of the field.
     */
    public long getBirths2011() {
        return m_long_births2011;
    } // end void
    /**
     * BIRTHS2011 field value setter.
     * This field is defined as "Births in period 7/1/2010 to 6/30/2011."
     * @param births2011 long value equal to the new value of the field.
     */
    public void setBirths2011(long births2011) {
        this.m_long_births2011 = births2011;
    } // end void
    /**
     * DEATHS2010 field value getter.
     * This field is defined as "Deaths in period 4/1/2010 to 6/30/2010."
     * @return long value equal to the value of the field.
     */
    public long getDeaths2010() {
        return m_long_deaths2010;
    } // end void
    /**
     * DEATHS2010 field value setter.
     * This field is defined as "Deaths in period 4/1/2010 to 6/30/2010."
     * @param deaths2010 long value equal to the new value of the field.
     */
    public void setDeaths2010(long deaths2010) {
        this.m_long_deaths2010 = deaths2010;
    } // end void
    /**
     * DEATHS2011 field value getter.
     * This field is defined as "Deaths in period 7/1/2010 to 6/30/2011."
     * @return long value equal to the value of the field.
     */
    public long getDeaths2011() {
        return m_long_deaths2011;
    } // end void
    /**
     * DEATHS2011 field value setter.
     * This field is defined as "Deaths in period 7/1/2010 to 6/30/2011."
     * @param deaths2011 long value equal to the new value of the field.
     */
    public void setDeaths2011(long deaths2011) {
        this.m_long_deaths2011 = deaths2011;
    } // end void
    /**
     * NATURALINC2010 field value getter.
     * This field is defined as "Natural increase in period 4/1/2010 
     * to 6/30/2010."
     * @return long value equal to the value of the field.
     */
    public long getNaturalinc2010() {
        return m_long_naturalinc2010;
    } // end void
    /**
     * NATURALINC2010 field value setter.
     * This field is defined as "Natural increase in period 4/1/2010 
     * to 6/30/2010."
     * @param naturalinc2010 long value equal to the new value of the field.
     */
    public void setNaturalinc2010(long naturalinc2010) {
        this.m_long_naturalinc2010 = naturalinc2010;
    } // end void
    /**
     * NATURALINC2011 field value getter.
     * This field is defined as "Natural increase in period 7/1/2010 
     * to 6/30/2011."
     * @return long value equal to the value of the field.
     */
    public long getNaturalinc2011() {
        return m_long_naturalinc2011;
    } // end void
    /**
     * NATURALINC2011 field value setter.
     * This field is defined as "Natural increase in period 7/1/2010 
     * to 6/30/2011."
     * @param naturalinc2011 long value equal to the new value of the field.
     */
    public void setNaturalinc2011(long naturalinc2011) {
        this.m_long_naturalinc2011 = naturalinc2011;
    } // end void
    /**
     * INTERNATIONALMIG2010 field value getter.
     * This field is defined as "Net international migration in 
     * period 4/1/2010 to 6/30/2010."
     * @return long value equal to the value of the field.
     */
    public long getInternationalmig2010() {
        return m_long_internationalmig2010;
    } // end void
    /**
     * INTERNATIONALMIG2010 field value setter.
     * This field is defined as "Net international migration in 
     * period 4/1/2010 to 6/30/2010."
     * @param internationalmig2010 long value equal to the new value of the 
     * field.
     */
    public void setInternationalmig2010(long internationalmig2010) {
        this.m_long_internationalmig2010 = internationalmig2010;
    } // end void
    /**
     * INTERNATIONALMIG2011 field value getter.
     * This field is defined as "Net international migration in period 
     * 7/1/2010 to 6/30/2011."
     * @return long value equal to the value of the field.
     */
    public long getInternationalmig2011() {
        return m_long_internationalmig2011;
    } // end void
    /**
     * INTERNATIONALMIG2011 field value setter.
     * This field is defined as "Net international migration in period 
     * 7/1/2010 to 6/30/2011."
     * @param internationalmig2011 long value equal to the new value of the
     * field.
     */
    public void setInternationalmig2011(long internationalmig2011) {
        this.m_long_internationalmig2011 = internationalmig2011;
    } // end void
    /**
     * DOMESTICMIG2010 field value getter.
     * This field is defined as "Net domestic migration in period 4/1/2010
     * to 6/30/2010."
     * @return long value equal to the value of the field.
     */
    public long getDomesticmig2010() {
        return m_long_domesticmig2010;
    } // end void
    /**
     * DOMESTICMIG2010 field value setter.
     * This field is defined as "Net domestic migration in period 4/1/2010
     * to 6/30/2010." 
     * @param domesticmig2010 long value equal to the new value of the field.
     */
    public void setDomesticmig2010(long domesticmig2010) {
        this.m_long_domesticmig2010 = domesticmig2010;
    } // end void
    /**
     * DOMESTICMIG2011 field value getter.
     * This field is defined as "Net domestic migration in period 7/1/2010
     * to 6/30/2011."
     * @return long value equal to the value of the field.
     */
    public long getDomesticmig2011() {
        return m_long_domesticmig2011;
    } // end void
    /**
     * DOMESTICMIG2011 field value setter.
     * This field is defined as "Net domestic migration in period 7/1/2010
     * to 6/30/2011."
     * @param domesticmig2011 long value equal to the new value of the field.
     */
    public void setDomesticmig2011(long domesticmig2011) {
        this.m_long_domesticmig2011 = domesticmig2011;
    } // end void
    /**
     * NETMIG2010 field value getter.
     * This field is defined as "Net migration in period 4/1/2010 to 6/30/2010."
     * @return long value equal to the value of the field.
     */
    public long getNetmig2010() {
        return m_long_netmig2010;
    } // end void
    /**
     * NETMIG2010 field value setter.
     * This field is defined as "Net migration in period 4/1/2010 to 6/30/2010."
     * @param netmig2010 long value equal to the new value of the field.
     */
    public void setNetmig2010(long netmig2010) {
        this.m_long_netmig2010 = netmig2010;
    } // end void
    /**
     * NETMIG2011 field value getter.
     * This field is defined as "Net migration in period 7/1/2010 to 6/30/2011."
     * @return long value equal to the value of the field.
     */
    public long getNetmig2011() {
        return m_long_netmig2011;
    } // end void
    /**
     * NETMIG2011 field value setter.
     * This field is defined as "Net migration in period 7/1/2010 to 6/30/2011."
     * @param netmig2011 long value equal to the new value of the field.
     */
    public void setNetmig2011(long netmig2011) {
        this.m_long_netmig2011 = netmig2011;
    } // end void
    /**
     * RESIDUAL2010 field value getter.
     * This field is defined as "Residual for period 4/1/2010 to 6/30/2010."
     * @return long value equal to the value of the field.
     */
    public long getResidual2010() {
        return m_long_residual2010;
    } // end void
    /**
     * RESIDUAL2010 field value setter.
     * This field is defined as "Residual for period 4/1/2010 to 6/30/2010."
     * @param residual2010 long value equal to the new value of the field.
     */
    public void setResidual2010(long residual2010) {
        this.m_long_residual2010 = residual2010;
    } // end void
    /**
     * RESIDUAL2011 field value getter.
     * This field is defined as "Residual for period 7/1/2010 to 6/30/2011."
     * @return long value equal to the value of the field.
     */
    public long getResidual2011() {
        return m_long_residual2011;
    } // end void
    /**
     * RESIDUAL2011 field value setter.
     * This field is defined as "Residual for period 7/1/2010 to 6/30/2011."
     * @param residual2011 long value equal to the new value of the field.
     */
    public void setResidual2011(long residual2011) {
        this.m_long_residual2011 = residual2011;
    } // end void
    /**
     * RBIRTH2011 field value getter.
     * This field is defined as "Birth rate in period 7/1/2010 to 6/30/2011."
     * @return long value equal to the value of the field.
     */
    public double getRbirth2011() {
        return m_dbl_rbirth2011;
    } // end void
    /**
     * RBIRTH2011 field value setter.
     * This field is defined as "Birth rate in period 7/1/2010 to 6/30/2011."
     * @param rbirth2011 long value equal to the new value of the field.
     */
    public void setRbirth2011(double rbirth2011) {
        this.m_dbl_rbirth2011 = rbirth2011;
    } // end void
    /**
     * RDEATH2011 field value getter.
     * This field is defined as "Death rate in period 7/1/2010 to 6/30/2011."
     * @return long value equal to the value of the field.
     */
    public double getRdeath2011() {
        return m_dbl_rdeath2011;
    } // end void
    /**
     * RDEATH2011 field value setter.
     * This field is defined as "Death rate in period 7/1/2010 to 6/30/2011."
     * @param rdeath2011 long value equal to the value of the field.
     */
    public void setRdeath2011(double rdeath2011) {
        this.m_dbl_rdeath2011 = rdeath2011;
    } // end void
    /**
     * RNATURALINC2011 field value getter.
     * This field is defined as "Natural increase rate in period 
     * 7/1/2010 to 6/30/2011."
     * @return long value equal to the value of the field.
     */
    public double getRnaturalinc2011() {
        return m_dbl_rnaturalinc2011;
    } // end void
    /**
     * RNATURALINC2011 field value setter.
     * This field is defined as "Natural increase rate in period 
     * 7/1/2010 to 6/30/2011."
     * @param rnaturalinc2011 long value equal to the new value of the field.
     */
    public void setRnaturalinc2011(double rnaturalinc2011) {
        this.m_dbl_rnaturalinc2011 = rnaturalinc2011;
    } // end void
    /**
     * RINTERNATIONALMIG2011 field value getter.
     * This field is defined as "Net international migration rate in period
     * 7/1/2010 to 6/30/2011."
     * @return long value equal to the value of the field.
     */
    public double getRinternationalmig2011() {
        return m_dbl_rinternationalmig2011;
    } // end void
    /**
     * RINTERNATIONALMIG2011 field value setter.
     * This field is defined as "Net international migration rate in period
     * 7/1/2010 to 6/30/2011."
     * @param rinternationalmig2011 long value equal to the new value of the
     * field.
     */
    public void setRinternationalmig2011(double rinternationalmig2011) {
        this.m_dbl_rinternationalmig2011 = rinternationalmig2011;
    } // end void
    /**
     * RDOMESTICMIG2011 field value getter.
     * This field is defined as "Net domestic migration rate in period 
     * 7/1/2010 to 6/30/2011."
     * @return long value equal to the value of the field.
     */
    public double getRdomesticmig2011() {
        return m_dbl_rdomesticmig2011;
    } // end void
    /**
     * RDOMESTICMIG2011 field value setter.
     * This field is defined as "Net domestic migration rate in period 
     * 7/1/2010 to 6/30/2011."
     * @param rdomesticmig2011 long value equal to the new value of the field.
     */
    public void setRdomesticmig2011(double rdomesticmig2011) {
        this.m_dbl_rdomesticmig2011 = rdomesticmig2011;
    } // end void
    /**
     * RNETMIG2011 field value getter.
     * This field is defined as "Net migration rate in period 7/1/2010
     * to 6/30/2011."
     * @return long value equal to the value of the field.
     */
    public double getRnetmig2011() {
        return m_dbl_rnetmig2011;
    } // end void
    /**
     * RNETMIG2011 field value getter.
     * This field is defined as "Net migration rate in period 7/1/2010
     * to 6/30/2011."
     * @param rnetmig2011 long value equal to the new value of the field.
     */
    public void setRnetmig2011(double rnetmig2011) {
        this.m_dbl_rnetmig2011 = rnetmig2011;
    } // end void
    /**
     * Record field getter.
     * @return a comma delimited string containing all the fields.
     */
    public String getFields()
    {
        return "SUMLEV, REGION, DIVISION, STATE, NAME," +
               "CENSUS2010POP, ESTIMATESBASE2010, POPESTIMATE2010," +
               "POPESTIMATE2011, NPOPCHG_2010, NPOPCHG_2011, BIRTHS2010," +
               "BIRTHS2011, DEATHS2010, DEATHS2011, NATURALINC2010," +
               "NATURALINC2011, INTERNATIONALMIG2010, INTERNATIONALMIG2011," +
               "DOMESTICMIG2010, DOMESTICMIG2011, NETMIG2010, NETMIG2011," +
               "RESIDUAL2010, RESIDUAL2011, RBIRTH2011, RDEATH2011," +
               "RNATURALINC2011, RINTERNATIONALMIG2011, RDOMESTICMIG2011," +
               "RNETMIG2011";
    } // end string
    /**
    * Override of the Object.ToString() to provide the object's
    * state. Output the data contained in
    */
    @Override
    public synchronized String toString()
    {
        return new String(this.m_str_sumlev+ "," + //0
                this.m_str_region+ "," + // 1
                this.m_str_division+ "," + // 2
                this.m_str_state+ "," + // 3
                this.m_str_name+ "," + // 4
                this.m_long_census2010pop+ "," + // 5
                this.m_long_estimatesbase2010+ "," + // 6
                this.m_long_popestimate2010+ "," + // 7
                this.m_long_popestimate2011+ "," + // 8
                this.m_long_npopchg_2010+ "," + // 9
                this.m_long_npopchg_2011+ "," + // 10
                this.m_long_births2010+ "," + // 11
                this.m_long_births2011+ "," + // 12
                this.m_long_deaths2010+ "," + // 13
                this.m_long_deaths2011+ "," + // 14
                this.m_long_naturalinc2010+ "," + // 15
                this.m_long_naturalinc2011+ "," +  // 16
                this.m_long_internationalmig2010+ "," + // 17
                this.m_long_internationalmig2011+ "," + // 18 
                this.m_long_domesticmig2010+ "," + // 19
                this.m_long_domesticmig2011+ "," + // 20
                this.m_long_netmig2010+ "," + // 21
                this.m_long_netmig2011+ "," + //22
                this.m_long_residual2010+ "," + // 23
                this.m_long_residual2011+ "," + // 24
                this.m_dbl_rbirth2011+ "," + // 25
                this.m_dbl_rdeath2011+ "," + // 26
                this.m_dbl_rnaturalinc2011+ "," + // 27
                this.m_dbl_rinternationalmig2011+ "," + // 28
                this.m_dbl_rdomesticmig2011+ "," + // 29
                this.m_dbl_rnetmig2011); // 30
    } // end override
    /**
     * Implementation of the RecordAnalytics.popPerIncr Method.
     */ 
    @Override
    public double popPerIncr(int year) {
        switch(year)
        {
              case 2010:
                      return (double)((double)m_long_npopchg_2010/(double)(m_long_popestimate2010-m_long_npopchg_2010)) *100.0;
              case 2011:
                      return (double)((double)m_long_npopchg_2010/(double)(m_long_popestimate2010-m_long_npopchg_2010)) * 100.0;
              default:
                      return 0;
        } // end switch
    } // end function

    /**
     * Implementation of the RecordAnalytics.MaxBirthPerYear method.
     */
    @Override
    public long maxBirthPerYear(int year) {
	  switch(year)
	  {
		case 2010:
			return m_long_births2010;
		case 2011:
			return m_long_births2011;
		default:
			return 0;
	  } // end switch
    } // end function
    /**
     * Implementation of the RecordAnalytics.MinBirthPerYear method.
     */
    @Override
    public long minBirthPerYear(int year) {
		 switch(year)
	  {
		case 2010:
			return m_long_births2010;
		case 2011:
			return m_long_births2011;
		default:
			return 0;
	  } // end switch
	} // end function

    /**
     * Implementation of the RecordAnalytics.MaxDeathPerYear method.
     */
    @Override
    public long maxDeathPerYear(int year) {
		 switch(year)
		  {
			case 2010:
				return m_long_deaths2010;
			case 2011:
				return m_long_deaths2011;
			default:
				return 0;
		  } // end switch
    } // end function
    /**
     * Implementation of the RecordAnalytics.MinDeathPerYear method.
     */
    @Override
    public long minDeathPerYear(int year) {
		 switch(year)
		  {
			case 2010:
				return m_long_deaths2010;
			case 2011:
				return m_long_deaths2011;
			default:
				return 0;
		  } // end switch
    } // end function
    /**
     * Comparable Interface Implementation.
     */   
    @Override
    public int compareTo(Record o) {
      
        switch(this.m_int_comparisonyear)
	{
            case 2010:
                if(this.m_long_popestimate2010 < o.getPopestimate2010() 
                        && this.m_str_sumlev=="40" &&
                        o.getSumlev() == "40")
                {
                                return -1;
                } // end if
                else if(this.m_long_popestimate2010 == o.getPopestimate2010()
                         && this.m_str_sumlev=="40" &&
                        o.getSumlev() == "40")
                {
                                return 0;
                } // end else if
                else if (this.m_str_sumlev=="40" &&
                        o.getSumlev() == "40")
                {
                                return 1;
                } // end else if
                else
                {
                    return -1;
                } // end else
            default:
                if(this.m_long_popestimate2011 < o.getPopestimate2011()
                         && this.m_str_sumlev=="40" &&
                        o.getSumlev() == "40")
                {
                                return -1;
                } // end if
                else if(this.m_long_popestimate2011 == o.getPopestimate2011()
                         && this.m_str_sumlev=="40" &&
                        o.getSumlev() == "40")
                {
                                return 0;
                } // end else if
                else if( this.m_str_sumlev=="40" &&
                        o.getSumlev() == "40")
                {
                                return 1;
                } // end else if
                else
                {
                    return -1;
                }
                
                
                
	} // end switch
      
    }  // end function
} // end class
