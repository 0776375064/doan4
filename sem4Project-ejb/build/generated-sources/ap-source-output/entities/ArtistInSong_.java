package entities;

import entities.Artists;
import entities.Songs;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(ArtistInSong.class)
public class ArtistInSong_ { 

    public static volatile SingularAttribute<ArtistInSong, String> role;
    public static volatile SingularAttribute<ArtistInSong, Artists> artistID;
    public static volatile SingularAttribute<ArtistInSong, Songs> songID;
    public static volatile SingularAttribute<ArtistInSong, Integer> workID;

}