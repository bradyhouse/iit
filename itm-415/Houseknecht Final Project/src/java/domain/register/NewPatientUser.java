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
package domain.register;

import domain.Patient;
import java.util.Date;
import javax.inject.Named;

/**
 * Simple class used to virtualize
 * the authentication attributes
 * of a new user.
 * 
 * @author Brady House
 */
@Named
public class NewPatientUser {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String zip;
    private String phoneNumber;
    private Date birthDate;
    private String provider;
    private String subscriberId;
    private String subscriberGroup;
     
     private Patient patient;
    /**
     * Default class constructor.
     */  
    public NewPatientUser() {
        this.userName = "";
        this.password = "";
        this.patient = new Patient();
    } // end:constructor
    /**
     * Overloaded constructor.
     * 
     * @param userName string value equal to the username.
     * @param password string value equal to the password.
     */
    public NewPatientUser(String userName, String password) {
       this.userName = userName;
       this.password = password;
       this.patient = new Patient();
    }  // end:constructor   
    /**
     * UserName getter.
     * 
     * @return string value equal to the UserName
     */
    public String getUserName() {
        return userName;
    } // end:getUserName
    /**
     * UserName setter.
     * 
     * @param username string value equal to the new UserName value.
     */
    public void setUserName(String username) {
        this.userName = username;
    } // end:setUserName
    /**
     * Password getter.
     * 
     * @return string value equal to the Password.
     */
    public String getPassword() {
        return password;
    } // end:getPassword
    /**
     * Setter used to assign the user's password.
     * 
     * @param password string value equal to the user's password
     */
    public void setPassword(String password) {
        this.password = password;
    } // end:setPassword    
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
} // end:NewPatientUser
