package domain;

import domain.Encounter;
import domain.Patient;
import domain.security.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-04T21:16:45")
@StaticMetamodel(Doctor.class)
public class Doctor_ extends BaseEntity_ {

    public static volatile SingularAttribute<Doctor, String> streetAddress;
    public static volatile SingularAttribute<Doctor, String> zip;
    public static volatile SingularAttribute<Doctor, String> lastName;
    public static volatile SingularAttribute<Doctor, String> speciality;
    public static volatile SingularAttribute<Doctor, String> state;
    public static volatile SingularAttribute<Doctor, String> city;
    public static volatile SingularAttribute<Doctor, String> country;
    public static volatile ListAttribute<Doctor, Patient> patients;
    public static volatile SingularAttribute<Doctor, String> phoneNumber;
    public static volatile SingularAttribute<Doctor, Date> birthDate;
    public static volatile ListAttribute<Doctor, Encounter> encounters;
    public static volatile SingularAttribute<Doctor, String> firstName;
    public static volatile SingularAttribute<Doctor, Date> createDate;
    public static volatile SingularAttribute<Doctor, User> user;

}