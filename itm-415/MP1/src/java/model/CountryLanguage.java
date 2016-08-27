/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * Class used to virtualize an individual 
 * CountryLanguage record.
 * 
 * @author Brady Houseknecht
 */
public class CountryLanguage {
    
    @NotNull(message = "Enter a valid country code.")
    @Size(max = 3)
    private String CountryCode;
    @NotNull(message = "Enter a valid language.")
    @Size(max = 30)
    private String Language;
    private Boolean IsOfficial;
    private Double Percentage;    
    /**
     * Default class constructor.
     */
    public CountryLanguage() {
    } // end:constructor
    /**
     * Overloaded constructor
     * 
     * @param CountryCode string value equal to the country code
     * @param Language string value equal to the language
     * @param IsOfficial boolean value indicating whether this is official language
     * @param Percentage double value equal to the percentage
     */
    public CountryLanguage(String CountryCode, String Language, Boolean IsOfficial, Double Percentage) {
        this.CountryCode = CountryCode;
        this.Language = Language;
        this.IsOfficial = IsOfficial;
        this.Percentage = Percentage;
    } // end:constructor
    /**
     * Get the value of the country code
     * 
     * @return string value equal to the country code
     */
    public String getCountryCode() {
        return CountryCode;
    } // end:getCountryCode
    /**
     * Set the value of the country code
     * 
     * @param CountryCode string value equal to the country code
     */
    public void setCountryCode(String CountryCode) {
        this.CountryCode = CountryCode;
    } // end:setCountryCode
    /**
     * Get the language
     * @return string value equal to the language
     */
    public String getLanguage() {
        return Language;
    } // end:getLanguage
    /**
     * Set the value of the language
     * 
     * @param Language string value equal to the language
     */
    public void setLanguage(String Language) {
        this.Language = Language;
    } // end:setLanguage
    /**
     * Get the value of IsOfficial
     * 
     * @return boolean value equal to IsOfficial
     */
    public Boolean getIsOfficial() {
        return IsOfficial;
    } // end:getIsOfficial
    /**
     * Set the value of IsOfficial
     * 
     * @param IsOfficial boolean value equal to IsOffical
     */
    public void setIsOfficial(Boolean IsOfficial) {
        this.IsOfficial = IsOfficial;
    } // end:setIsOfficial
    /**
     * Get the value of Percentage
     * 
     * @return double value equal to the Percentage
     */
    public Double getPercentage() {
        return Percentage;
    } // end:getPercentage
    /**
     * Set percentage value
     * 
     * @param Percentage new value of the percentage
     * Note - the value must be greater then zero and less then 100
     */
    public void setPercentage(Double Percentage) {
        if(Percentage < 0 || Percentage > 100)
           throw new IllegalArgumentException("The percentage must greater then zero and less then or equal to 100 percent");
        this.Percentage = Percentage;
    } // end:setPercentage
} // end:CountryLanguage
