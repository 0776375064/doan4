package entities;

import entities.News;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(NewViews.class)
public class NewViews_ { 

    public static volatile SingularAttribute<NewViews, Date> createdAt;
    public static volatile SingularAttribute<NewViews, Integer> view;
    public static volatile SingularAttribute<NewViews, Integer> viewID;
    public static volatile SingularAttribute<NewViews, News> newID;

}