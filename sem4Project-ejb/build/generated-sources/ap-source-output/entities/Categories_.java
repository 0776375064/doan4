package entities;

import entities.News;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(Categories.class)
public class Categories_ { 

    public static volatile CollectionAttribute<Categories, News> newsCollection;
    public static volatile SingularAttribute<Categories, String> description;
    public static volatile SingularAttribute<Categories, String> categoryName;
    public static volatile SingularAttribute<Categories, Integer> categoryID;
    public static volatile SingularAttribute<Categories, Integer> status;

}