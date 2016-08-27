/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HW1.beans;

import javax.ejb.Stateless;

/**
 *
 * @author Brady
 */
@Stateless
public class ResumeBean implements ResumeBeanLocal {

    @Override
    public String showResume() {
        return "Redirecting...";
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
