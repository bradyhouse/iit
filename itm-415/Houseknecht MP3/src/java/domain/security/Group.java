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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
/**
 * Entity that virtualizes a 
 * collection of authenticated
 * users.
 * 
 * @author Brady House
 */
@Entity
@Table(name = "mp3_Group")
public class Group implements Serializable {
    @Id
    private String groupName;
    private String groupDescription;
    @ManyToMany(mappedBy = "groups")
    private List<User> users = new ArrayList<>();
    /**
     * Default class constructor.
     */
    public Group() {
    } // end:constructor
    /**
     * Overloaded class constructor which accepts
     * the value of the groupName and groupDescription
     * that should be used to initialize the class
     * instance.
     * 
     * @param groupName string value equal to the groupName.
     * @param groupDescription string value equal to the groupDescription.
     */
    public Group(String groupName, String groupDescription) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    } // end:constructor
    /**
     * GroupName getter.
     * 
     * @return string value equal to the GroupName.
     */
    public String getGroupName() {
        return groupName;
    } // end:getGroupName
    /**
     * GroupName setter.
     * 
     * @param groupName string value equal to the GroupName.
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    } // end:setGroupName
    /**
     * GroupDescription getter.
     * 
     * @return string value equal to the GroupDescription. 
     */
    public String getGroupDescription() {
        return groupDescription;
    } // end:getGroupDescription
    /**
     * GroupDescription setter.
     * 
     * @param groupDescription string value equal to the GroupDescription.
     */
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    } // end:setGroupDescription
    /**
     * User List getter.
     * 
     * @return list of the users belonging to the group.
     */
    public List<User> getUsers() {
        return users;
    } // end:getUsers
    /**
     * User List setter.
     * 
     * @param users list of users belonging to the group.
     */
    public void setUsers(List<User> users) {
        this.users = users;
    } // end:setUsers
    /**
     * Control method used to "synchronously" add a user
     * to the group's user list and the group to the
     * user's group list.
     * 
     * @param user class instance
     */
    public void addUser(User user) {
        if (!this.users.contains(user)) {
            this.users.add(user);
        }
        if (!user.getGroups().contains(this)) {
            user.getGroups().add(this);
        }
    } // end:addUser
    /**
     * Override toString. Returns a comma 
     * delimited representation of the class
     * attributes.
     * 
     * @return comma delimited string of all class attributes
     */
    @Override
    public String toString() {
        return "Group{" + "groupName=" + groupName + ", groupDescription=" + groupDescription + '}';
    } // end:toString
 } // end:Group
