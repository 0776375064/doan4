package entities;

import entities.Albums;
import entities.ArtistInSong;
import entities.Artists;
import entities.FavouriteAlbum;
import entities.OrderDetails;
import entities.SongLanguage;
import entities.SongLikes;
import entities.SongReview;
import entities.SongTracking;
import entities.Suppliers;
import entities.TypeOfSong;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(Songs.class)
public class Songs_ { 

    public static volatile SingularAttribute<Songs, String> songName;
    public static volatile CollectionAttribute<Songs, SongReview> songReviewCollection;
    public static volatile SingularAttribute<Songs, String> thumbnail;
    public static volatile SingularAttribute<Songs, Suppliers> supplierID;
    public static volatile CollectionAttribute<Songs, ArtistInSong> artistInSongCollection;
    public static volatile SingularAttribute<Songs, Date> release;
    public static volatile CollectionAttribute<Songs, OrderDetails> orderDetailsCollection;
    public static volatile SingularAttribute<Songs, String> description;
    public static volatile SingularAttribute<Songs, Albums> albumID;
    public static volatile SingularAttribute<Songs, Artists> artistID;
    public static volatile SingularAttribute<Songs, String> path;
    public static volatile CollectionAttribute<Songs, SongLikes> songLikesCollection;
    public static volatile SingularAttribute<Songs, Integer> price;
    public static volatile CollectionAttribute<Songs, SongTracking> songTrackingCollection;
    public static volatile SingularAttribute<Songs, TypeOfSong> typeID;
    public static volatile SingularAttribute<Songs, SongLanguage> langID;
    public static volatile SingularAttribute<Songs, Integer> songID;
    public static volatile SingularAttribute<Songs, String> lyrics;
    public static volatile CollectionAttribute<Songs, FavouriteAlbum> favouriteAlbumCollection;
    public static volatile SingularAttribute<Songs, Integer> views;
    public static volatile SingularAttribute<Songs, Integer> typeAlbum;

}