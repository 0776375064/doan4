package entities;

import entities.Advertise;
import entities.ArtistFollow;
import entities.Comment;
import entities.FavouriteAlbum;
import entities.News;
import entities.Orders;
import entities.SongLikes;
import entities.SongReview;
import entities.Stories;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(Accounts.class)
public class Accounts_ { 

    public static volatile CollectionAttribute<Accounts, Advertise> advertiseCollection;
    public static volatile CollectionAttribute<Accounts, SongReview> songReviewCollection;
    public static volatile SingularAttribute<Accounts, String> address;
    public static volatile SingularAttribute<Accounts, String> role;
    public static volatile SingularAttribute<Accounts, String> gender;
    public static volatile CollectionAttribute<Accounts, News> newsCollection;
    public static volatile CollectionAttribute<Accounts, Comment> commentCollection;
    public static volatile CollectionAttribute<Accounts, ArtistFollow> artistFollowCollection;
    public static volatile SingularAttribute<Accounts, String> avatar;
    public static volatile CollectionAttribute<Accounts, Orders> ordersCollection;
    public static volatile SingularAttribute<Accounts, String> favourite;
    public static volatile SingularAttribute<Accounts, Integer> accountID;
    public static volatile SingularAttribute<Accounts, String> password;
    public static volatile SingularAttribute<Accounts, Integer> money;
    public static volatile CollectionAttribute<Accounts, SongLikes> songLikesCollection;
    public static volatile CollectionAttribute<Accounts, Stories> storiesCollection;
    public static volatile SingularAttribute<Accounts, String> phone;
    public static volatile SingularAttribute<Accounts, String> fullname;
    public static volatile CollectionAttribute<Accounts, FavouriteAlbum> favouriteAlbumCollection;
    public static volatile SingularAttribute<Accounts, String> email;
    public static volatile SingularAttribute<Accounts, String> username;
    public static volatile SingularAttribute<Accounts, Integer> status;

}