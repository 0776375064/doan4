package entities;

import entities.Orders;
import entities.Songs;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-04T16:22:04")
@StaticMetamodel(OrderDetails.class)
public class OrderDetails_ { 

    public static volatile SingularAttribute<OrderDetails, Orders> orderID;
    public static volatile SingularAttribute<OrderDetails, Integer> price;
    public static volatile SingularAttribute<OrderDetails, Integer> detailID;
    public static volatile SingularAttribute<OrderDetails, Integer> discount;
    public static volatile SingularAttribute<OrderDetails, Songs> songID;

}