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
import java.io.PrintWriter;
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
import model.Country;
import model.City;
import service.CountryDAO;
import service.CityDAO;
import reusables.Strings;
/**
 * HttpServlet controller class, which handles /country/insert 
 * GET and POST requests.
 * 
 * @author Brady Houseknecht
 */
@WebServlet(name = "CountryInsert", urlPatterns = {"/country/insert"})
public class CountryInsert extends HttpServlet {
    @Resource
    Validator validator;
    @EJB
    CountryDAO countryDao;
    private static final Logger LOG = Logger.getLogger(CountryInsert.class.getName());
    private static final Strings u = new Strings();
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Add City lookup data
        List<City> cities = countryDao.selectCities();
        request.setAttribute("cities", cities);
        // And then redirect to View
        request.getRequestDispatcher("/dynamic/country-insert-update.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            // On a POST create a Country class instance
            // based on the request
            Country _c = this.buildCountryFromRequest(request);
            String _sql = countryDao.createInsertStatement(_c);

            // Validate it's attribute
            Set<ConstraintViolation<Country>> violations = validator.validate(_c);
         
           if (violations.isEmpty()) {
               if (countryDao.create(_c)) {
                   // Success, then tell
                   request.setAttribute("successes", "The country has been created.");
                   request.getRequestDispatcher("/country/select").forward(request, response);
               } else {
                   // Otherwise, applogize
                   request.setAttribute("violations", "There was a problem creating the country.");
                   request.getRequestDispatcher("/country/select").forward(request, response);
               } // end:else 
           } else {
               // Otherwise appologize and explain
               LOG.info("There are " + violations.size() + " violations.");
               for (ConstraintViolation<Country> _violation : violations) {
                   LOG.info("### " + _violation.getRootBeanClass().getSimpleName()
                           + "." + _violation.getPropertyPath()
                           + " - Invalid Value = "
                           + _violation.getInvalidValue()
                           + " - Error Msg = " + _violation.getMessage());
               } // end:for            
               request.setAttribute("violations", violations);
               request.getRequestDispatcher("/dynamic/country-insert-update.jsp").forward(request, response);
           } // end:else 
    } // end:doPost
} // end:CountryInsert
