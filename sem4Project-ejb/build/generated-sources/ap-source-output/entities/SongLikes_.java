package entities;

import entities.Accounts;
import entities.Songs;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(SongLikes.class)
public class SongLikes_ { 

    public static volatile SingularAttribute<SongLikes, Accounts> accountID;
    public static volatile SingularAttribute<SongLikes, Integer> likeID;
    public static volatile SingularAttribute<SongLikes, Songs> songID;

}