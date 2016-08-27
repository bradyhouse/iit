/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd466;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 *
 * @author Christopher
 */
@Entity
@Table(name = "Orderless")
@NamedQueries({
    @NamedQuery(name="Orderless.findByAll",query="select o from Orderless o")
})
public class Orderless extends IdTemplate implements Serializable{

    @OneToMany
    
    private List<Inventory> items = new ArrayList<>();
    
    private String withoutOrder;

    /**
     * Get the value of withoutOrder
     *
     * @return the value of withoutOrder
     */
    public String getWithoutOrder() {
        return withoutOrder;
    }

    /**
     * Set the value of withoutOrder
     *
     * @param withoutOrder new value of withoutOrder
     */
    public void setWithoutOrder(String withoutOrder) {
        this.withoutOrder = withoutOrder;
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

    
    public Orderless() {
    }
    
}
