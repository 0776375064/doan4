package entities;

import entities.Accounts;
import entities.Songs;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(SongReview.class)
public class SongReview_ { 

    public static volatile SingularAttribute<SongReview, Integer> createdAt;
    public static volatile SingularAttribute<SongReview, Accounts> accountID;
    public static volatile SingularAttribute<SongReview, String> message;
    public static volatile SingularAttribute<SongReview, Integer> reviewID;
    public static volatile SingularAttribute<SongReview, Songs> songID;

}