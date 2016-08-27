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
import model.Country;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.City;
/**
 * Country Data Access Object 
 * 
 * This class is a wrapper for
 * the Country table defined on the
 * world database. 
 * 
 * @author Brady Houseknecht
 */
@Stateless
public class CountryDAO {
    @Resource(lookup = "jdbc/houseknechtMp1Mvc")
    DataSource ds;
    /**
     * Create a new record
     * 
     * @param c Country instance defining attributes of the new record.
     * @return boolean indicating whether the operation was successful.
     * @see model.Country
     */
    public boolean create(Country c) {
        String _sql = this.createInsertStatement(c);
        
        Logger.getLogger(CountryDAO.class.getName()).log(Level.INFO, _sql);
        try (Connection _con = ds.getConnection()) {
              Statement _stmt = _con.createStatement();
              _stmt.execute(_sql);
              return true;
        } catch (SQLException _ex) {
           Logger.getLogger(CountryDAO.class.getName()).log(Level.SEVERE, null, _ex);
           return false;
        } // end:try
    } // end:create
    /**
     * Create an Insert statement given a 
     * Country class instance
     * 
     * @param c Country class instance
     * @return string value equal to the insert statement
     */
    public String createInsertStatement(Country c)  {
       return  ("INSERT INTO country (Code," +
            "Name, " +
            "Continent, " +
            "Region, " +
            "SurfaceArea, " +
            "IndepYear, " +
            "Population, " +
            "LifeExpectancy, " +
            "GNP, " +
            "GNPOld, " +
            "LocalName, " +
            "GovernmentForm, " +
            "HeadOfState, " +
            "Capital, " +
            "Code2) VALUES ('"
            + (null!=c.getCode() ? c.getCode().trim() : "" ) + "', '" 
            + (null!=c.getName() ? c.getName().trim() : "" ) + "', '"
            + (null!=c.getContinent() ? c.getContinent().trim() : "" ) + "', '" 
            + (null!=c.getRegion()? c.getRegion().trim() : "" ) + "', "
            + (null!=c.getSurfaceArea() ? c.getSurfaceArea().toString() : "1" ) + ", "
            + (null!=c.getIndepYear() ? c.getIndepYear().toString() : "1" ) + ", "
            + (null!=c.getPopulation() ? c.getPopulation().toString() : "1" ) + ", "
            + (null!=c.getLifeExpectancy() ? c.getLifeExpectancy().toString() : "1" ) + ", "
            + (null!=c.getGNP() ? c.getGNP().toString() : "1" ) + ", "
            + (null!=c.getGNPOld() ? c.getGNPOld().toString() : "1" ) + ", '"
            + (null!=c.getLocalName() ? c.getLocalName().trim() : "") + "', '" 
            + (null!=c.getGovernmentForm() ? c.getGovernmentForm().trim() : "") + "', '" 
            + (null!=c.getHeadOfState() ? c.getHeadOfState().trim() : "") + "', " 
            + (null!=c.getCapital() ? c.getCapital().toString() : "1") + ", '"
            + (null!=c.getCode2() ? c.getCode2().trim() : "") + "'"
            + ");");
    } // end:createInsertStatement
    /**
     * Delete an existing Country record
     * 
     * @param countryCode string value equal to the countryCode of the record to be deleted
     * @return boolean indicating whether the operation was successful.
     * @see model.Country
     */
    public boolean delete(String countryCode) {
        String _sql1 =   "DELETE FROM city WHERE " +
                        " countrycode = '" + countryCode.trim() +"';";
        String _sql2 =   "DELETE FROM countryLanguage WHERE " +
                        " countrycode = '" + countryCode.trim() +"';";
        String _sql3 =   "DELETE FROM country WHERE " +
                        " code = '" + countryCode.trim() +"';";
        try (Connection _con = ds.getConnection()) {
              Statement _stmt1 = _con.createStatement();
              _stmt1.execute(_sql1);
              Statement _stmt2 = _con.createStatement();
              _stmt2.execute(_sql2);
              Statement _stmt3 = _con.createStatement();
              _stmt2.execute(_sql3);
              return true;
        } catch (SQLException _ex) {
           Logger.getLogger(CountryDAO.class.getName()).log(Level.SEVERE, null, _ex);
           return false;
        } // end:try
    } // end:delete
    /**
     * Update an existing record
     * 
     * @param c Country instance defining attributes of the new record.
     * @return boolean indicating whether the operation was successful.
     * @see model.Country
     */
    public boolean update(Country c) {

        String _sql1 =   "SET FOREIGN_KEY_CHECKS = 0;";
        
        Logger.getLogger(CountryDAO.class.getName()).log(Level.INFO,_sql1);

        String _sql2 =  "UPDATE country " +
                        "SET Name='" + c.getName().trim() + "', " +
                        "Continent='" + c.getContinent().trim() + "', " +
                        "Region='" + c.getRegion().trim() + "', " +
                        "SurfaceArea=" + c.getSurfaceArea().toString() + ", " +
                        "IndepYear=" + c.getIndepYear().toString() + ", " +
                        "Population=" + c.getPopulation().toString() + ", " +
                        "LifeExpectancy=" + c.getLifeExpectancy().toString() + ", " +
                        "GNP=" + c.getGNP().toString() + ", " +
                        "GNPOld=" + c.getGNPOld().toString() + ", " +
                        "LocalName='" + c.getLocalName().trim() + "', " +
                        "GovernmentForm='" + c.getGovernmentForm().trim() + "', " +
                        "HeadOfState='" + c.getHeadOfState().trim() + "', " +
                        "Capital=" + c.getCapital().toString() + ", " +
                        "Code2='" + c.getCode2().trim() + "' " +
                        "WHERE Code='" + c.getCode() + "'; ";

        Logger.getLogger(CountryDAO.class.getName()).log(Level.INFO,_sql2);

        String _sql3 = "SET FOREIGN_KEY_CHECKS = 1;";
        
        Logger.getLogger(CountryDAO.class.getName()).log(Level.INFO,_sql3);
        
        try (Connection _con = ds.getConnection()) {
              Statement _stmt1 = _con.createStatement();
              _stmt1.execute(_sql1);
               Statement _stmt2 = _con.createStatement();
              _stmt1.execute(_sql2); 
              Statement _stmt3 = _con.createStatement();
              _stmt1.execute(_sql3);
              return true;
        } catch (SQLException _ex) {
           Logger.getLogger(CountryDAO.class.getName()).log(Level.SEVERE, null, _ex);
           return false;
        } // end:try
    } // end:update
    /**
     * Select all records
     * 
     * @return List of records (Country instances)
     * @see model.Country
     */
    public List<Country> select() {
        String _sql = "SELECT * FROM country;";
        List _countries = new ArrayList<>();

        try (Connection _con = ds.getConnection()) {

            Statement _stmt = _con.createStatement();
            ResultSet _rs = _stmt.executeQuery(_sql);

            while (_rs.next()) {
                Country _c = new Country(
                        _rs.getString("Code"),
                        _rs.getString("Name"),
                        _rs.getString("Continent"),
                        _rs.getString("Region"),
                        _rs.getFloat("SurfaceArea"),
                        _rs.getInt("IndepYear"),
                        _rs.getInt("Population"),
                        _rs.getFloat("LifeExpectancy"),
                        _rs.getFloat("GNP"),
                        _rs.getFloat("GNPOld"),
                        _rs.getString("LocalName"),
                        _rs.getString("GovernmentForm"),
                        _rs.getString("HeadOfState"),
                        _rs.getInt("Capital"),
                        _rs.getString("Code2")
                        );
                _countries.add(_c);
            } // end:while

        } catch (SQLException _ex) {
            Logger.getLogger(CountryDAO.class.getName()).log(Level.SEVERE, null, _ex);
            return null;
        } // end:catch
        return _countries;
    } // end:select  
    /**
     * Overloaded Select with country code filter criteria
     * 
     * @param code string value equal to the code to filter for
     * @return single record (Country instances)
     * @see model.Country
    */
    public Country select(String code) {
        Country _country = null;
        String _sql = "SELECT * FROM country WHERE " +
                "code='" + code.trim() + "';";
        try (Connection _con = ds.getConnection()) {
            Statement _stmt = _con.createStatement();
            ResultSet _rs = _stmt.executeQuery(_sql);
            while (_rs.next()) {
                return new Country(
                        _rs.getString("Code"),
                        _rs.getString("Name"),
                        _rs.getString("Continent"),
                        _rs.getString("Region"),
                        _rs.getFloat("SurfaceArea"),
                        _rs.getInt("IndepYear"),
                        _rs.getInt("Population"),
                        _rs.getFloat("LifeExpectancy"),
                        _rs.getFloat("GNP"),
                        _rs.getFloat("GNPOld"),
                        _rs.getString("LocalName"),
                        _rs.getString("GovernmentForm"),
                        _rs.getString("HeadOfState"),
                        _rs.getInt("Capital"),
                        _rs.getString("Code2")
                        );
            } // end:while
        } catch (SQLException _ex) {
            Logger.getLogger(CountryDAO.class.getName()).log(Level.SEVERE, null, _ex);
            return null;
        } // end:catch
        return _country;
    }// end:select
    /**
     * Select all records
     * 
     * @return List of records (City instances)
     * @see model.City
     */
    public List<City> selectCities() {
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
    
} // end:CountryDAO
