/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Accounts;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface AccountsFacadeLocal {

    void create(Accounts accounts);

    void edit(Accounts accounts);

    void remove(Accounts accounts);

    Accounts find(Object id);

    List<Accounts> findAll();

    List<Accounts> findRange(int[] range);

    int count();

    public int checkSongLike(int accountID, int songID);

    public int deleteLike(int accountID, int songID);

    public int findByUsername(String username);

    public int checkLogin(String username, String password);

    public int checkNewsLike(int accountID, int newID);

    public List<Object[]> myOrders(int accountID, int type);

    public List<Object> mySongBought(int accountID);

    public int checkArtistFollow(int accountID, int artistID);

    public int deleteFollow(int accountID, int artistID);

    public int checkLoginOrder(String id, String password);
    
}
