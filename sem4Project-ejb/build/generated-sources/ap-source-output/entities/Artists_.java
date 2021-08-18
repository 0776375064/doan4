package entities;

import entities.Albums;
import entities.ArtistFollow;
import entities.ArtistInSong;
import entities.Songs;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(Artists.class)
public class Artists_ { 

    public static volatile SingularAttribute<Artists, String> birthday;
    public static volatile CollectionAttribute<Artists, Albums> albumsCollection;
    public static volatile SingularAttribute<Artists, String> nationality;
    public static volatile SingularAttribute<Artists, String> achievement;
    public static volatile CollectionAttribute<Artists, ArtistInSong> artistInSongCollection;
    public static volatile SingularAttribute<Artists, String> nickname;
    public static volatile SingularAttribute<Artists, String> description;
    public static volatile CollectionAttribute<Artists, Songs> songsCollection;
    public static volatile SingularAttribute<Artists, Integer> artistID;
    public static volatile CollectionAttribute<Artists, ArtistFollow> artistFollowCollection;
    public static volatile SingularAttribute<Artists, String> avatar;
    public static volatile SingularAttribute<Artists, String> fullname;

}