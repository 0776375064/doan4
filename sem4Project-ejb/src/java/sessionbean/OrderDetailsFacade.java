/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.OrderDetails;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author asus
 */
@Stateless
public class OrderDetailsFacade extends AbstractFacade<OrderDetails> implements OrderDetailsFacadeLocal {

    @PersistenceContext(unitName = "sem4Test-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderDetailsFacade() {
        super(OrderDetails.class);
    }
    //thong tin order details

    @Override
    public List detailOrder(int idOrder) {
        String sql = "select Orders.OrderID,DayTrading,OrderDate,Status,BankName,Orders.Price,Note, "
                + "Songs.SongID,SongName,Songs.Thumbnail,Songs.Price,Path, "
                + "OrderDetails.Price, "
                + "Artists.ArtistID,Nickname,Fullname "
                + "from Orders "
                + "full join OrderDetails on OrderDetails.OrderID = Orders.OrderID "
                + "full join Songs on Songs.SongID = OrderDetails.SongID "
                + "full join Artists on Artists.ArtistID = Songs.ArtistID "
                + "where Orders.OrderID =? ";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, idOrder);
        List<Object[]> authors = query.getResultList();
        String tam = null;
        List list = new ArrayList();
        for (Object[] a : authors) {
            tam = "[" + a[0] + "/" + a[1] + "/" + a[2] + "/" + a[3] + "/" + a[4] + "/" + a[5] + "/" + a[6] + "/" + a[7] + "/" + a[8] + "/"
                    + a[9] + "/" + a[10] + "/" + a[11] + "/" + a[12] + "/" + a[13] + "/" + a[14] + "/" + a[15] + "]";
            list.add(tam);
            tam = null;
        }
        return list;
    }

    @Override
    public List<OrderDetails> findIdOfOrder(int idOrder) {
        String sql = "select * from OrderDetails where OrderID = ? ";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, idOrder);
        return query.getResultList();
    }
}
