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
package domain.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Entity that virtualizes a 
 * single authenticated user.
 * 
 * @author Brady House
 */
@Entity
@Table(name="mp3_User")
@Named 
public class User implements Serializable {
    @Id
    private String userName;
    private String password;
    @ManyToMany
    @JoinTable(name="mp3_UserLink", 
            joinColumns=@JoinColumn(name="USERNAME"),
            inverseJoinColumns=@JoinColumn(name="GROUPNAME"))
    private List<Group> groups;
    /**
     * Default class constructor.
     */
    public User() {
        this.groups = new ArrayList<>();
    } // end:constructor
    /**
     * Overloaded class constructor, which accepts the
     * UserName and Password values that class instance
     * should be initialized with.
     * 
     * @param userName string value equal to the UserName
     * @param password string value equal to the Password
     */
    public User(String userName, String password) {
        this.groups = new ArrayList<>();
        this.userName = userName;
        this.password = password;
    } // end:constructor
    /**
     * UserName getter.
     * 
     * @return string value equal to the UserName
     */
    public String getUserName() {
        return userName;
    } // end:getUserName
    /**
     * UserName setter.
     * 
     * @param username string value equal to the new UserName value.
     */
    public void setUserName(String username) {
        this.userName = username;
    } // end:setUserName
    /**
     * Password getter.
     * 
     * @return string value equal to the Password.
     */
    public String getPassword() {
        return password;
    } // end:getPassword
    /**
     * Setter used to assign the user's password.
     * 
     * @param password string value equal to the user's password
     */
    public void setPassword(String password) {
        this.password = password;
    } // end:setPassword    
    /**
     * Groups list getter.
     * 
     * @return list of groups that the user belongs too.
     */
    public List<Group> getGroups() {
        return groups;
    } // end:getGroups
    /**
     * Groups list setter.
     * 
     * @param groups list of groups that the user belongs too.
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    } // end:setGroups
    /**
     * Control method used to "synchronously" add a group
     * to the user's group list and the user to the
     * group's user list.
     * 
     * @param group class instance
     */
    public void addGroup(Group group) {
        if (!this.groups.contains(group)) {
            this.groups.add(group);
        } // end:if
        if (!group.getUsers().contains(this)) {
            group.getUsers().add(this);
        } // end:if
    } // end:addGroup
    /**
     * Control function used to encrypt
     * the password value prior to its persistence
     * to the database. 
     */
    @PrePersist
    @PreUpdate
    private void EncryptPassword(){
        String _encryptedPassword = DigestUtils.md5Hex(this.password);
        this.password = _encryptedPassword;
    } // end:EncryptPassword
    /**
     * Override toString. Returns a comma 
     * delimited representation of the class
     * attributes.
     * 
     * @return comma delimited string of all class attributes.
     */
    @Override
    public String toString() {
        return "User{" + "userName=" + userName + ", password=" + password + ", groups=" + groups + '}';
    } // end:toString
} // end:User
