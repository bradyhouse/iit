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
package domain.ejb;
import domain.Doctor;
import domain.Patient;
import domain.Encounter;
import domain.Observation;
import domain.ejb.security.GroupBean;
import domain.ejb.security.UserBean;
import domain.security.Group;
import domain.security.User;
import java.util.Date;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Named;

/**
 * Singleton Session bean used to populate the 
 * configured database with test data on
 * startup of the application.
 * 
 * @author Brady House
 */
@Singleton
@Startup
@Named
public class DataInjectorBean {
    
    @EJB
    private GroupBean groupBean;
    @EJB
    private UserBean userBean;
    @EJB
    private DoctorBean doctorBean;
    @EJB
    private PatientBean patientBean;
    @EJB
    private EncounterBean encounterBean;
    @EJB
    private ObservationBean observationBean;
   
    private static final Logger log = Logger.getLogger(DataInjectorBean.class.getName());

    /**
     * Control function intended
     * to run after construction
     * in order to "inject" 
     * data into the database.
     */
    @PostConstruct
    private void inject(){
        
        /// Add Groups
        Group _DoctorGroup = new Group("DOCTORS", "Members of this group are doctors");
        Group _PatientGroup = new Group("PATIENTS", "Members of this group are patients");
        this.groupBean.create(_DoctorGroup);
        this.groupBean.create(_PatientGroup);
        /// Add Users
        User  _UserDoctorJack = new User("juphill1", "12345");
        _UserDoctorJack.addGroup(_DoctorGroup);
        User  _UserPatientJill = new User("juphill2", "12345");
        _UserPatientJill.addGroup(_PatientGroup);
        this.userBean.create(_UserDoctorJack);
        this.userBean.create(_UserPatientJill);
        /// Add Doctor
        Doctor _DoctorJack = new Doctor("Jack", 
                "McHill", "FetchWater Ave. Apt 123", "Chicago", 
                "Illinois",
                "60605", 
                "312-123-4567", 
                new Date("01/01/1970"), 
                "Family Medicine");
        _DoctorJack.setUser(_UserDoctorJack);
        this.doctorBean.create(_DoctorJack);
        Patient _PatientJill = new Patient("Jill", "UpHill", 
                "FetchWater Ave. Apt 123",
                "Chicago",
                "Illinois",
                "60605",
                "312-123-4567",
                new Date("01/02/1969"),
                "Fairytale Shield",
                "1",
                "P12345");
        _PatientJill.setUser(_UserPatientJill);
        _PatientJill.addDoctor(_DoctorJack);
        this.patientBean.create(_PatientJill);
        // Add Observations
        Observation _DoctorJackObsEncounterWithJill = new Observation("Distracted by thirst during appointment.", true);
        Observation _PatientJillObsAboutEncounterWithJack = new Observation("Where's the pale of water!!", false);
        this.observationBean.create(_DoctorJackObsEncounterWithJill);
        this.observationBean.create(_PatientJillObsAboutEncounterWithJack);
        // Add Encounter
        Encounter _JackAndJillEncounter = new Encounter(_DoctorJack, _PatientJill);
        _JackAndJillEncounter.AddObservation(_DoctorJackObsEncounterWithJill);
        _JackAndJillEncounter.AddObservation(_PatientJillObsAboutEncounterWithJack);
        this.encounterBean.create(_JackAndJillEncounter);
        _DoctorJack.addEncounter(_JackAndJillEncounter);
        _PatientJill.addEncounter(_JackAndJillEncounter);
        
    } // end:inject
} // end:DataInjectorBean
