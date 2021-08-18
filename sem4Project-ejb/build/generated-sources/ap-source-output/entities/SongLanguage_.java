package entities;

import entities.Songs;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(SongLanguage.class)
public class SongLanguage_ { 

    public static volatile SingularAttribute<SongLanguage, String> langName;
    public static volatile SingularAttribute<SongLanguage, String> description;
    public static volatile CollectionAttribute<SongLanguage, Songs> songsCollection;
    public static volatile SingularAttribute<SongLanguage, Integer> langID;

}