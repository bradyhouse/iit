package domain.security;

import domain.security.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-04T21:16:45")
@StaticMetamodel(Group.class)
public class Group_ { 

    public static volatile SingularAttribute<Group, String> groupName;
    public static volatile ListAttribute<Group, User> users;
    public static volatile SingularAttribute<Group, String> groupDescription;

}