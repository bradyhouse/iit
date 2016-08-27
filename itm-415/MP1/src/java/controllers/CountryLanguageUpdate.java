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
/**
 * HttpServlet controller class, which handles /language/update GET and POST
 * requests.
 * 
 * @author Brady Houseknecht
 */
@WebServlet(name = "CountryLanguageUpdate", urlPatterns = {"/language/update"})
public class CountryLanguageUpdate extends HttpServlet {
    @EJB
    CountryLanguageDAO countryLanguageDao;
    @Resource
    Validator validator;
    private static final Logger LOG = Logger.getLogger(CountryLanguageUpdate.class.getName());
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
        if(request.getParameterMap().containsKey("postbackCountryCode") &&
           request.getParameterMap().containsKey("postbackLanguage")) {
            // On a POST create a City based on the request
            String _code = request.getParameter("postbackCountryCode").trim();
            String _language = request.getParameter("postbackLanguage").trim();
           
            CountryLanguage _c = countryLanguageDao.select(_code, _language);
            _c.setIsOfficial((request.getParameter("isofficial") == "1" ? true : false));
            _c.setPercentage(Double.parseDouble(request.getParameter("percentage")));
            
            // Validate it's attribute
            Set<ConstraintViolation<CountryLanguage>> _violations = validator.validate(_c);
            if (_violations.isEmpty()) {
                // If no validation errors, 
                // then create
                if (countryLanguageDao.update(_c)) {
                    // Success, then tell
                    request.setAttribute("successes", "The country language has been updated.");
                    request.getRequestDispatcher("/language/select").forward(request, response);
                } else {
                    // Otherwise, applogize
                    request.setAttribute("violations", "There was a problem updating the country language.");
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
            } // end:else 
        } else if(request.getParameterMap().containsKey("countryCode") &&
                  request.getParameterMap().containsKey("language")) {
           // Country Code and language supplied
           String _code = request.getParameter("countryCode").trim();
           String _lang = request.getParameter("language").trim();
           LOG.info(("Updating " + _code + ", " + _lang));
           // Add the list of country codes to the request
           List<String> _countryCodes = countryLanguageDao.selectCountryCode();
           request.setAttribute("countryCodes", _countryCodes);
           // Add the selected City 
           CountryLanguage _language = countryLanguageDao.select(_code, _lang);
           
           request.setAttribute("language", _language);
           // And redirect ...
           request.getRequestDispatcher("/dynamic/language-insert-update.jsp").forward(request, response);
        } else {
            // Otherwise, appologize
            request.setAttribute("violations", ("Doh! No \"Id\" came over on that request, Please try again."));
            request.getRequestDispatcher("/city/select").forward(request, response);
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
     * Method used to build a city object from the supplied
     * HttpServletRequest instance.
     * 
     * @param request HttpServletRequest instance 
     * @return City class instance
     * @see model.CountryLanguage
     */
    public  CountryLanguage buildCountryLanguageFromRequest(HttpServletRequest request) {
        // Method sets values as null or populated, not as empty string...
       return new CountryLanguage(request.getParameter("countryCode"),
                request.getParameter("language"),
                (request.getParameter("isofficial") == "1" ? true : false),
                Double.parseDouble(request.getParameter("Percentage")));
    } // end:buildCountryLanguageFromRequest
} // end:CountryLanguageUpdate
