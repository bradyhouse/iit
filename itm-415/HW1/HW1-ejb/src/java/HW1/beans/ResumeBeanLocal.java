/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HW1.beans;

import javax.ejb.Local;

/**
 *
 * @author Brady
 */
@Local
public interface ResumeBeanLocal {

    String showResume();
    
}
