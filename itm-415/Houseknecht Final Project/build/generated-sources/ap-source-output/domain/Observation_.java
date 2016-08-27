package domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-15T15:53:36")
@StaticMetamodel(Observation.class)
public class Observation_ extends BaseEntity_ {

    public static volatile SingularAttribute<Observation, String> description;
    public static volatile SingularAttribute<Observation, Boolean> IsDoctor;
    public static volatile SingularAttribute<Observation, Date> createDate;

}