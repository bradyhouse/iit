/*
Copyright 2013 Brady Houseknecht

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package model;
import java.io.Serializable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * Class used to virtualize an individual 
 * Country record.
 * 
 * @author Brady Houseknecht
 */
public class Country implements Serializable {
    @NotNull(message = "Enter a valid code")
    @Size(max = 3)
    private String Code;
    @NotNull(message = "Enter a valid name")
    @Size(max = 52)
    private String Name;
    @NotNull(message = "Enter a valid continent")
    @Size(max = 35)
    private String Continent;
    @NotNull(message = "Enter a valid region")
    @Size(max = 26)
    private String Region;
    @NotNull(message = "Enter a valid surface area")
    private Float SurfaceArea;
    @Digits(integer = 6, fraction = 0)
    private Integer IndepYear;
    @Digits(integer = 11, fraction = 0)
    private Integer Population;
    private Float LifeExpectancy;
    private Float GNP;
    private Float GNPOld;
    @NotNull(message = "Enter a valid local name")
    @Size(max = 45)
    private String LocalName;
    @NotNull(message = "Enter a valid government form")
    @Size(max = 45)
    private String GovernmentForm;
    @Size(max = 60)
    private String HeadOfState;
    @Digits(integer = 11, fraction = 0)
    private Integer Capital;
    @NotNull(message = "Enter a valid Code2 value")
    @Size(max = 45)
    private String Code2;
    
