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
package controllers;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import model.City;
import model.Country;
import model.CountryLanguage;
import service.CountryDAO;
import service.CountryLanguageDAO;
/**
 * HttpServlet controller class, which handles /country/update GET and POST
 * requests.
 * 
 * @author Brady Houseknecht
 */
@WebServlet(name = "CountryUpdate", urlPatterns = {"/country/update"})
public class CountryUpdate extends HttpServlet {
    @EJB
    CountryDAO countryDao;
    @Resource
    Validator validator;
    private static final Logger LOG = Logger.getLogger(CountryUpdate.class.getName());
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameterMap().containsKey("postbackCode")) {
            String _code = request.getParameter("postbackCode").trim();
            Country _c = countryDao.select(_code);
            _c.setName(request.getParameter("name"));      
            _c.setContinent(request.getParameter("continent"));      
            _c.setRegion(request.getParameter("region"));      
            _c.setSurfaceArea(Float.parseFloat(request.getParameter("surfacearea")));      
            _c.setIndepYear(Integer.parseInt(request.getParameter("indepyear")));      
            _c.setPopulation(Integer.parseInt(request.getParameter("population")));      
            _c.setLifeExpectancy(Float.parseFloat(request.getParameter("lifeexpectancy")));      
            _c.setGNP(Float.parseFloat(request.getParameter("gnp")));      
            _c.setGNPOld(Float.parseFloat(request.getParameter("gnpold")));      
            _c.setLocalName(request.getParameter("localname"));      
            _c.setGovernmentForm(request.getParameter("governmentform"));      
            _c.setHeadOfState(request.getParameter("headofstate"));      
            _c.setCode2(request.getParameter("code2"));      
            
            // Validate it's attribute
            Set<ConstraintViolation<Country>> _violations = validator.validate(_c);
            if (_violations.isEmpty()) {
                // If no validation errors, 
                // then create
                if (countryDao.update(_c)) {
                    // Success, then tell
                    request.setAttribute("successes", "The country has been updated.");
                    request.getRequestDispatcher("/country/select").forward(request, response);
                } else {
                    // Otherwise, applogize
                    request.setAttribute("violations", "There was a problem updating the country.");
                    request.getRequestDispatcher("/country/select").forward(request, response);
                } // end:else
            } else {
                // Otherwise appologize and explain
                LOG.info(("There are " + _violations.size() + " violations."));
                for (ConstraintViolation<Country> _violation : _violations) {
                    LOG.info(("### " + _violation.getRootBeanClass().getSimpleName()
                            + "." + _violation.getPropertyPath()
                            + " - Invalid Value = "
                            + _violation.getInvalidValue()
                            + " - Error Msg = " + _violation.getMessage()));
                } // end:for            
                request.setAttribute("violations", _violations);
                request.getRequestDispatcher("/dynamic/country-insert-update.jsp").forward(request, response);
            } // end:else 
        } else if(request.getParameterMap().containsKey("code")) {
           // Country Code and language supplied
           String _code = request.getParameter("code").trim();
           LOG.info(("Updating " + _code));
           // Add the selected City 
           Country _country = countryDao.select(_code);
           // Add City lookup data
           List<City> cities = countryDao.selectCities();
           request.setAttribute("cities", cities);
           request.setAttribute("country", _country);
           // And redirect ...
           request.getRequestDispatcher("/dynamic/country-insert-update.jsp").forward(request, response);
        } else {
            // Otherwise, appologize
            request.setAttribute("violations", ("Doh! No \"Code\" came over on that request, Please try again."));
            request.getRequestDispatcher("/country/select").forward(request, response);
        } // end:if:else
    } // end:doGet
    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          this.doGet(request, response);
    } //end:doPost
 /**
     * Method used to build a country object from the supplied
     * HTTP request.
     * 
     * @param request HttpServletRequest instance
     * @return 
     */
    public  Country buildCountryFromRequest(HttpServletRequest request) {
       return (new Country(request.getParameter("code").trim(),
                    request.getParameter("name").trim(),
                    request.getParameter("continent").trim(),
                    request.getParameter("region").trim(),
                    Float.parseFloat(request.getParameter("surfacearea")),
                    Integer.parseInt(request.getParameter("indepyear").trim()),
                    Integer.parseInt(request.getParameter("population").trim()),
                    Float.parseFloat(request.getParameter("lifeexpectancy").trim()),
                    Float.parseFloat(request.getParameter("gnp").trim()),
                    Float.parseFloat(request.getParameter("gnpold").trim()),
                    request.getParameter("localname").trim(),
                    request.getParameter("governmentform").trim(),
                    request.getParameter("headofstate").trim(),
                    Integer.parseInt(request.getParameter("capital").trim()),
                    request.getParameter("code2").trim()
                   ));
    } // end:buildCountryFromRequest
} // end:CountryUpdate
