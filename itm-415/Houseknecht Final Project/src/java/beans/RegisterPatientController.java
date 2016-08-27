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

import domain.Doctor;
import domain.Patient;
import domain.ejb.DoctorBean;
import domain.ejb.PatientBean;
import domain.register.NewPatientUser;
import domain.ejb.security.GroupBean;
import domain.ejb.security.UserBean;
import domain.security.Group;
import domain.security.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * New User register page
 * controller class.  This
 * class handles the process
 * of creating a new user.
 * 
 * @author Brady Houseknecht
 */
@Named
@RequestScoped
public class RegisterPatientController {
    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());
    private FacesContext context = FacesContext.getCurrentInstance();
    private NewPatientUser user = new NewPatientUser();
    private String doctorUserName = "";
   
    @EJB
    private UserBean userBean = new UserBean();
    @EJB
    private PatientBean patientBean = new PatientBean();
    @EJB
    private DoctorBean doctorBean = new DoctorBean();
    
    
    /**
     * Method used to cancel the login
     * process and redirect the user to the
     * login form.
     * 
     * @return String value equal to the login.xhtml page.
     */
    public String doCancel()
    {
        return "/secure/admin.xhtml";
    } // end:doCancel
    
    /**
     * Get the value of doctor's username
     *
     * @return the value of doctor's username
     */
    public String getDoctorUsername() {
        return this.doctorUserName;
    } // end:getDoctorUserName

    /**
     * Set the value of Doctor's username
     *
     * @param username new value of doctor's username
     */
    public void setDoctorUsername(String username) {
        this.doctorUserName = username;
    } // end:setDoctorUsername

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return this.user.getPassword();
    } // end:getPassword
    
    /**
     * Set the value of the user's password.
     * 
     * @param password new value of user's password.
     */
    public void setPassword(String password) {
        
        this.user.setPassword(password);
    } // end:setPassword
    
    public String doAddPatientUser() {
       
         HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
         
          
         try {
        
            Group _PatientGroup = new Group("PATIENTS", "Members of this group are patients");
            
            domain.security.User _user = new domain.security.User();
            
            _user.setUserName(this.user.getUserName());
            _user.setPassword(this.user.getPassword());
            
            _user.addGroup(_PatientGroup);
            userBean.create(_user);
            
            context.addMessage(null, new FacesMessage("doctorUserName: " + this.doctorUserName));
           
            Doctor _doctor = this.doctorBean.findByUserName(this.doctorUserName);
           
            context.addMessage(null, new FacesMessage("Found doctor ...<br />"));
           
            Patient _patient = new Patient();
            
            _patient.setFirstName(this.user.getFirstName());
            _patient.setLastName(this.user.getLastName());
            _patient.setBirthDate(this.user.getBirthDate());
            _patient.setStreetAddress(this.user.getStreetAddress());
            _patient.setCity(this.user.getCity());
            _patient.setState(this.user.getState());
            _patient.setCountry("United States");
            _patient.setZip(this.user.getZip());
            _patient.setPhoneNumber(this.user.getPhoneNumber());
            _patient.setProvider(this.user.getProvider());
            _patient.setSubscriberGroup(this.user.getSubscriberGroup());
            _patient.setSubscriberId(this.user.getSubscriberId());
            _patient.setUser(_user);
            _patient.addDoctor(_doctor);
            
            this.patientBean.create(_patient);
            
            context.addMessage(null, new FacesMessage("Patient Created!"));
            return "/secure/admin.xhtml";
    
         } // end:try
         catch (Exception err){
                
            context.addMessage(null, new FacesMessage(err.getLocalizedMessage()));
            LOG.log(Level.SEVERE, null, err);
            return "/secure/registerNewPatient.xhtml";
        } // end:catch
       
    } // end:doAddPatientUser
    
    public String doAddDoctorUser() {
        Group _doctorGroup = new Group("DOCTORS", "Members of this group are patients");
        domain.security.User _user = new domain.security.User();
        _user.setUserName(this.user.getUserName());
        _user.setPassword(this.user.getPassword());
        _user.addGroup(_doctorGroup);
        userBean.create(_user);
        context.addMessage(null, new FacesMessage("Doctor Created!"));
        return "/secure/admin.xhtml";
    } // end:doAddDoctorUser
    
    
    /**
     * Get the value of the user
     *
     * @return the value of owner
     */
    public NewPatientUser getUser() {
           return this.user;
    } // end:getUser

    /**
     * Set the value of the user
     *
     * @param NewPatientUser new value of user
     */
    public void setUser(NewPatientUser pUser) {
        this.user = pUser;
    } // end:setUser

} // end:RegisterPatientController