    /**
     * Default class constructor
     */
    public Country() {
    } // end:Constructor
    /**
     * Overload constructor which includes parameter to initialize all
     * class attributes
     * 
     * @param Code string value equal to the code
     * @param Name string value equal to the name
     * @param Continent string value equal to the continent
     * @param Region string value equal to the region
     * @param SurfaceArea float value equal to the independence year
     * @param Population integer value equal to the population
     * @param LifeExpectancy float value equal to the life expectancy
     * @param GNP float value equal to the GNP
     * @param GNPOld float value equal to the old GNP
     * @param LocalName string value equal to the local name
     * @param GovernmentForm string value equal to the government form
     * @param HeadOfState string value equal to the head of state
     * @param Capital string value equal to the capital
     * @param Code2 string value equal to the code 2 value
     */
    public Country(String Code, String Name, String Continent, String Region, 
            Float SurfaceArea, Integer IndepYear, Integer Population, 
            Float LifeExpectancy, Float GNP, Float GNPOld, String LocalName, 
            String GovernmentForm, String HeadOfState, Integer Capital, String Code2) {
        this.Code = Code;
        this.Name = Name;
        this.Continent = Continent;
        this.Region = Region;
        this.SurfaceArea = SurfaceArea;
        this.IndepYear = IndepYear;
        this.Population = Population;
        this.LifeExpectancy = LifeExpectancy;
        this.GNP = GNP;
        this.GNPOld = GNPOld;
        this.LocalName = LocalName;
        this.GovernmentForm = GovernmentForm;
        this.HeadOfState = HeadOfState;
        this.Capital = Capital;
        this.Code2 = Code2;
    } // end:constructor
    /**
     * get the value of the code
     * 
     * @return string value equal to the code
     */
    public String getCode() {
        return Code;
    } // end:getCode
    /**
     * Set the value of code
     * 
     * @param Code string value equal to the code
     */
    public void setCode(String Code) {
        this.Code = Code;
    } // end:setCode
    /**
     * Get the value of the name
     * 
     * @return string value equal to the name
     */
    public String getName() {
        return Name;
    } // end:getName
    /**
     * Set the value of the name
     * 
     * @param Name string value equal to the name
     */
    public void setName(String Name) {
        this.Name = Name;
    } // end:setName
    /**
     * Get the value of continent
     * 
     * @return string value equal to the continent
     */
    public String getContinent() {
        return Continent;
    } // end:getContinent
    /**
     * Set the value of the continent
     * 
     * @param Continent string value equal to the continent. Valid values include:
     * 'Asia', 'Europe', 'North America', 'Africa'
     */
    public void setContinent(String Continent) {
        switch(Continent.trim().toLowerCase())
        {
            case "asia":
            case "europe":
            case "north america":
            case "africa":
                this.Continent = Continent;        
                break;
            default:
                 throw new IllegalArgumentException("Continent must be equal to 'Asia', 'Europe', 'North America', or 'Africa'");
        } // end:switch
    } // end:setContinent
    /**
     * Get the value of the region
     * 
     * @return string value equal to the region
     */
    public String getRegion() {
        return Region;
    } // end:getRegion
    /**
     * Set the value of the region
     * 
     * @param Region sring value equal to the region
     */
    public void setRegion(String Region) {
        this.Region = Region;
    } // end:setRegion
    /**
     * Get the value of the surface area
     * 
     * @return float value equal to the surface area
     */
    public Float getSurfaceArea() {
        return SurfaceArea;
    } // end:getSurfaceArea
    /**
     * Set the value of the surface area
     * 
     * @param SurfaceArea float value equal to the surface area
     */
    public void setSurfaceArea(Float SurfaceArea) {
        this.SurfaceArea = SurfaceArea;
    } // end:setSurfaceArea
    /**
     * Get the value of the independence year
     * 
     * @return integer value equal to the independence year
     */
    public Integer getIndepYear() {
        return IndepYear;
    } // end:getIndepYear
    /**
     * Set the value of the independence year
     * 
     * @param IndepYear integer equal to the independence year
     */
    public void setIndepYear(Integer IndepYear) {
        this.IndepYear = IndepYear;
    } // end:setIndepYear
    /**
     * Get the value of the population
     * 
     * @return integer value equal to the population
     */
    public Integer getPopulation() {
        return Population;
    } // end:getPopulation
    /**
     * Set the value of the population
     * 
     * @param Population integer value of the of the population. NOTE - the
     * value must be greater then zero
     */
    public void setPopulation(Integer Population) {
        if(Population <= 0) {
            throw new IllegalArgumentException("Population must be greater then zero");
        } // end:if
        this.Population = Population;
    } // end:setPopulation
    /**
     * Get the life expectancy
     * 
     * @return float value equal to life expectancy.  
     */
    public Float getLifeExpectancy() {
        return LifeExpectancy;
    } // end:getLifeExpectancy
    /**
     * Set the life expectancy
     * 
     * @param LifeExpectancy float value equal to the life expectancy.  
     * Note - the value must be null or greater then zero and less then 200.0.
     */
    public void setLifeExpectancy(Float LifeExpectancy) {
        if(null!=LifeExpectancy && ( LifeExpectancy < 0 || LifeExpectancy > 200.0))
           throw new IllegalArgumentException("Life expectancy must greater then zero and less then 200");
        this.LifeExpectancy = LifeExpectancy;
    } // end:setLifeExpectancy
    /**
     * Get the value of GNP
     * 
     * @return float value equal to GNP. 
     */
    public Float getGNP() {
        return GNP;
    } // end:getGNP
    /**
     * Set the value of GNP
     * 
     * @param GNP float value equal to GNP
     * Note - the value must be null or greater than or equal to zero
     */
    public void setGNP(Float GNP) {
        if(null!=GNP && (GNP < 0))
          throw new IllegalArgumentException("GNP must be greater then or equal to zero");
        this.GNP = GNP;
    } // end:setGNP
    /**
     * Get the old GNP
     * 
     * @return float value equal to the old GNP
     */
    public Float getGNPOld() {
        return GNPOld;
    } // end:getGNPOld
    /**
     * Set the value of old GNP
     * 
     * @param GNPOld float value equal to old GNP
     * Note - the value must be null or greater than or equal to zero
     * 
     */
    public void setGNPOld(Float GNPOld) {
        if(null!=GNPOld && (GNPOld < 0))
          throw new IllegalArgumentException("Old GNP must be greater then or equal to zero");
        this.GNPOld = GNPOld;
    } // end:setGNPOld
    /**
     * Get the value of local name
     * 
     * @return string value equal to the local name
     */
    public String getLocalName() {
        return LocalName;
    } // end:getLocalName
    /**
     * Set the value of the local name
     * 
     * @param LocalName string value equal to local name
     */
    public void setLocalName(String LocalName) {
        this.LocalName = LocalName;
    } // end:setLocalName
    /**
     * Get the government form
     * 
     * @return string value equal to the government form
     */
    public String getGovernmentForm() {
        return GovernmentForm;
    } // end:getGovernmentForm
    /**
     * Set the value of the government form
     * 
     * @param GovernmentForm string value equal to the government form
     */
    public void setGovernmentForm(String GovernmentForm) {
        this.GovernmentForm = GovernmentForm;
    } // end:setGovernmentForm
    /**
     * Get the value of the head of state
     * 
     * @return string value equal to the head of state
     */
    public String getHeadOfState() {
        return HeadOfState;
    } // end:getHeadOfState
    /**
     * Set the value of the head of state
     * 
     * @param HeadOfState string value equal to the head of state
     */
    public void setHeadOfState(String HeadOfState) {
        this.HeadOfState = HeadOfState;
    } // end:setHeadOfState
    /**
     * Get the capital
     * 
     * @return integer value equal to the capital
     */
    public Integer getCapital() {
        return Capital;
    } // end:getCapital
    /**
     * Set the value of the capital
     * 
     * @param Capital integer value equal to the capital
     */
    public void setCapital(Integer Capital) {
        this.Capital = Capital;
    } // end:setCapital
    /**
     * Get the value of code 2
     * 
     * @return string value equal to code 2
     */
    public String getCode2() {
        return Code2;
    } // end:getCode2
    /**
     * Set the value of code 2
     * 
     * @param Code2 string value equal to code 2
     */
    public void setCode2(String Code2) {
        this.Code2 = Code2;
    } // end:setCode2
} // end:country
