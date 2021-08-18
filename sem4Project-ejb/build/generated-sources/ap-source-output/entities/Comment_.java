package entities;

import entities.Accounts;
import entities.News;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SingularAttribute<Comment, Integer> createdAt;
    public static volatile SingularAttribute<Comment, Accounts> accountID;
    public static volatile SingularAttribute<Comment, News> newID;
    public static volatile SingularAttribute<Comment, Integer> commentID;
    public static volatile SingularAttribute<Comment, String> message;

}