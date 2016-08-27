package domain;

import domain.Country;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-10-16T17:29:40")
@StaticMetamodel(City.class)
public class City_ { 

    public static volatile SingularAttribute<City, Integer> id;
    public static volatile SingularAttribute<City, String> name;
    public static volatile SingularAttribute<City, Country> countryCode;
    public static volatile SingularAttribute<City, String> district;
    public static volatile SingularAttribute<City, Integer> population;

}