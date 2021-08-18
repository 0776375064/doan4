package entities;

import entities.Accounts;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(Advertise.class)
public class Advertise_ { 

    public static volatile SingularAttribute<Advertise, Accounts> accountID;
    public static volatile SingularAttribute<Advertise, String> thumbnail;
    public static volatile SingularAttribute<Advertise, String> description;
    public static volatile SingularAttribute<Advertise, Integer> advertiseID;
    public static volatile SingularAttribute<Advertise, String> advertiseName;
    public static volatile SingularAttribute<Advertise, String> url;

}