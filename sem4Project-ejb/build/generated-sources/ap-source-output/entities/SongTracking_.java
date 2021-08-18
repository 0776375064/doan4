package entities;

import entities.Songs;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(SongTracking.class)
public class SongTracking_ { 

    public static volatile SingularAttribute<SongTracking, String> dateTracking;
    public static volatile SingularAttribute<SongTracking, Integer> listens;
    public static volatile SingularAttribute<SongTracking, Songs> songID;
    public static volatile SingularAttribute<SongTracking, Integer> songChartID;

}