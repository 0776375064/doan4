package entities;

import entities.Accounts;
import entities.Songs;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(FavouriteAlbum.class)
public class FavouriteAlbum_ { 

    public static volatile SingularAttribute<FavouriteAlbum, String> albumName;
    public static volatile SingularAttribute<FavouriteAlbum, Accounts> accountID;
    public static volatile SingularAttribute<FavouriteAlbum, Integer> fvID;
    public static volatile SingularAttribute<FavouriteAlbum, Songs> songID;

}