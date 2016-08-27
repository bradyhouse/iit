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
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.City;
import service.CityDAO;
/**
 * HttpServlet controller class, which handles /city/select GET and POST
 * requests.
 * 
 * @author Brady Houseknecht
 */
@WebServlet(name = "CitySelect", urlPatterns = {"/city/select"})
public class CitySelect extends HttpServlet {
    @EJB
    CityDAO cityDao;
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
        String _sortOrder = "asc";
        if(null!=request.getAttribute("sortOrder"))
        {
            _sortOrder =request.getAttribute("sortOrder").toString();
        } // end:if
        List<City> cities = cityDao.select("ID",_sortOrder);
        request.setAttribute("cities", cities);
        request.getRequestDispatcher("/dynamic/city-select.jsp").forward(request, response);
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
        this.doGet(request, response);
    } // end:doPost  
} // end:CitySelect
