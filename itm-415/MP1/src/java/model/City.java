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
 * City record.
 * 
 * @author Brady Houseknecht
 */
public class City implements Serializable {
    private Integer id;
    @NotNull(message = "Enter a valid city name.")
    @Size(max = 35)
    private String name;
    @NotNull(message = "Enter a valid country code.")
    @Size(max = 3)
    private String countryCode;
    @NotNull(message = "Enter a valid district.")
    @Size(max = 20)
    private String district;
    @NotNull(message = "Enter a valid population.")
    @Digits(integer = 11, fraction = 0)
    private Integer population;
    public City() {
    }
    public City(Integer id, String name, String countryCode, String district, Integer population) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }
    /**
     * Get the value of the id
     * 
     * @return the value of the id 
     */
    public Integer getId() {
        return id;
    }
    /**
     * Set the value of the id
     * 
     * @param id new value of the id
     */
    public void setId(Integer id) {
        this.id = id;
    }   
    /**
     * Get the value of the name
     * 
     * @return value of the name
     */
    public String getName() {
        return name;
    }
    /**
     * Set the value of the name
     * 
     * @param name new value of the name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Get the value of the countryCode
     * 
     * @return value of the countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }
    /**
     * Set value of the countryCode
     * 
     * @param countryCode new value of the countryCode
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    /**
     * Get the value of the district
     * 
     * @return value of district
     */
    public String getDistrict() {
        return district;
    }
    /**
     * Set the value of the district
     * 
     * @param district new value of the district
     */
    public void setDistrict(String district) {
        this.district = district;
    }
    /**
     * Get the value of the population
     * 
     * @return the value of the population
     */
    public Integer getPopulation() {
        return population;
    }
    /**
     * Set the value of the population
     * 
     * @param population new value of the population
     */
    public void setPopulation(Integer population) {
        this.population = population;
    }
    @Override
    public String toString() {
        return "City{" + "id=" + id + ", name=" + name + ", countryCode=" + countryCode + ", district=" + district + ", population=" + population + '}';
    }  
} // end:City
