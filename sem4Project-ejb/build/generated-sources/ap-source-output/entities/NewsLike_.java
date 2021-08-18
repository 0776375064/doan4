package entities;

import entities.News;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(NewsLike.class)
public class NewsLike_ { 

    public static volatile SingularAttribute<NewsLike, Integer> accountID;
    public static volatile SingularAttribute<NewsLike, News> newID;
    public static volatile SingularAttribute<NewsLike, Integer> newLikeID;

}