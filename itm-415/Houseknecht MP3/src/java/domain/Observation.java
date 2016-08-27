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

package domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class which virtualizes an Observation entity.
 *
 * @author Brady Houseknecht
 */
@Entity
@Table(name="mp3_Observation")
public class Observation extends BaseEntity implements Serializable {
   @Temporal(TemporalType.DATE)
    private Date createDate;
    @Basic(optional = true)
    @Column(name = "Description")
    private String description;  
    @Basic(optional= true)
    @Column(name="IsDoctor")
    private Boolean IsDoctor;
    
    /**
     * Default class constructor.
     */
    public Observation() {
    } // end:constructor
    /**
     * Overloaded constructor
     * 
     * @param description String value equal to the observation description
     * @param isDoctor boolean value equal to the isDoctor flag
     */
    public Observation(String description, boolean isDoctor) {
        this.createDate = new Date();
        this.description = description;
        this.IsDoctor = isDoctor;
    } // end:constructor
  
    /**
     * CreateDate getter
     * 
     * @return Date value equal to CreateDate
     */
    public Date getCreateDate() {
        return createDate;
    } // end:getCreateDate
    /**
     * CreateDate setter
     * @param createDate Date value equal to the new CreateDave value 
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    } // end:setCreateDate
    /**
     * Observation description getter
     * 
     * @return String value equal to the Observation description
     */
    public String getDescription() {
        return description;
    } // end:getDescription
    /**
     * Observation description setter
     * 
     * @param description String value equal to the observation's description
     */
    public void setDescription(String description) {
        this.description = description;
    } // end:setDescription
    /**
     * IsDoctor getter
     * 
     * @return boolean value equal to IsDoctor
     */
    public Boolean getIsDoctor() {
        return IsDoctor;
    } // end:getIsDoctor
    /**
     * IsDoctor setter
     * 
     * @param IsDoctor boolean value equal to the IsDoctor 
     */
    public void setIsDoctor(Boolean IsDoctor) {
        this.IsDoctor = IsDoctor;
    } // end:setIsDoctor
    
    /**
     * Override toString. Returns a comma 
     * delimited representation of the class
     * attributes.
     * 
     * @return comma delimited string of all class attributes
     */
    @Override
    public String toString() {
        return "Observation{" + "createDate=" + createDate + ", description=" + description + '}';
    } // end:toString

  
} // end:Observation
