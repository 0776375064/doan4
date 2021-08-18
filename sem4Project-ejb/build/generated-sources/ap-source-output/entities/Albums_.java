package entities;

import entities.Artists;
import entities.Songs;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(Albums.class)
public class Albums_ { 

    public static volatile SingularAttribute<Albums, String> albumName;
    public static volatile SingularAttribute<Albums, String> thumbnail;
    public static volatile SingularAttribute<Albums, Date> release;
    public static volatile SingularAttribute<Albums, Integer> albumID;
    public static volatile CollectionAttribute<Albums, Songs> songsCollection;
    public static volatile SingularAttribute<Albums, Artists> artistID;

}