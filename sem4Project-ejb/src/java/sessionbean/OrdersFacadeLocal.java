/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entities.Orders;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface OrdersFacadeLocal {

    void create(Orders orders);

    void edit(Orders orders);

    void remove(Orders orders);

    Orders find(Object id);

    List<Orders> findAll();

    List<Orders> findRange(int[] range);

    String  orderIdEnd();
    
    String sumPriceOderAll(String start,String end);
    
    String sumPriceOderAllNo(String start,String end);
    
    List PriceAll(String start,String end);
    
    String countOrderNo(String start,String end); 
    
    String countOrderNotIs(String start,String end); 
    
    String sumPriceTop(String start,String end);
    
    List sumSongNameTop(String start,String end);
    
    
    List listSong(String start,String end);
    
      
    
    List sumOrrder(String start,String end);
    
    
    List nformationOrder(String start,String end);
    int count();

    public String getTransactionStatus(String idOrder);

    public String getNote(String idOrder);

    public String sunUnpaid(String s, String e);

    public String sunPaid(String s, String e);

    public String sunAll(String s, String e);

    public List PriceAllAccount(String start, String end);

    public String countOrderNotIsAccount(String start, String end);

    public List chartMoney(String start, String end);

    public String countMoneyt(String start, String end);

    public String sumMoney(String start, String end);

    public String sumCanceled(String s, String e);

    public String countOrderAdmin(String start, String end);

    public List PriceAllAdmin(String start, String end);

}
