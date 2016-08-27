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

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Abstract class defining the attributes
 * common to all stateless domain.ejb beans.
 * 
 * @param <Type> 
 * @author Brady House
 */
public class AbstractBaseBean<Type> {
    @PersistenceContext(unitName="Houseknecht_FP_PU")
    private EntityManager manager;
    private Class<Type> entityClass;
    /**
     * EnityManager getter.
     * 
     * @return EnityManager instance
     */
    protected EntityManager getEntityManager(){
        return this.manager;
    } // end:getEntityManager
    /**
     * Control function used to create a new instance of the
     * configured type.
     * 
     * @param entity instance of the configured entity type that is to be created.
     */
    public void create(Type entity){
        this.manager.persist(entity);
    } // end:create  
    /**
     * Find value function that can be used to search for the configured
     * entity type using its Id attribute value.
     * 
     * @param id object equal to the configured type id attribute
     * @return configured type instance
     */
    public Type find(Object id){
        return this.manager.find(entityClass, id);
    } // end:find
    /**
     * Overloaded version of the find value function, which accepts
     * a JPQL query string in place of the Id attribute value.
     * 
     * @param jpql string value defining the query criteria.
     * @return list of instances of the configured type.
     */
    public List<Type> findAll(String jpql){
        TypedQuery<Type> query = this.manager.createQuery(jpql, entityClass);
        return query.getResultList();
    } // end:findAll
    /**
     * Update control function.
     * 
     * @param entity instance of the configured entity type that is to be updated.
     */
    public void update(Type entity){
        this.manager.merge(entity);
    } // end:update
    /**
     * Delete control function.
     * @param entity instance of the configured entity that is to be deleted.
     */
    public void delete(Type entity){
        this.manager.remove(entity);
    } // end:delete
} // end:AbstractBaseBean
 
