/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd466;

import org.junit.Test;

/**
 *
 * @author Christopher
 */
public class CustomerTest extends JPATemplateTest{
    
    public CustomerTest() {
    }
    
    @Test
    public void Confirm() {
    tx.begin();
    System.out.print("\nDatabase built\n\n");
    tx.commit();
    }
    
}
