/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd466;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Christopher
 */
public abstract class JPATemplateTest{
    
    private static EntityManagerFactory emf;
    protected EntityManager em;
    protected EntityTransaction tx;
    
    @BeforeClass
    public static void setUpClass(){
        emf = Persistence.createEntityManagerFactory("chernaPU");
    }
    
    @AfterClass
    public static void tearDownClass(){
        emf.close();
    }
    
    @Before
    public void setUp(){
        em = emf.createEntityManager();
        tx = em.getTransaction();
        manageTables();
    }
    
    private void manageTables(){

    tx.begin();
    
    //populate Customer
    Customer customer = new Customer();
    customer.setFirstName("Jake");
    customer.setLastName("Fonz");
    Customer customer1 = new Customer();
    customer1.setFirstName("Mary");
    customer1.setLastName("Shelley");
    Customer customer2 = new Customer();
    customer2.setFirstName("Erin");
    customer2.setLastName("Carter");
    Customer customer3 = new Customer();
    customer3.setFirstName("Amanda");
    customer3.setLastName("Neil");
    
    //populate CustInfo
    CustInfo custInfo = new CustInfo();
    custInfo.setCustAddress("777 West Ave.");
    custInfo.setCustCity("Chicago");
    custInfo.setCustState("Illinois");
    CustInfo custInfo1 = new CustInfo();
    custInfo1.setCustAddress("931 Washington Rd.");
    custInfo1.setCustCity("Montgomery");
    custInfo1.setCustState("Alabama");
    CustInfo custInfo2 = new CustInfo();
    custInfo2.setCustAddress("3789 Manchester Way");
    custInfo2.setCustCity("Olympia");
    custInfo2.setCustState("Washington");
    CustInfo custInfo3 = new CustInfo();
    custInfo3.setCustAddress("2538 South Drive");
    custInfo3.setCustCity("Jefferson City");
    custInfo3.setCustState("Missouri");
    
    //populate Inventory
    Inventory inventory = new Inventory();
    inventory.setItem("Leather Purse");
    inventory.setQuantity(1);
    inventory.setStatus("Not Pending");
    Inventory inventory1 = new Inventory();
    inventory1.setItem("Leather Wallet");
    inventory1.setQuantity(2);
    inventory1.setStatus("Not Pending");
    Inventory inventory2 = new Inventory();
    inventory2.setItem("Leather Computer Case");
    inventory2.setQuantity(3);
    inventory2.setStatus("Pending");
    Inventory inventory3 = new Inventory();
    inventory3.setItem("Leather Boots");
    inventory3.setQuantity(2);
    inventory3.setStatus("Pending");
    Inventory inventory4 = new Inventory();
    inventory4.setItem("Leather Glasses Case");
    inventory4.setQuantity(100);
    inventory4.setStatus("Not Pending");
    
    //populate Orderless
    Orderless orderless = new Orderless();
    orderless.setWithoutOrder("This item is not part of an order");
    
    //relate Customer and CustInfo
    customer.getCustinfo().add(custInfo);
    custInfo.setCustomer(customer);
    
    customer1.getCustinfo().add(custInfo1);
    custInfo1.setCustomer(customer1);
    
    customer2.getCustinfo().add(custInfo2);
    custInfo2.setCustomer(customer2);
    
    customer3.getCustinfo().add(custInfo3);
    custInfo3.setCustomer(customer3); 
        
    //relate Customer and Inventory
    customer.getItems().add(inventory);
    inventory.getCustomers().add(customer);
    
    customer1.getItems().add(inventory1);
    inventory1.getCustomers().add(customer1);
    customer1.getItems().add(inventory2);
    inventory2.getCustomers().add(customer1);
    
    customer2.getItems().add(inventory3);
    inventory3.getCustomers().add(customer2);
    
    customer3.getItems().add(inventory1);
    inventory1.getCustomers().add(customer3);
    customer3.getItems().add(inventory2);
    inventory2.getCustomers().add(customer3);
    customer3.getItems().add(inventory3);
    inventory3.getCustomers().add(customer3);
    
    //relate Inventory and Orderless
    orderless.getItems().add(inventory4);
    inventory4.setOrderless(orderless);
    
    
    em.persist(customer);
    em.persist(customer1);
    em.persist(customer2);
    em.persist(customer3);
    em.persist(custInfo);
    em.persist(custInfo1);
    em.persist(custInfo2);
    em.persist(custInfo3);
    em.persist(inventory);
    em.persist(inventory1);
    em.persist(inventory2);
    em.persist(inventory3);
    em.persist(inventory4);
    tx.commit();
    }
    
    @After
    public void tearDown(){
        em.close();
    }
    
}
