package domain;

import domain.Country;
import domain.CountrylanguagePK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-10-16T17:29:40")
@StaticMetamodel(Countrylanguage.class)
public class Countrylanguage_ { 

    public static volatile SingularAttribute<Countrylanguage, Float> percentage;
    public static volatile SingularAttribute<Countrylanguage, String> isOfficial;
    public static volatile SingularAttribute<Countrylanguage, CountrylanguagePK> countrylanguagePK;
    public static volatile SingularAttribute<Countrylanguage, Country> country;

}