package edu.iit.sat.itmd466;

import edu.iit.sat.itmd466.Inventory;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-10T19:23:45")
@StaticMetamodel(Orderless.class)
public class Orderless_ extends IdTemplate_ {

    public static volatile SingularAttribute<Orderless, String> withoutOrder;
    public static volatile ListAttribute<Orderless, Inventory> items;

}