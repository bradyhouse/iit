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
import model.CountryLanguage;
import service.CountryLanguageDAO;
import reusables.Strings;
/**
 * HttpServlet controller class, which handles /language/insert GET and POST
 * requests.
 * 
 * @author Brady Houseknecht
 */
@WebServlet(name = "CountryLanguageInsert", urlPatterns = {"/language/insert"})
public class CountryLanguageInsert extends HttpServlet {
    @Resource
    Validator validator;
    @EJB
    CountryLanguageDAO countryLanguageDao;
    private static final Logger LOG = Logger.getLogger(CountryLanguageInsert.class.getName());
    private static final Strings u = new Strings();
    /**
     * Method used to build a city object from the supplied
     * http request.
     * 
     * @param request
     * @return 
     */
    public  CountryLanguage buildCountryLanguageFromRequest(HttpServletRequest request) {
        // Method sets values as null or populated, not as empty string...
       return new CountryLanguage(request.getParameter("countryCode"),
                request.getParameter("language"),
                (request.getParameter("isofficial")== "1" ? true : false),
                Double.parseDouble(request.getParameter("percentage")));
    } // end:buildCountryLanguageFromRequest
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
        List<String> _countryCodes = countryLanguageDao.selectCountryCode();
        request.setAttribute("countryCodes", _countryCodes);
        // And then redirect to View
        request.getRequestDispatcher("/dynamic/language-insert-update.jsp").forward(request, response);
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
         CountryLanguage _c = buildCountryLanguageFromRequest(request);
         // On a POST create a City based on the request
         // Validate it's attribute
        Set<ConstraintViolation<CountryLanguage>> _violations = validator.validate(_c);
        if (_violations.isEmpty()) {
            // If no validation errors, 
            // then create
            if (countryLanguageDao.create(_c)) {
                // Success, then tell
                request.setAttribute("successes", "The country language has been created.");
                request.getRequestDispatcher("/language/select").forward(request, response);
            } else {
                // Otherwise, applogize
                request.setAttribute("violations", "There was a problem creating the country language.");
                request.getRequestDispatcher("/language/select").forward(request, response);
            } // end:else
        } else {
            // Otherwise appologize and explain
            LOG.info("There are " + _violations.size() + " violations.");
            for (ConstraintViolation<CountryLanguage> _violation : _violations) {
                LOG.info("### " + _violation.getRootBeanClass().getSimpleName()
                        + "." + _violation.getPropertyPath()
                        + " - Invalid Value = "
                        + _violation.getInvalidValue()
                        + " - Error Msg = " + _violation.getMessage());
            } // end:for            
            request.setAttribute("violations", _violations);
            request.getRequestDispatcher("/dynamic/language-insert-update.jsp").forward(request, response);
        } // end:else */
    } // end:doPost
} // end:CountryLanguageInsert
