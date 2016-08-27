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
import service.CityDAO;
import reusables.Strings;
/**
 * HttpServlet controller class, which handles /city/insert GET and POST
 * requests.
 * 
 * @author Brady Houseknecht
 */
@WebServlet(name = "CityInsert", urlPatterns = {"/city/insert"})
public class CityInsert extends HttpServlet {
    @Resource
    Validator validator;
    @EJB
    CityDAO cityDao;
    private static final Logger LOG = Logger.getLogger(CityInsert.class.getName());
    private static final Strings u = new Strings();
    /**
     * Method used to build a city object from the supplied
     * http request.
     * 
     * @param request
     * @return 
     */
    public  City buildCityFromRequest(HttpServletRequest request) {
        // Method sets values as null or populated, not as empty string...
       return new City(cityDao.selectNextId(),request.getParameter("name"),
                request.getParameter("countryCode"),
                request.getParameter("district"),
                Integer.parseInt(request.getParameter("population")));
    }
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
        // On GET, add the list of country codes to the request
        List<String> _countryCodes = cityDao.selectCountryCode();
        request.setAttribute("countryCodes", _countryCodes);
        // And then redirect to View
        request.getRequestDispatcher("/dynamic/city-insert-update.jsp").forward(request, response);
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
        // On a POST create a City based on the request
        City c = buildCityFromRequest(request);
        // Validate it's attribute
        Set<ConstraintViolation<City>> violations = validator.validate(c);
        if (violations.isEmpty()) {
            // If no validation errors, 
            // then create
            if (cityDao.create(c)) {
                // Success, then tell
                request.setAttribute("successes", "The city has been created.");
                request.setAttribute("sortOrder","desc");
                request.getRequestDispatcher("/city/select").forward(request, response);
            } else {
                // Otherwise, applogize
                request.setAttribute("violations", "There was a problem creating the city.");
                request.getRequestDispatcher("/city/select").forward(request, response);
            } // end:else
        } else {
            // Otherwise appologize and explain
            LOG.info("There are " + violations.size() + " violations.");
            for (ConstraintViolation<City> violation : violations) {
                LOG.info("### " + violation.getRootBeanClass().getSimpleName()
                        + "." + violation.getPropertyPath()
                        + " - Invalid Value = "
                        + violation.getInvalidValue()
                        + " - Error Msg = " + violation.getMessage());
            } // end:for            
            request.setAttribute("violations", violations);
            request.getRequestDispatcher("/dynamic/city-insert-update.jsp").forward(request, response);
        } // end:else
    } // end:doPost
} // end:InsertCity
