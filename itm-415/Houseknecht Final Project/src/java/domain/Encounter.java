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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * A class which virtualizes an Encounter entity.
 * 
 * @author Brady Houseknecht
 */
@Entity
@Table(name="mp3_Encounter")
public class Encounter extends BaseEntity implements Serializable {
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @ManyToMany
     @JoinTable(name="mp3_EncounterLink", 
            joinColumns=@JoinColumn(name="Encounter_ID"),
            inverseJoinColumns=@JoinColumn(name="Observation_ID"))
    private List<Observation> observations = new ArrayList<>();
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Patient patient;
    /**
     * Default class constructor
     */
    public Encounter() {
    } // end:constructor
    /**
     * Overloaded class constructor
     * 
     * @param doctor class instance
     * @param patient class instance
     */
    public Encounter(Doctor doctor, Patient patient) {
        this.doctor = doctor;
        this.patient = patient;
        this.createDate = new Date();
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
     * Encounter observation list getter
     * 
     * @return List of observations for the encounter
     */
    public List<Observation> getObservations() {
        return observations;
    } // end:getObservations
    /**
     * Encounter observation list setter
     * 
     * @param observations List of observations for the encounter
     */
    public void setObservations(List<Observation> observations) {
        this.observations = observations;
    } // end:setObservations
    /**
     * Control function that can be used to add an
     * observation to the encounter.
     * 
     * @param observation equal to the observation to be added
     */
    public void AddObservation(Observation observation)
    {
        if(!this.observations.contains(observation)){
            this.observations.add(observation);
        } // end:if
    } // end:AddObservation
    /**
     * Encounter doctor getter
     * 
     * @return Doctor equal to the encounter doctor
     */
    public Doctor getDoctor() {
        return doctor;
    } // end:getDoctor
    /**
     * Encounter doctor setter
     * 
     * @param doctor equal to the encounter doctor
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    } // end:setDoctor
    /**
     * Encounter patient getter
     * 
     * @return Patient equal to the encounter patient
     */
    public Patient getPatient() {
        return patient;
    } // end:getPatient
    /**
     * Encounter patient setter
     * 
     * @param patient equal to the encounter patient
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    } // end:setPatient
    /**
     * Override toString. Returns a comma 
     * delimited representation of the class
     * attributes.
     * 
     * @return comma delimited string of all class attributes
     */
   @Override
    public String toString() {
        return "Encounter{" + "createDate=" + createDate + ", observations=" + observations + ", doctor=" + doctor + ", patient=" + patient + '}';
    } // end:toString
} // end:Encounter
