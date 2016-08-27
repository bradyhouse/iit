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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * Class which virtualizes a Patient entity.
 * 
 * @author Brady Houseknecht
 */
@Entity
@Named
@Table(name="mp3_Patient")
@NamedQuery(name="Patient.findByUsername",
        query="select p from Patient p where p.user.userName = :userName")
public class Patient extends BaseEntity implements Serializable {
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
    @Column(name = "Provider")
    private String provider;
    @Basic(optional = true)
    @Column(name = "SubscriberId")
    private String subscriberId;
    @Basic(optional = true)
    @Column(name = "SubscriberGroup")
    private String subscriberGroup;
    @OneToOne
    private Doctor primaryDoctor;
    @ManyToMany(mappedBy = "patients")
    private List<Doctor> doctors  = new ArrayList<>();;
    @OneToMany(mappedBy = "patient")
    private List<Encounter> encounters = new ArrayList<>();
    @OneToOne
    @JoinColumn(name="USERNAME")
    private User user;
    /**
     * Default class constructor
     */
    public Patient() {
    } // end:constructor
    /**
     * Overloaded class constructor
     * 
     * @param firstName string value equal to the patient's first name
     * @param lastName string value equal to the patient's last name
     * @param streetAddress string value equal to the patient's street address
     * @param city string value equal to the patient's city address
     * @param state string value equal to the patient's state address
     * @param zip string value equal to the patient's zip code
     * @param phoneNumber string value equal to the patient's phone number
     * @param birthDate date value equal to the patient's birth date
     * @param provider string value equal to the patient's insurance provider name
     * @param subscriberId string value equal to the patient's insurance provider subscriber id
     * @param subscriberGroup string value equal to the patient's insurance provider group id
     */
    public Patient(String firstName, 
            String lastName, 
            String streetAddress, 
            String city, 
            String state,
            String zip, 
            String phoneNumber, 
            Date birthDate, 
            String provider, 
            String subscriberId, 
            String subscriberGroup) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.provider = provider;
        this.subscriberId = subscriberId;
        this.subscriberGroup = subscriberGroup;
    } // end:constructor
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
     * @return date value equal to the Patient's birth date 
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
     * Provider getter
     * 
     * @return String value equal to the Patient's insurance provider
     */
    public String getProvider() {
        return provider;
    } // end:getProvider
    /**
     * Provider setter
     * 
     * @param provider String value equal to the patient's new insurance provider
     */
    public void setProvider(String provider) {
        this.provider = provider;
    } // end:setProvider
    /**
     * Insurance provider subscriber id getter
     * 
     * @return String value equal to the patient's insurance provider subscriber id
     */
    public String getSubscriberId() {
        return subscriberId;
    } // end:getSubscriberId
    /**
     * Insurance subscriber Id setter
     * 
     * @param subscriberId String value equal to the patient's insurance subscriber id
     */
    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    } // end:setSubscriberId
    /**
     * Insurance provider subscriber group getter
     * 
     * @return String value equal to the patient's insurance provider subscriber group
     */
    public String getSubscriberGroup() {
        return subscriberGroup;
    } // end:getSubscriberGroup
    /**
     * Insurance provider subscriber group setter
     * 
     * @param subscriberGroup String value equal to the patient's insurance provider subscriber group
     */
    public void setSubscriberGroup(String subscriberGroup) {
        this.subscriberGroup = subscriberGroup;
    } // end:setSubscriberGroup
    /**
     * Patient's first name getter
     * 
     * @return String value equal to the patient's first name
     */
    public String getFirstName() {
        return firstName;
    } // end:getFirstName
    /**
     * Patient's first name setter
     * 
     * @param firstName String value equal to the patient's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    } // end:setFirstName
    /**
     * Patient's last name getter
     * 
     * @return String value equal to the patient's last name 
     */
    public String getLastName() {
        return lastName;
    } // end:getLastName
    /**
     * Patient's last name setter
     * 
     * @param lastName String value equal to the patient's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    } // end:setLastName
    /**
     * Patient's street address getter
     * 
     * @return String value equal to the patient's street address
     */
    public String getStreetAddress() {
        return streetAddress;
    } // end:getStreetAddress
    /**
     * Patient's street address setter
     * 
     * @param streetAddress String value equal to the patient's address
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    } // end:setStreetAddress
    /**
     * Patient's city getter
     * 
     * @return String value equal to the patient's resident city
     */
    public String getCity() {
        return city;
    } // end:getCity
    /**
     * Patient's city setter
     * 
     * @param city String value equal to the patient's resident city
     */
    public void setCity(String city) {
        this.city = city;
    } // end:setCity
    /**
     * Patient's resident state getter
     * 
     * @return String value equal to the Patient's resident state
     */
    public String getState() {
        return state;
    } // end:getState
    /**
     * Patient's resident state setter
     * 
     * @param state String value equal to the Patient's resident state
     */
    public void setState(String state) {
        this.state = state;
    } // end:setState
    /**
     * Patient's country getter
     * 
     * @return String value equal to the Patient's resident country 
     */
    public String getCountry() {
        return country;
    } // end:getCountry
    /**
     * Patient's resident country setter
     * 
     * @param country String value equal to the Patient's resident country
     */
    public void setCountry(String country) {
        this.country = country;
    } // end:setCountry
    /**
     * Patient's zip code getter
     * 
     * @return String value equal to the patient's zip code
     */
    public String getZip() {
        return zip;
    } // end:getZip
    /**
     * Patient's zip code setter
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
     * Patient's primary doctor getter
     * 
     * @return Doctor equal to the patient's primary doctor
     */
    public Doctor getPrimaryDoctor() {
        return primaryDoctor;
    } // end:getPrimaryDoctory
    /**
     * Patient's primary doctor setter
     * 
     * @param primaryDoctor Doctor equal to the patient's primary doctor
     */
    public void setPrimaryDoctor(Doctor primaryDoctor) {
        this.primaryDoctor = primaryDoctor;
    } // end:setPrimaryDoctor
    /**
     * Patient's doctor list getter
     * 
     * @return List of the patients doctors.
     */
    public List<Doctor> getDoctors() {
        return doctors;
    } // end:getDoctors
    /**
     * Patient's doctor list setter
     * 
     * @param doctors List of the patient's doctors
     */
    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    } // end:setDoctors
    /**
     * Patient's encounter list getter
     * 
     * @return List of the Doctor's encounters
     */
    public List<Encounter> getEncounters() {
        return encounters;
    } // end:getEncounters
    /**
     * Patient's encounter list setter
     * 
     * @param encounters List of the doctor's encounters
     */
    public void setEncounters(List<Encounter> encounters) {
        this.encounters = encounters;
    } // end:setEncounters
    /**
     * Control function that can be used to add
     * a doctor's to the patient's doctor list
     * 
     * @param doctor to added to the patient's doctor list
     */
    public void addDoctor(Doctor doctor){
        if (!this.doctors.contains(doctor)) {
            this.doctors.add(doctor);
        } // end:if
        if (!doctor.getPatients().contains(this)) {
           doctor.getPatients().add(this);
        } // end:if
    } // end:addDoctor
    /**
     * Control function that can be used to 
     * add a patient initiated encounter.
     * 
     * @param encounter value equal to the patient encounter
     */
    public void addEncounter(Encounter encounter) {
        if(!this.encounters.contains(encounter)){
            this.encounters.add(encounter);
        } // end:if
        if(encounter.getPatient() != this)
        {
            encounter.setPatient(this);
        } // end:if
    } // end:addEncounter
    /**
     * User getter
     * 
     * @return user class instance
     */
    public User getUser() {
        return user;
    } // end:getUser
    /**
     * User setter
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
        return "Patient{" + "createDate=" + createDate + ", firstName=" + firstName + ", lastName=" + lastName + ", streetAddress=" + streetAddress + ", city=" + city + ", state=" + state + ", country=" + country + ", zip=" + zip + ", phoneNumber=" + phoneNumber + ", birthDate=" + birthDate + ", provider=" + provider + ", subscriberId=" + subscriberId + ", subscriberGroup=" + subscriberGroup + ", primaryDoctor=" + primaryDoctor + ", doctors=" + doctors + ", encounters=" + encounters + ", user=" + user.toString() + '}';
    } // end:toString
} // end:Patient
