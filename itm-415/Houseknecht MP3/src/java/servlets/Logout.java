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

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet class used to end an 
 * authenticated user's session.
 * 
 * @author Brady House
 */
@WebServlet(name = "logout", urlPatterns = {"/logout"})
public class Logout extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          request.logout();
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter _out = response.getWriter();
        try {
            _out.println("<!DOCTYPE html>");
            _out.println("<html>");
            _out.println("<head>");
            _out.println("<title>MP3 logout</title>");            
            _out.println("</head>");
            _out.println("<body>");
            _out.println("<pre><nobr>     _        _        _    <br />");
            _out.println("   _( )__   _( )__   _( )__ <br />");
            _out.println(" _|     _|_|     _|_|     _|<br />");
            _out.println("(_ F _ (_(_ M _ (_(_ P _ (_ <br />");
            _out.println("  |_( )__| |_( )__| |_( )__|</nobr></pre>");
            _out.println("<hr/>");
            _out.println("<h2>You are now logged out </h3>");
            _out.println("<a href='"+ request.getContextPath() + "/Main'>Login Again</a>");
            _out.println("</body>");
            _out.println("</html>");
        } // end:try 
        finally {            
            _out.close();
        } // end:finally
    } // end:processRequest

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
    }

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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}  // end:Logout
