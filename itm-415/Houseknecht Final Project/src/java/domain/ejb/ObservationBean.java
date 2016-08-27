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

import domain.Observation;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Stateless session bean used to perform
 * CRUD operations on the mp3_Observation table.
 * 
 * @author Brady House
 */
@Stateless
@Named
public class ObservationBean extends AbstractBaseBean<Observation> {
    /**
     * Default class constructor.
     */
    public ObservationBean() {
    } // end:constructor
    /**
     * Value function used to get a 
     * list of all Observation entities.
     * 
     * @return list of Observation instances
     */
    public List<Observation> findAll(){
        return super.findAll("select o from Observation o");
    } // end:findAll
} // end:ObservationBean
