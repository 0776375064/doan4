package entities;

import entities.Accounts;
import entities.OrderDetails;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, String> note;
    public static volatile SingularAttribute<Orders, Accounts> accountID;
    public static volatile SingularAttribute<Orders, Date> dayTrading;
    public static volatile SingularAttribute<Orders, Integer> orderID;
    public static volatile SingularAttribute<Orders, Integer> transactionStatus;
    public static volatile SingularAttribute<Orders, Integer> price;
    public static volatile CollectionAttribute<Orders, OrderDetails> orderDetailsCollection;
    public static volatile SingularAttribute<Orders, String> bankName;
    public static volatile SingularAttribute<Orders, Date> orderDate;
    public static volatile SingularAttribute<Orders, Integer> paymentType;
    public static volatile SingularAttribute<Orders, Integer> status;

}