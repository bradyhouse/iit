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
package domain.ejb.security;

import domain.ejb.AbstractBaseBean;
import domain.security.Group;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Stateless session bean used to perform
 * CRUD operations on the mp3_Group table.
 * 
 * @author Brady House
 */
@Stateless
@Named
public class GroupBean extends AbstractBaseBean<Group> {
    /**
     * Default class constructor.
     */
    public GroupBean() {
    } // end:constructor
    /**
     * Value function used to get a list 
     * of all group entities based on the mp3_Group
     * table.
     * 
     * @return list of group instances
     */
    public List<Group> findAll(){
        return super.findAll("select g from Group g");
    } // end:findAll
} // end:GroupBean
