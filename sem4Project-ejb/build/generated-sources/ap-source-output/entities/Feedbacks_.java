package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(Feedbacks.class)
public class Feedbacks_ { 

    public static volatile SingularAttribute<Feedbacks, Date> createdAt;
    public static volatile SingularAttribute<Feedbacks, Integer> feedbackID;
    public static volatile SingularAttribute<Feedbacks, String> description;
    public static volatile SingularAttribute<Feedbacks, String> title;
    public static volatile SingularAttribute<Feedbacks, String> email;

}