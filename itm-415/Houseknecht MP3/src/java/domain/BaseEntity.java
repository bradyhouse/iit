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

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.*;
/**
 * Mapped super class defining attributes
 * shared by all domain entity classes.
 * 
 * @author Brady House
 */
@MappedSuperclass   
public class BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    @Id
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date activityDate;
    
    /**
     * Id Getter
     * @return 
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
     *
     * @return
     */
    public Date getActivityDate() {
        return activityDate;
    } // end:getActivityDate
    
    /**
     * Control function triggered 
     * prior to persistence, update
     * or removal, which updates the 
     * activityDate property with
     * the current timestamp on the
     * system.
     */
    @PrePersist
    @PreUpdate
    @PreRemove
    public void recordActivity()
    {
        this.activityDate = new Date();
    } // end:recordActivity
    
    /**
     *
     * @param activityDate
     */
    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    } // end:setActivityDate
} // end:BaseEntity
