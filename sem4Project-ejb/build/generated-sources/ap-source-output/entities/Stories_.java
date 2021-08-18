package entities;

import entities.Accounts;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(Stories.class)
public class Stories_ { 

    public static volatile SingularAttribute<Stories, Integer> storyID;
    public static volatile SingularAttribute<Stories, Accounts> accountID;
    public static volatile SingularAttribute<Stories, Integer> created;
    public static volatile SingularAttribute<Stories, Integer> feeling;
    public static volatile SingularAttribute<Stories, String> message;
    public static volatile SingularAttribute<Stories, Integer> songID;

}