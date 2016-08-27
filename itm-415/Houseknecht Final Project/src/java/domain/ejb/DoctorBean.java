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
public class DoctorBean extends AbstractBaseBean<Doctor> {
    /**
     * Default class constructor
     */
    public DoctorBean() {
    } // end:constructor
    /**
     * Value function used to get a list of all doctor entities.
     * 
     * @return list of doctor instances
     */
    public List<Doctor> findAll(){
        return super.findAll("select d from Doctor d");
    } // end:findAll
    /**
     * Value function used to get a doctor entity based on a 
     * user name string.
     * 
     * @param userName string value equal to Doctor's username property
     * @return doctor class instance
     */
    public Doctor findByUserName(String userName) {
        TypedQuery<Doctor> _query = this.getEntityManager().createNamedQuery(
                "Doctor.findByUsername",Doctor.class);
        _query.setParameter("userName", userName);
        return _query.getSingleResult();
    } // end:findByUserName
            
} // end:DoctorBean
