package entities;

import entities.Songs;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(Suppliers.class)
public class Suppliers_ { 

    public static volatile SingularAttribute<Suppliers, Integer> supplierID;
    public static volatile SingularAttribute<Suppliers, String> address;
    public static volatile SingularAttribute<Suppliers, String> hompage;
    public static volatile SingularAttribute<Suppliers, String> phone;
    public static volatile SingularAttribute<Suppliers, String> contactName;
    public static volatile SingularAttribute<Suppliers, String> companyName;
    public static volatile CollectionAttribute<Suppliers, Songs> songsCollection;
    public static volatile SingularAttribute<Suppliers, String> fax;

}