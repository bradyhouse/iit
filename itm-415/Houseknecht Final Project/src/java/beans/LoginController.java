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

package beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Login controller class. Handles 
 * user login / password 
 * authentication.
 * 
 * @author brady houseknecht
 */
@RequestScoped
@Named
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());
    private FacesContext context;
    private String username;
    private String password;

    public LoginController() {
        context = FacesContext.getCurrentInstance();
    } // end:constructor

    public boolean isDoctor(){
        return context.getExternalContext().isUserInRole("DOCTOR");
    } // end:isDoctor

    public boolean isPatient(){
        return context.getExternalContext().isUserInRole("PATIENT");
    } // end:isPatient

    public boolean isAdmin(){
        return context.getExternalContext().isUserInRole("ADMIN");
    } // end:isAdmin
    
    public String getRemoteUser(){
        return context.getExternalContext().getRemoteUser();
    } // end:getRemoteUser
    
    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    } // end:getUserName

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    } // end:setUsername

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    } // end:getPassword

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    } // end:setPassword

    public String login() {

        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(username, password);
        } catch (ServletException ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Login", "Invalid Login"));
            LOG.log(Level.SEVERE, null, ex);
            return "/login.xhtml";
        }

        return "/secure/welcome.xhtml";
    } // end:login
    
  
    
    public String logout() {
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bad Logout", "Bad Logout"));
            LOG.log(Level.SEVERE, null, ex);
        }
        return "/login.xhtml";
    } // end:logout
}
