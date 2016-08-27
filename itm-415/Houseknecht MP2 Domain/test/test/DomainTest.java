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

package test;

import domain.Doctor;
import domain.Encounter;
import domain.Observation;
import domain.Patient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test case that can be used to initialize and verify
 * the Houseknecht_itmd4515 database based on the
 * entity model class defined in the "Houseknecht MP2 Domain"
 * project.
 * 
 * @author Brady Houseknecht
 */
public class DomainTest {
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Houseknecht_MP2_DomainPU");
    protected static EntityManager em;
    protected static EntityTransaction tx;
    /**
     * Class constructor
     */
    public DomainTest() {
    } // end:constructor
    @BeforeClass
    public static void setUpClass() {
    } // end:setUpClass
    @AfterClass
    public static void tearDownClass() {
    } // end:tearDownClass
    @Before
    public void setUp() throws ParseException {
        em = emf.createEntityManager();
        tx = em.getTransaction();
        SimpleDateFormat _formatter = new SimpleDateFormat("MM/dd/yyyy");
        /**
         * Add Doctor 1
         */
        Doctor _d1 = new Doctor();
        _d1.setFirstName("Bob");
        _d1.setLastName("Gandolf");
        _d1.setStreetAddress("1405 South Michigan Ave.");
        _d1.setCity("Chicago");
        _d1.setState("IL");
        _d1.setCountry("US");
        _d1.setZip("60605");
        _d1.setPhoneNumber("312-504-5133");
        _d1.setSpeciality("Ear Nose and Throat");
        _d1.setCreateDate(new Date());
        _d1.setBirthDate(_formatter.parse("01/01/1967"));
        /**
         * Add Patient 1 - Bilbo Baggins
         */ 
        Patient _p1 = new Patient();
        _p1.setCreateDate(new Date());
        _p1.setBirthDate(_formatter.parse("01/01/1801"));
        _p1.setStreetAddress("1305 South Michigan Ave");
        _p1.setFirstName("Bilbo");
        _p1.setLastName("Baggins");
        _p1.setCity("Chicago");
        _p1.setZip("12345");
        _p1.setState("IL");
        _p1.setCountry("US");
        _p1.setPhoneNumber("312-111-1111");
        _p1.setProvider("Middle Earth Healthcare");
        _p1.setSubscriberGroup("HOBBIT1234R");
        _p1.setPrimaryDoctor(_d1);
        _p1.addDoctor(_d1);
        
        /**
         * Add a patient encounter
         */
         Encounter _enc1 = new Encounter();
         _enc1.setCreateDate(new Date());
         _enc1.setDoctor(_d1);
         _enc1.setPatient(_p1);
         
         /**
          * Add an observation to the
          * encounter
          */
         Observation _ob1 = new Observation();
         _ob1.setDescription("Sat in the waiting room forever!");
         _ob1.setCreateDate(new Date());
         _enc1.AddObservation(_ob1);
         _p1.addEncounter(_enc1);
         
        tx.begin();
        em.persist(_ob1);
        em.persist(_enc1);
        em.persist(_d1);
        em.persist(_p1);
        tx.commit();
    } // end:setUp
    @After
    public void tearDown() {
          em.close();
    } // end:tearDown
    @Test
    public void runTest() throws ParseException {
                
        SimpleDateFormat _formatter = new SimpleDateFormat("MM/dd/yyyy");
        TypedQuery<Doctor> _query = em.createQuery("select d from Doctor d where d.lastName = 'Gandolf'", Doctor.class);
        Doctor _d1 = _query.getSingleResult();
        assertNotNull(_d1);
        System.out.println("Doctor: " + _d1.getFirstName() +  " " + _d1.getLastName());

        List<Patient> _d1Patients = _d1.getPatients();
        for (Patient _d1Patient : _d1Patients) {
            System.out.println("\t" + _d1Patient.getFirstName() + " " + _d1Patient.getLastName());
            for (Encounter _encounter : _d1Patient.getEncounters()) {
                System.out.println("\t\tEncounter on "
                        + _encounter.getCreateDate()
                        + " with "
                        + "doctor " + _encounter.getDoctor().getFirstName() + " " + _encounter.getDoctor().getLastName());
                 for(Observation _obs : _encounter.getObservations())
                 {
                     System.out.println("\t\tPatient observed \"" +
                             _obs.getDescription() + "\""); 
                 } // end:for
            } // end:for
        } // end:for
        
        tx.begin();
        TypedQuery<Patient> _petQuery = em.createQuery("select p from Patient p where p.lastName = 'Baggins'", Patient.class);
        Patient _patient1 = _petQuery.getSingleResult();
        assertNotNull(_patient1);
        _patient1.setBirthDate(_formatter.parse("07/29/1975"));
        tx.commit();
        tx.begin();
        Observation _obs1 = em.find(Observation.class, 1);
        em.remove(_obs1);
        Encounter _enc1 = em.find(Encounter.class, 1);
        em.remove(_enc1);
        Patient _pat1 = em.find(Patient.class, 1);
        em.remove(_pat1);
        Doctor _doc1 = em.find(Doctor.class, 1);
        em.remove(_doc1);
        tx.commit();
    } // end:runTest
} // end:DomainTest