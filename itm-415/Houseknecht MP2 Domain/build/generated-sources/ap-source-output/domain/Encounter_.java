package domain;

import domain.Doctor;
import domain.Observation;
import domain.Patient;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-11-01T18:40:11")
@StaticMetamodel(Encounter.class)
public class Encounter_ { 

    public static volatile SingularAttribute<Encounter, Integer> id;
    public static volatile SingularAttribute<Encounter, Patient> patient;
    public static volatile SingularAttribute<Encounter, Date> createDate;
    public static volatile ListAttribute<Encounter, Observation> observations;
    public static volatile SingularAttribute<Encounter, Doctor> doctor;

}