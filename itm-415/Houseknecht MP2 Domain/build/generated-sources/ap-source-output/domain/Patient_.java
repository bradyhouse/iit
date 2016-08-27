package domain;

import domain.Doctor;
import domain.Encounter;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-11-01T18:40:11")
@StaticMetamodel(Patient.class)
public class Patient_ { 

    public static volatile SingularAttribute<Patient, String> streetAddress;
    public static volatile SingularAttribute<Patient, String> lastName;
    public static volatile SingularAttribute<Patient, String> zip;
    public static volatile SingularAttribute<Patient, String> state;
    public static volatile SingularAttribute<Patient, String> provider;
    public static volatile SingularAttribute<Patient, String> city;
    public static volatile SingularAttribute<Patient, String> country;
    public static volatile SingularAttribute<Patient, Integer> id;
    public static volatile SingularAttribute<Patient, String> phoneNumber;
    public static volatile SingularAttribute<Patient, Doctor> primaryDoctor;
    public static volatile SingularAttribute<Patient, String> subscriberGroup;
    public static volatile SingularAttribute<Patient, String> subscriberId;
    public static volatile SingularAttribute<Patient, Date> birthDate;
    public static volatile ListAttribute<Patient, Doctor> doctors;
    public static volatile ListAttribute<Patient, Encounter> encounters;
    public static volatile SingularAttribute<Patient, String> firstName;
    public static volatile SingularAttribute<Patient, Date> createDate;

}