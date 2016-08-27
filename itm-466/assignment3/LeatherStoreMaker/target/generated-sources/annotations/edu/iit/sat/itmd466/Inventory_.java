package edu.iit.sat.itmd466;

import edu.iit.sat.itmd466.Customer;
import edu.iit.sat.itmd466.Orderless;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-10T19:23:45")
@StaticMetamodel(Inventory.class)
public class Inventory_ extends IdTemplate_ {

    public static volatile SingularAttribute<Inventory, String> item;
    public static volatile SingularAttribute<Inventory, Double> quantity;
    public static volatile SingularAttribute<Inventory, Orderless> orderless;
    public static volatile ListAttribute<Inventory, Customer> customers;
    public static volatile SingularAttribute<Inventory, String> status;

}