package entities;

import entities.Accounts;
import entities.Categories;
import entities.Comment;
import entities.NewViews;
import entities.NewsLike;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(News.class)
public class News_ { 

    public static volatile SingularAttribute<News, Date> createdAt;
    public static volatile SingularAttribute<News, Accounts> accountID;
    public static volatile SingularAttribute<News, String> thumbnail;
    public static volatile CollectionAttribute<News, NewsLike> newsLikeCollection;
    public static volatile CollectionAttribute<News, NewViews> newViewsCollection;
    public static volatile SingularAttribute<News, Integer> newID;
    public static volatile CollectionAttribute<News, Comment> commentCollection;
    public static volatile SingularAttribute<News, String> description;
    public static volatile SingularAttribute<News, String> title;
    public static volatile SingularAttribute<News, Integer> views;
    public static volatile SingularAttribute<News, Categories> categoryID;

}