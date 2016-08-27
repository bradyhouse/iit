package edu.iit.sat.itmd466;

import edu.iit.sat.itmd466.Customer;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-10T19:23:45")
@StaticMetamodel(CustInfo.class)
public class CustInfo_ extends IdTemplate_ {

    public static volatile SingularAttribute<CustInfo, String> custCity;
    public static volatile SingularAttribute<CustInfo, String> custAddress;
    public static volatile SingularAttribute<CustInfo, String> custState;
    public static volatile SingularAttribute<CustInfo, Customer> customer;

}