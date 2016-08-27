package edu.iit.sat.itmd466;

import edu.iit.sat.itmd466.CustInfo;
import edu.iit.sat.itmd466.Inventory;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-10T19:23:45")
@StaticMetamodel(Customer.class)
public class Customer_ extends IdTemplate_ {

    public static volatile SingularAttribute<Customer, String> firstName;
    public static volatile SingularAttribute<Customer, String> lastName;
    public static volatile ListAttribute<Customer, Inventory> items;
    public static volatile ListAttribute<Customer, CustInfo> custinfo;

}