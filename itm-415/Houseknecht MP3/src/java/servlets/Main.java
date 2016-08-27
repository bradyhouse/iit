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

import domain.Doctor;
import domain.Encounter;
import domain.Observation;
import domain.Patient;
import domain.ejb.DoctorBean;
import domain.ejb.PatientBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Authenticated user interface. Provides
 * different functionality depending on
 * the type of user.
 * 
 * @author Brady House
 */
@WebServlet(name = "Main", urlPatterns = {"/Main"})
public class Main extends HttpServlet {
    @EJB
    private PatientBean patientBean;
    @EJB
    private DoctorBean doctorBean;
    
    
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

        Patient _patient = null;
        Doctor _doctor = null;

        if (request.isUserInRole("DOCTOR")) {
            _doctor = this.doctorBean.findByUserName(request.getRemoteUser());
        } // end:if

        if (request.isUserInRole("PATIENT")) {
            _patient = this.patientBean.findByUserName(request.getRemoteUser());
        } // end:if

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter _out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            _out.println("<!DOCTYPE html>");
            _out.println("<html>");
            _out.println("<head>");
            _out.println("<title>Main</title>");
            _out.println("</head>");
            _out.println("<body>");
            _out.println("<pre><nobr>     _        _        _    <br />");
            _out.println("   _( )__   _( )__   _( )__ <br />");
            _out.println(" _|     _|_|     _|_|     _|<br />");
            _out.println("(_ M _ (_(_ P _ (_(_ 3 _ (_ <br />");
            _out.println("  |_( )__| |_( )__| |_( )__|</nobr></pre>");
            _out.println("<hr/>");
            if (request.isUserInRole("PATIENT")) {
                _out.println("<h2>Welcome, " + _patient.getFirstName() + " " 
                        + _patient.getLastName() +
                     "</h2>");
                if (_patient.getDoctors().size() > 0) {
                    _out.println("<p>You have the following physicians:");
                    _out.println("<ul>");
                    for (Doctor _patientDoctor : _patient.getDoctors()) {
                        _out.println("<li>Dr. " + _patientDoctor.getLastName() + ", " + 
                                _patientDoctor.getSpeciality().toUpperCase());
                        
                        if(_patient.getEncounters().size() > 0) {
                            for(Encounter _patientDoctorEncounter : _patient.getEncounters()) {
                                if(_patientDoctorEncounter.getDoctor().getId() == _patientDoctor.getId()) {
                                    DateFormat _dateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                                    String _encounterDateStr = _dateFormatter.format(_patientDoctorEncounter.getCreateDate());
                                    _out.println("<ul><li><i>Encountered On</i> " + _encounterDateStr);
                                    _out.println("<ul><li>Afterwards, you said--");
                                    for(Observation _patientObservation : _patientDoctorEncounter.getObservations())
                                    {
                                        if(!_patientObservation.getIsDoctor())
                                        {
                                            _out.println("<li><i>" + _patientObservation.getDescription() + "</i></li>");
                                        } // end:if
                                     } // end:for
                                    _out.println("</li></ul>");
                                    _out.println("</li></ul>");
                                } // end:if
                            } // end:for
                        } // end:if
                        _out.println("</li>");
                    } // end:for
                    _out.println("</ul>");
                } // end:if
            } // end:if

            if (request.isUserInRole("DOCTOR")) {
                _out.println("<h2>Welcome, Dr. " + _doctor.getLastName() + "</h2>");
               
                if (_doctor.getPatients().size() > 0) {
                    _out.println("<p>You have the following patients:");
                    _out.println("<ul>");
                    for (Patient _doctorPatient : _doctor.getPatients()) {
                        _out.println("<li>Patient #" + _doctorPatient.getId() + ", " + _doctorPatient.getLastName() + ", " +
                                _doctorPatient.getFirstName());
                         if(_doctorPatient.getEncounters().size() > 0) {
                            for(Encounter _doctorPatientEncounter : _doctorPatient.getEncounters()) {
                                DateFormat _dateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                                String _encounterDateStr = _dateFormatter.format(_doctorPatientEncounter.getCreateDate());
                                _out.println("<ul><li><i>Encountered On</i> " + _encounterDateStr);
                                _out.println("<ul><li>Afterwards, you said--");
                                for(Observation _doctorObservation : _doctorPatientEncounter.getObservations())
                                {
                                    if(_doctorObservation.getIsDoctor())
                                    {
                                        _out.println("<li><i>" + _doctorObservation.getDescription() + "</i></li>");
                                    } // end:if
                                } // end:for
                                _out.println("</li></ul>");
                                _out.println("</li></ul>");
                            } // end:for
                            _out.println("</li>");
                        } // end:if
                         else {
                             _out.println("<ul><li>No Encounters</li><ul></li>");
                         } // end:else
                    } // end:for
                    _out.println("</ul>");
                } // end:if
            } // end:if
            _out.println("<br/><a href='" + request.getContextPath() + "/logout'>Logout</a>");
            _out.println("<br/><a href=\"../javadoc/index.html\" target=\"_blank\">javadocs</a>");
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
} // end:Main
