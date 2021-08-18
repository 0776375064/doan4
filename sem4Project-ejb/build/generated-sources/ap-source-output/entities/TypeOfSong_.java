package entities;

import entities.Songs;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(TypeOfSong.class)
public class TypeOfSong_ { 

    public static volatile SingularAttribute<TypeOfSong, String> thumbnail;
    public static volatile SingularAttribute<TypeOfSong, String> typeName;
    public static volatile SingularAttribute<TypeOfSong, String> description;
    public static volatile CollectionAttribute<TypeOfSong, Songs> songsCollection;
    public static volatile SingularAttribute<TypeOfSong, Integer> typeID;

}