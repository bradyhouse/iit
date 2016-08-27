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
import domain.Patient;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;

/**
 * Stateless session bean used to perform
 * CRUD operations on the mp3_Doctor table.
 * 
 * @author Brady House
 */
@Stateless
@Named
public class PatientBean extends AbstractBaseBean<Patient> {
    /**
     * Default class constructor.
     */
    public PatientBean() {
    } // end:constructor
     /**
     * Value function used to get a list 
     * of all patients entities based on the mp3_Patient
     * table.
     * 
     * @return list of patient instances
     */
    public List<Patient> findAll(){
        return super.findAll("select p from Patient p");
    } // end:findAll
       /**
     * Value function used to get a patient entity based on a 
     * user name string.
     * 
     * @param userName string value equal to Doctor's username property
     * @return doctor class instance
     */
    public Patient findByUserName(String userName) {
        TypedQuery<Patient> _query = this.getEntityManager().createNamedQuery(
                "Patient.findByUsername", Patient.class);
        _query.setParameter("userName", userName);
        return _query.getSingleResult();
    } // end:findByUserName
 } // end:PatientBean
