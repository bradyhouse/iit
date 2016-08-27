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
import model.CountryLanguage;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
/**
 * CountryLanguage Data Access Object 
 * 
 * This class is a wrapper for
 * the CountryLanguage table 
 * defined on the world database. 
 * 
 * @author Brady Houseknecht
 */
@Stateless
public class CountryLanguageDAO {
    @Resource(lookup = "jdbc/houseknechtMp1Mvc")
    DataSource ds;
    /**
     * Create a new record
     * 
     * @param c CountryLanguage instance defining attributes of the new record.
     * @return boolean indicating whether the operation was successful.
     * @see model.CountryLanguage
     */
    public boolean create(CountryLanguage c) {
        // Verify that the Name / CountryCode / District Combination
        // doesn't already exist
        if(null==this.select(c.getCountryCode(),c.getLanguage()))
        {
            String _sql = "INSERT INTO countrylanguage (CountryCode, Language ,IsOfficial, Percentage) VALUES ('"
                    + c.getCountryCode() + "', '" 
                    + c.getLanguage() + "'," 
                    + (c.getIsOfficial() == true ? "'T'" : "'F'") + "," 
                    + c.getPercentage().toString() +
                    ")";
            try (Connection _con = ds.getConnection()) {
                  Statement _stmt = _con.createStatement();
                  _stmt.execute(_sql);
                  return true;
            } catch (SQLException _ex) {
               Logger.getLogger(CountryLanguageDAO.class.getName()).log(Level.SEVERE, null, _ex);
               return false;
            } // end:try
        } // end:if
        else {
            return false;
        } // end:else
    } // end:create
    /**
     * Delete an existing CountryLanguage record
     * 
     * @param countryCode string value equal to the countryCode of the record to be deleted
     * @param language string value equal to the language of the record to be deleted
     * @return boolean indicating whether the operation was successful. 
     */
    public boolean delete(String countryCode, String language) {
        String _sql =   "DELETE FROM countrylanguage WHERE " +
                        " countrycode = '" + countryCode.trim() +"' AND " +
                        " language= '" + language.trim() + "';";
        try (Connection _con = ds.getConnection()) {
              Statement _stmt = _con.createStatement();
              _stmt.execute(_sql);
              return true;
        } catch (SQLException _ex) {
           Logger.getLogger(CountryLanguageDAO.class.getName()).log(Level.SEVERE, null, _ex);
           return false;
        } // end:try
    } // end:delete
    /**
     * Update an existing record
     * 
     * @param c CountryLanguage instance defining attributes of the new record.
     * @return boolean indicating whether the operation was successful.
     * @see model.CountryLanguage
     */
    public boolean update(CountryLanguage c) {

        String _sql =   "UPDATE countrylanguage " +
                        "SET countrycode = '" + c.getCountryCode().trim() + "', " +
                        "language = '" + c.getLanguage().trim()+ "', " +
                        "isOfficial = " + (c.getIsOfficial() == true ? "'T'" : "'F'"  )+ ", " +
                        "Percentage = " + c.getPercentage().toString() +
                        "WHERE countrycode = '" + c.getCountryCode().trim() + "' AND " +
                        "language = '" + c.getLanguage().trim() + "'";
        
        try (Connection _con = ds.getConnection()) {
              Statement _stmt = _con.createStatement();
              _stmt.execute(_sql);
              return true;
        } catch (SQLException _ex) {
           Logger.getLogger(CountryLanguageDAO.class.getName()).log(Level.SEVERE, null, _ex);
           return false;
        } // end:try
    } // end:update
    /**
     * Select all records
     * 
     * @return List of records (CountryLanguage instances)
     * @see model.CountryLanguage
     */
    public List<CountryLanguage> select() {
        String _sql = "SELECT * FROM countrylanguage;";
        List _languages = new ArrayList<>();

        try (Connection _con = ds.getConnection()) {

            Statement _stmt = _con.createStatement();
            ResultSet _rs = _stmt.executeQuery(_sql);

            while (_rs.next()) {
                CountryLanguage _c = new CountryLanguage(_rs.getString("CountryCode"),
                        _rs.getString("Language"),
                        _rs.getBoolean("IsOfficial"),
                        _rs.getDouble("Percentage"));
                _languages.add(_c);
            } // end:while

        } catch (SQLException _ex) {
            Logger.getLogger(CityDAO.class.getName()).log(Level.SEVERE, null, _ex);
            return null;
        } // end:catch
        return _languages;
    } // end:select  
    /**
     * Overloaded Select with id filter criteria
     * 
     * @param code string value equal to the country code to filter for
     * @param language string value equal to the language to filter for
     * @return single record (CountryLanguage instances)
     * @see model.CountryLanguage
    */
    public CountryLanguage select(String code, String language) {
        CountryLanguage _language = null;
        String _sql = "SELECT * FROM countrylanguage WHERE " +
                "countrycode='" + code.trim() + "' AND " + 
                "language='" + language.trim() + "';";
        try (Connection _con = ds.getConnection()) {
            Statement _stmt = _con.createStatement();
            ResultSet _rs = _stmt.executeQuery(_sql);
            while (_rs.next()) {
                return new CountryLanguage(_rs.getString("CountryCode"),
                        _rs.getString("Language"),
                        (_rs.getString("IsOfficial").trim()== "T"? true : false),
                        _rs.getDouble("Percentage"));
            } // end:while
        } catch (SQLException _ex) {
            Logger.getLogger(CountryLanguageDAO.class.getName()).log(Level.SEVERE, null, _ex);
            return null;
        } // end:catch
        return _language;
    }// end:select
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
            Logger.getLogger(CountryLanguageDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return _codes;
    } // end:selectCountryCode
} // end:CountryLanguage
