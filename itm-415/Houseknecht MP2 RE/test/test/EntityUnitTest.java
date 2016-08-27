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

import domain.City;
import domain.Country;
import domain.Countrylanguage;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * 
 * @author Brady House
 */
public class EntityUnitTest {
    
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Houseknecht_MP2_PU");
    protected static EntityManager em;
    protected static EntityTransaction tx;

    
    public EntityUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    } // end:setUp
    
    @After
    public void tearDown() {
        em.close();
    } // end:tearDown
    @Test
    public void reTest() {
        Query _query = em.createQuery("select c from Country c");
        List<Country> _countries = new ArrayList<>();
        _countries = _query.getResultList();
        
        for(Country _c : _countries){
            String _line = "";
            _line = _c.getName().toUpperCase() + "__________" +  
                    System.lineSeparator() + "Cities: ";
            for(City _city : _c.getCityCollection()){
                _line += _city.getName() + ", ";
            } // end:for
            _line += System.lineSeparator()+ "Languages: ";
            
            for(Countrylanguage _lang : _c.getCountrylanguageCollection()){
                _line +=  _lang.getCountrylanguagePK().getLanguage() + ", ";
            } // end:for
            System.out.println(_line);
        } // end:for
    } // end:retest
}