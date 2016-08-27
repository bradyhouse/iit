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
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.CityDAO;
/**
 * HttpServlet controller class, which handles /city/delete GET and POST
 * requests.
 * 
 * @author Brady Houseknecht
 */
@WebServlet(name = "CityDelete", urlPatterns = {"/city/delete"})
public class CityDelete extends HttpServlet {
    @EJB
    CityDAO cityDao;
    private static final Logger LOG = Logger.getLogger(CityDelete.class.getName());
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
        if(request.getParameterMap().containsKey("id"))
        {
            // Id supplied, then proceed
            Integer _id = Integer.parseInt(request.getParameter("id"));

           LOG.info(("Deleting Id " + _id.toString()));
           if (cityDao.delete(_id)) {
                // Success, then brag 
                request.setAttribute("successes", ("Record " + _id.toString() + " has been deleted.") );
                request.setAttribute("sortOrder","asc");
                request.getRequestDispatcher("/city/select").forward(request, response);
            } else {
                // Otherwise, appologize
                request.setAttribute("violations", ("Doh! Record " + _id.toString() + " could not be deleted."));
                request.getRequestDispatcher("/city/select").forward(request, response);
            } // end:else 
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
} //end:CityDelete
