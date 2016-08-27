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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class which virtualizes an Observation entity.
 *
 * @author Brady Houseknecht
 */
@Entity
public class Observation implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    @Id
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Basic(optional = true)
    @Column(name = "Description")
    private String description;  
    /**
     * Default class constructor.
     */
    public Observation() {
    } // end:constructor
    /**
     * Overloaded constructor
     * 
     * @param createDate date value equal to the observation creation date
     * @param description String value equal to the observation description
     */
    public Observation(Date createDate, String description) {
        this.createDate = createDate;
        this.description = description;
    } // end:constructor
    /**
     * Id Getter
     */
    public Integer getId() {
        return id;
    } // end:getId
    /**
     * Id Setter
     * @param id Integer value equal to the new id.
     */
    public void setId(Integer id) {
        this.id = id;
    } // end:setId
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
     * Override toString. Returns a comma 
     * delimited representation of the class
     * attributes.
     * 
     * @return comma delimited string of all class attributes
     */
    @Override
    public String toString() {
        return "Observation{" + "id=" + id + 
                ", createDate=" + createDate + 
                ", description=" + description + '}';
    } // end:toString

    
} // end:Observation
