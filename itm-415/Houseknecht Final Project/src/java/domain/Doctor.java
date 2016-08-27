/*
Copyright 2013 Brady Houseknecht

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package domain;

import domain.security.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class which virtualizes a Doctor entity.
 * 
 * @author Brady Houseknecht
 */
@Entity
@Table(name="mp3_Doctor")
@NamedQuery(name="Doctor.findByUsername",
        query="select d from Doctor d where d.user.userName = :userName")
@Named
public class Doctor extends BaseEntity implements Serializable {
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Basic(optional = false)
    @Column(name = "FirstName")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "LastName")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "StreetAddress")
    private String streetAddress;
    @Basic(optional = false)
    @Column(name = "City")
    private String city;
    @Basic(optional = false)
    @Column(name = "State")
    private String state;
    @Basic(optional = false)
    @Column(name = "Country")
    private String country;
    @Basic(optional = false)
    @Column(name = "Zip")
    private String zip;
    @Basic(optional = false)
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    @Basic(optional = false)
    @Column(name = "BirthDate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Basic(optional = true)
    @Column(name = "Speciality")
    private String speciality;
    @ManyToMany
    @JoinTable(name="mp3_DoctorLink", 
            joinColumns=@JoinColumn(name="Doctor_ID"),
            inverseJoinColumns=@JoinColumn(name="Patient_ID"))
    private List<Patient> patients = new ArrayList<>();
    @OneToMany(mappedBy = "doctor")
    private List<Encounter> encounters = new ArrayList<>();
    @OneToOne
    @JoinColumn(name="USERNAME")
    private User user;
    /**
     * Default class constructor
     */
    public Doctor() {
    } // end:constructor  
    /**
     * Overloaded class constructor.
     * 
     * @param firstName String value equal to the Doctor's first name
     * @param lastName String value equal to the Doctor's last name
     * @param streetAddress String value equal to the Doctor's street address
     * @param city String value equal to the Doctor's city address
     * @param state String value equal to the Doctor's state address
     * @param zip String value equal to the Doctor's zip code
     * @param phoneNumber String value equal to the Doctor's phone number
     * @param birthDate Date value equal to the Doctor's birth date
     * @param speciality String value equal to the Doctor's speciality
     */
    public Doctor(String firstName, String lastName, 
            String streetAddress, String city, 
            String state, String zip, String phoneNumber, 
            Date birthDate, String speciality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.speciality = speciality;
    } // end:Constructor
    /**
     * CreateDate getter
     * 
     * @return Date value equal to CreateDate
     */
    public Date getCreateDate() {
        return createDate;
    } // end:getCreateDate
    /**
     * CreateDate setter
     * @param createDate Date value equal to the new CreateDave value 
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    } // end:setCreateDate
    /**
     * BirthDate getter
     * 
     * @return date value equal to the Doctor's birth date 
     */
    public Date getBirthDate() {
        return birthDate;
    } // end:getBirthDate
    /**
     * BirthDate setter
     * 
     * @param birthDate Date value equal to the new patient birth date 
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    } // end:setBirthDate
    /**
     * Doctor's speciality getter
     * 
     * @return String value equal to the Doctor's insurance provider
     */
    public String getSpeciality() {
        return speciality;
    } // end:getSpeciality
    /**
     * Doctor's speciality setter
     * 
     * @param speciality String value equal to the doctor's speciality
     */
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    } // end:setSpeciality
    /**
     * Doctor's first name getter
     * 
     * @return String value equal to the patient's first name
     */
    public String getFirstName() {
        return firstName;
    } // end:getFirstName
    /**
     * Doctor's first name setter
     * 
     * @param firstName String value equal to the patient's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    } // end:setFirstName
    /**
     * Doctor's last name getter
     * 
     * @return String value equal to the patient's last name 
     */
    public String getLastName() {
        return lastName;
    } // end:getLastName
    /**
     * Doctor's last name setter
     * 
     * @param lastName String value equal to the patient's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    } // end:setLastName
    /**
     * Doctor's street address getter
     * 
     * @return String value equal to the patient's street address
     */
    public String getStreetAddress() {
        return streetAddress;
    } // end:getStreetAddress
    /**
     * Doctor's street address setter
     * 
     * @param streetAddress String value equal to the patient's address
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    } // end:setStreetAddress
    /**
     * Doctor's city getter
     * 
     * @return String value equal to the patient's resident city
     */
    public String getCity() {
        return city;
    } // end:getCity
    /**
     * Doctor's city setter
     * 
     * @param city String value equal to the patient's resident city
     */
    public void setCity(String city) {
        this.city = city;
    } // end:setCity
    /**
     * Doctor's resident state getter
     * 
     * @return String value equal to the Doctor's resident state
     */
    public String getState() {
        return state;
    } // end:getState
    /**
     * Doctor's resident state setter
     * 
     * @param state String value equal to the Doctor's resident state
     */
    public void setState(String state) {
        this.state = state;
    } // end:setState
    /**
     * Doctor's country getter
     * 
     * @return String value equal to the Doctor's resident country 
     */
    public String getCountry() {
        return country;
    } // end:getCountry
    /**
     * Doctor's country setter
     * 
     * @param country String value equal to the Doctor's resident country
     */
    public void setCountry(String country) {
        this.country = country;
    } // end:setCountry
    /**
     * Doctor's zip code getter
     * 
     * @return String value equal to the patient's zip code
     */
    public String getZip() {
        return zip;
    } // end:getZip
    /**
     * Doctor's zip code setter
     * 
     * @param zip String value equal to the patient's zip code
     */
    public void setZip(String zip) {
        this.zip = zip;
    } // end:setZip
    /**
     * Patient phone number getter
     * 
     * @return String value equal to the patient's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    } // end:getPhoneNumber
    /**
     * Patient phone number setter
     * 
     * @param phoneNumber String value equal to the patient's phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    } // end:setPhoneNumber
    /**
     * Doctor's patients list getter
     * 
     * @return a list of the Doctor's patients 
     */
    public List<Patient> getPatients() {
        return patients;
    } // end:getPatients
    /**
     * Doctor's patient list setter
     * 
     * @param patients List of Doctor's patients
     */
    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    } // end:setPatients
    /**
     * Doctor's encounter list getter
     * 
     * @return List of the Doctor's encounters
     */
    public List<Encounter> getEncounters() {
        return encounters;
    } // end:getEncounters
    /**
     * Doctor's encounter list setter
     * 
     * @param encounters List of the doctor's encounters
     */
    public void setEncounters(List<Encounter> encounters) {
        this.encounters = encounters;
    } // end:setEncounters
    /**
     * Control function that can be used to add
     * a patient's to the doctor's patient list
     * 
     * @param patient to be added to the doctor's list of patients
     */
    public void addPatient(Patient patient) {
        if (!this.patients.contains(patient)) {
            this.patients.add(patient);
        } // end:if
        if (!patient.getDoctors().contains(this)) {
            patient.getDoctors().add(this);
        } // end:if
    } // end:addPatient
    /**
     * Control function that can be used to 
     * add a doctor initiated encounter.
     * 
     * @param encounter value to the doctor encounter
     */
    public void addEncounter(Encounter encounter) {
        if(!this.encounters.contains(encounter)){
            this.encounters.add(encounter);
        } // end:if
        if(encounter.getDoctor() != this)
        {
            encounter.setDoctor(this);
        } // end:if
    } // end:addEncounter
    /**
     * Control function used to persist the object
     * using the Houseknecht_MP2_DomainPU project
     * persistence configuration.
     * 
     * @param object to be persisted
     */
    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Houseknecht_MP2_DomainPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    } // end:persist
    /**
     * User getter.
     * 
     * @return user class instance
     */
    public User getUser() {
        return user;
    } // end:getUser
    /**
     * User setter.
     * 
     * @param user class instance
     */
    public void setUser(User user) {
        this.user = user;
    } // end:setUser
    /**
     * Override toString. Returns a comma 
     * delimited representation of the class
     * attributes.
     * 
     * @return comma delimited string of all class attributes
     */
    @Override
    public String toString() {
        return "Doctor{" + "createDate=" + createDate + ", firstName=" + firstName + ", lastName=" + lastName + ", streetAddress=" + streetAddress + ", city=" + city + ", state=" + state + ", country=" + country + ", zip=" + zip + ", phoneNumber=" + phoneNumber + ", birthDate=" + birthDate + ", speciality=" + speciality + ", patients=" + patients + ", encounters=" + encounters + ", user=" + user.toString() + '}';
    }
} // end:Doctor
