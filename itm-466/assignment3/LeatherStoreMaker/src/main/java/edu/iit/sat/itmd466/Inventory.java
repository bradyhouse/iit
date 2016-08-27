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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Christopher
 */
@Entity
@Table(name="Inventory")
@NamedQueries({
    @NamedQuery(name="Inventory.findById",query="select inv from Inventory inv where inv.id = :id"),
    @NamedQuery(name="Inventory.findByItem",query="select inv from Inventory inv where inv.item = :item"),
    @NamedQuery(name="Inventory.findByQuantity",query="select inv from Inventory inv where inv.quantity = :quantity"),
    @NamedQuery(name="Inventory.findByStatus",query="select inv from Inventory inv where inv.status = :status"),
    @NamedQuery(name="Inventory.findByAll",query="select inv from Inventory inv")
})
public class Inventory extends IdTemplate implements Serializable{
    
    @ManyToMany(mappedBy = "items", cascade = CascadeType.PERSIST)
    private List<Customer> customers = new ArrayList<>();
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinTable(name = "Unselected_Items")
    private Orderless orderless;

    /**
     * Get the value of orderless
     *
     * @return the value of orderless
     */
    public Orderless getOrderless() {
        return orderless;
    }

    /**
     * Set the value of orderless
     *
     * @param orderless new value of orderless
     */
    public void setOrderless(Orderless orderless) {
        this.orderless = orderless;
    }

    
    /**
     * Get the value of customers
     *
     * @return the value of customers
     */
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * Set the value of customers
     *
     * @param customers new value of customers
     */
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    
    private String item;
    private double quantity;
    private String status;

    /**
     * Get the value of status
     *
     * @return the value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the value of status
     *
     * @param status new value of status
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * Get the value of quantity
     *
     * @return the value of quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Set the value of quantity
     *
     * @param quantity new value of quantity
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }


    /**
     * Get the value of item
     *
     * @return the value of item
     */
    public String getItem() {
        return item;
    }

    /**
     * Set the value of item
     *
     * @param item new value of item
     */
    public void setItem(String item) {
        this.item = item;
    }

    
    public Inventory() {
    }
    
}
