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
package service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.sql.DataSource;
import model.City;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
/**
 * City Data Access Object 
 * 
 * This class is a wrapper for
 * the City table defined on the
 * world database. 
 * 
 * @author Brady Houseknecht
 */
@Stateless
public class CityDAO {
    @Resource(lookup = "jdbc/houseknechtMp1Mvc")
    DataSource ds;
    /**
     * Create a new record
     * 
     * @param c City instance defining attributes of the new record.
     * @return boolean indicating whether the operation was successful.
     * @see model.City
     */
    public boolean create(City c) {
        // Verify that the Name / CountryCode / District Combination
        // doesn't already exist
        if(this.select(c.getName(),c.getCountryCode(),c.getDistrict()).isEmpty())
        {
            String _sql = "INSERT INTO city(ID, Name, CountryCode, District, Population) VALUES ("
                    + c.getId().toString() + ",'"
                    + c.getName() + "', '" 
                    + c.getCountryCode() + "', '" 
                    + c.getDistrict() + "'," 
                    + c.getPopulation().toString() +
                    ")";
            try (Connection _con = ds.getConnection()) {
                  Statement _stmt = _con.createStatement();
                  _stmt.execute(_sql);
                  return true;
            } catch (SQLException _ex) {
               Logger.getLogger(CityDAO.class.getName()).log(Level.SEVERE, null, _ex);
               return false;
            } // end:try
        } // end:if
        else {
            return false;
        } // end:else
    } // end:create
    /**
     * Delete an existing City record based on its Id.
     * 
     * @param cityId integer value equal to the Id of the record to be deleted.
     * @return boolean indicating whether the operation was successful. 
     */
    public boolean delete(Integer id) {
        String _sql =   "DELETE FROM city " +
                        "WHERE id = " + id.toString();
        try (Connection _con = ds.getConnection()) {
              Statement _stmt = _con.createStatement();
              _stmt.execute(_sql);
              return true;
        } catch (SQLException _ex) {
           Logger.getLogger(CityDAO.class.getName()).log(Level.SEVERE, null, _ex);
           return false;
        } // end:try
    } // end:delete
    /**
     * Update an existing record
     * 
     * @param c City instance defining attributes of the new record.
     * @return boolean indicating whether the operation was successful.
     * @see model.City
     */
    public boolean update(City c) {

        String _sql =   "UPDATE city " +
                        "SET name = '" + c.getName().trim() + "', " +
                        "countrycode = '" + c.getCountryCode().trim() + "', " +
                        "district = '" + c.getDistrict().trim()+ "', " +
                        "population = " + c.getPopulation().toString() + " " +
                        "WHERE id = " + c.getId().toString();
        
        try (Connection _con = ds.getConnection()) {
              Statement _stmt = _con.createStatement();
              _stmt.execute(_sql);
              return true;
        } catch (SQLException _ex) {
           Logger.getLogger(CityDAO.class.getName()).log(Level.SEVERE, null, _ex);
           return false;
        } // end:try
    } // end:update
    /**
     * Select all records
     * 
     * @return List of records (City instances)
     * @see model.City
     */
    public List<City> select() {
        String _sql = "select * from city";
        List _cities = new ArrayList<>();

        try (Connection _con = ds.getConnection()) {

            Statement _stmt = _con.createStatement();
            ResultSet _rs = _stmt.executeQuery(_sql);

            while (_rs.next()) {
                City _c = new City(_rs.getInt("ID"),
                        _rs.getString("Name"),
                        _rs.getString("CountryCode"),
                        _rs.getString("District"),
                        _rs.getInt("Population"));
                _cities.add(_c);
            } // end:while

        } catch (SQLException _ex) {
            Logger.getLogger(CityDAO.class.getName()).log(Level.SEVERE, null, _ex);
            return null;
        } // end:catch
        return _cities;
    } // end:select  
    /**
     * Overloaded Select with id filter criteria
     * 
     * @param id int value equal to the id to filter for
     * @return single record (City instances)
     * @see model.City
     * @return 
     */
    public City select(Integer id) {
        City _city = null;
        
        String _sql = "SELECT * FROM city WHERE " +
                "id=" + id.toString();
        try (Connection _con = ds.getConnection()) {

            Statement _stmt = _con.createStatement();
            ResultSet _rs = _stmt.executeQuery(_sql);
        
            while (_rs.next()) {
                return new City(_rs.getInt("ID"),
                        _rs.getString("Name"),
                        _rs.getString("CountryCode"),
                        _rs.getString("District"),
                        _rs.getInt("Population"));
                
            } // end:while
        } catch (SQLException _ex) {
            Logger.getLogger(CityDAO.class.getName()).log(Level.SEVERE, null, _ex);
            return null;
        } // end:catch
        return null;
    }// end:select
    /**
     * Overloaded Select with sort criteria
     * 
     * @param field string value equal to the field to sort by
     * @param order string value equal to "ASC" or "DESC"
     * @return List of records (City instances)
     * @see model.City
     */
    public List<City> select(String field, String order) {
        String _sql = "SELECT * FROM city ORDER BY " + field + " " + order;
        List _cities = new ArrayList<>();
        try (Connection con = ds.getConnection()) {

            Statement _stmt = con.createStatement();
            ResultSet _rs = _stmt.executeQuery(_sql);

            while (_rs.next()) {
                City _c = new City(_rs.getInt("ID"),
                        _rs.getString("Name"),
                        _rs.getString("CountryCode"),
                        _rs.getString("District"),
                        _rs.getInt("Population"));
                _cities.add(_c);
            } // end:while
        } catch (SQLException ex) {
            Logger.getLogger(CityDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } // end:catch
        return _cities;
    } // end:select    
    /**
     * Overloaded Select with filter criteria
     * 
     * @param name string value equal to the name to filter for
     * @param countryCode string value equal to the countryCode to filter for 
     * @param district string value equal to the district to filter for
     * @return List of records (City instances)
     * @see model.City
     */    
    public List<City> select(String name, String countryCode, String district) {
        
        List<City> _cities = new ArrayList();
        
        String _sql = "select * from city where " +
                "name='" + name.trim() +"' and " +
                "countryCode='" + countryCode.trim() + "' and " +
                "district='" + district.trim() + "'";

        try (Connection _con = ds.getConnection()) {

            Statement _stmt = _con.createStatement();
            ResultSet _rs = _stmt.executeQuery(_sql);
        
            while (_rs.next()) {
                City _c = new City(_rs.getInt("ID"),
                        _rs.getString("Name"),
                        _rs.getString("CountryCode"),
                        _rs.getString("District"),
                        _rs.getInt("Population"));
                
                _cities.add(_c);
            }
            return _cities;
            
        } catch (SQLException _ex) {
            Logger.getLogger(CityDAO.class.getName()).log(Level.SEVERE, null, _ex);
            return null;
        } // end:catch
    } // end:select
    /**
     * Select all Country Codes
     * 
     * @return List of CountryCodes
     */
    public List<String> selectCountryCode() {
        String _sql = "SELECT DISTINCT code FROM country ORDER BY code ASC";

        List _codes = new ArrayList<>();

        try (Connection con = ds.getConnection()) {

            Statement _stmt = con.createStatement();
            ResultSet _rs = _stmt.executeQuery(_sql);

            while (_rs.next()) {
                _codes.add(_rs.getString("code"));
            } 
        } catch (SQLException ex) {
            Logger.getLogger(CityDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return _codes;
    } // end:selectCountryCode
    /**
     * Get the Max(Id) + 1.
     * 
     * @return integer value equal to the next City Id value.
     */
    public int selectNextId(){
        String _sql = "select (max(id)+1) as NextId from city";
        try (Connection _con = ds.getConnection()) {
            Statement _stmt = _con.createStatement();
            ResultSet _rs = _stmt.executeQuery(_sql);
            while (_rs.next()) {
                return _rs.getInt("NextId");
            } // end:while
        } catch (SQLException _ex) {
            Logger.getLogger(CityDAO.class.getName()).log(Level.SEVERE, null, _ex);
            return 1;
        } // end:catch
        return 1;
    } // end:selectNextId 
} // end:CityDAO
