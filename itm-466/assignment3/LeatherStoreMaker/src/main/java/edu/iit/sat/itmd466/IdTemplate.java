/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd466;

import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Christopher
 */
@MappedSuperclass
public class IdTemplate {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    
    @PreUpdate
    @PrePersist
    private void setLastModified(){
        lastModified = GregorianCalendar.getInstance().getTime();
    }
    
    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the value of lastModified
     *
     * @return the value of lastModified
     */
    public Date getLastModified() {
        return lastModified;
    }

    /**
     * Set the value of lastModified
     *
     * @param lastModified new value of lastModified
     */
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }


    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Long id) {
        this.id = id;
    }

    
    public IdTemplate() {
    }
    
}
