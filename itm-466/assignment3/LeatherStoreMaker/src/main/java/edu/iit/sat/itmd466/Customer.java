/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd466;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Christopher
 */
@Entity
@Table(name="Customers")
@NamedQueries({
    @NamedQuery(name="Customer.findById",query="select c from Customer c where c.id = :id"),
    @NamedQuery(name="Customer.findByFirstName",query="select c from Customer c where c.firstName = :firstName"),
    @NamedQuery(name="Customer.findByLastName",query="select cln from Customer cln where cln.lastName = :lastName"),
    @NamedQuery(name="Customer.findByAll",query="select c from Customer c")
})
public class Customer extends IdTemplate implements Serializable{
    
    @OneToOne(mappedBy = "customer", cascade = CascadeType.REMOVE)
    @JoinTable(name = "Customer_Information")
    private List<CustInfo> custinfo = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(name = "Orders")
    private List<Inventory> items = new ArrayList<>();

    /**
     * Get the value of custinfo
     *
     * @return the value of custinfo
     */
    public List<CustInfo> getCustinfo() {
        return custinfo;
    }

    /**
     * Set the value of custinfo
     *
     * @param custinfo new value of custinfo
     */
    public void setCustinfo(List<CustInfo> custinfo) {
        this.custinfo = custinfo;
    }


    /**
     * Get the value of items
     *
     * @return the value of items
     */
    public List<Inventory> getItems() {
        return items;
    }

    /**
     * Set the value of items
     *
     * @param items new value of items
     */
    public void setItems(List<Inventory> items) {
        this.items = items;
    }
    
    private String firstName;
    private String lastName;

    /**
     * Get the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the value of lastName
     *
     * @param lastName new value of lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    /**
     * Get the value of firstName
     *
     * @return the value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of firstName
     *
     * @param firstName new value of firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    
    public Customer() {
    }
    
}
