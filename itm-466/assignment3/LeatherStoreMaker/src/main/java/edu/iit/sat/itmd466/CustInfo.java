/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd466;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Christopher
 */
@Entity
@Table(name="Shipping_Address")
@NamedQueries({
    @NamedQuery(name="CustInfo.findByCustAddress",query="select ci from CustInfo ci where ci.custAddress = :custAddress"),
    @NamedQuery(name="CustInfo.findByCustCity",query="select ci from CustInfo ci where ci.custCity = :custCity"),
    @NamedQuery(name="CustInfo.findByCustState",query="select ci from CustInfo ci where ci.custState = :custState"),
    @NamedQuery(name="CustInfo.findByAll",query="select ci from CustInfo ci")
})
public class CustInfo extends IdTemplate implements Serializable {

    @OneToOne
    private Customer customer;

    /**
     * Get the value of customer
     *
     * @return the value of customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Set the value of customer
     *
     * @param customer new value of customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    
    private String custAddress;
    private String custCity;
    private String custState;

    /**
     * Get the value of custState
     *
     * @return the value of custState
     */
    public String getCustState() {
        return custState;
    }

    /**
     * Set the value of custState
     *
     * @param custState new value of custState
     */
    public void setCustState(String custState) {
        this.custState = custState;
    }


    /**
     * Get the value of custCity
     *
     * @return the value of custCity
     */
    public String getCustCity() {
        return custCity;
    }

    /**
     * Set the value of custCity
     *
     * @param custCity new value of custCity
     */
    public void setCustCity(String custCity) {
        this.custCity = custCity;
    }


    /**
     * Get the value of custAddress
     *
     * @return the value of custAddress
     */
    public String getCustAddress() {
        return custAddress;
    }

    /**
     * Set the value of custAddress
     *
     * @param custAddress new value of custAddress
     */
    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    
    public CustInfo() {
    }
    
}
