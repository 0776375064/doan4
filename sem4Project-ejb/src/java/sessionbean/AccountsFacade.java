/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Accounts;
import entities.Artists;
import entities.News;
import entities.Songs;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author asus
 */
@Stateless
public class AccountsFacade extends AbstractFacade<Accounts> implements AccountsFacadeLocal {

    @EJB
    private ArtistsFacadeLocal artistsFacade;

    @EJB
    private ArtistFollowFacadeLocal artistFollowFacade;

    @EJB
    private NewsFacadeLocal newsFacade;

    @EJB
    private SongsFacadeLocal songsFacade;

    @PersistenceContext(unitName = "sem4Test-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountsFacade() {
        super(Accounts.class);
    }

    @Override
    public int checkSongLike(int accountID, int songID) {
        Query query = em.createQuery("SELECT i FROM SongLikes i WHERE i.accountID = :accountID and i.songID = :songID");
        Accounts account = AccountsFacade.this.find(accountID);
        Songs song = songsFacade.find(songID);
        query.setParameter("accountID", account);
        query.setParameter("songID", song);
        return query.getResultList().size();
    }

    @Override
    public int deleteLike(int accountID, int songID) {
        Query query = em.createQuery("DELETE FROM SongLikes i WHERE i.accountID = :accountID and i.songID = :songID");
        Accounts account = AccountsFacade.this.find(accountID);
        Songs song = songsFacade.find(songID);
        query.setParameter("accountID", account);
        query.setParameter("songID", song);
        return query.executeUpdate();
    }

    @Override
    public int checkArtistFollow(int accountID, int artistID) {
        Query query = em.createQuery("SELECT i FROM ArtistFollow i WHERE i.accountID = :accountID and i.artistID = :artistID");
        Accounts account = AccountsFacade.this.find(accountID);
        Artists artist = artistsFacade.find(artistID);
        query.setParameter("accountID", account);
        query.setParameter("artistID", artist);
        return query.getResultList().size();
    }

    @Override
    public int deleteFollow(int accountID, int artistID) {
        Query query = em.createQuery("DELETE FROM ArtistFollow i WHERE i.accountID = :accountID and i.artistID = :artistID");
        Accounts account = AccountsFacade.this.find(accountID);
        Artists artist = artistsFacade.find(artistID);
        query.setParameter("accountID", account);
        query.setParameter("artistID", artist);
        return query.executeUpdate();
    }

    @Override
    public int checkLogin(String username, String password) {
        Query query = em.createQuery("SELECT c FROM Accounts c WHERE c.username = :username AND c.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Accounts> list = query.getResultList();
        if (list.size() == 0) {
            return 0;
        } else {
            return list.get(0).getAccountID();
        }
    }

    // find customer by username
    @Override
    public int findByUsername(String username) {
        Query query = em.createNamedQuery("Accounts.findByUsername");
        query.setParameter("username", username);
        List<Accounts> list = query.getResultList();
        return list.size();
    }

    @Override
    public int checkNewsLike(int accountID, int newID) {
        Query query = em.createQuery("SELECT i FROM Comment i WHERE i.accountID = :accountID and i.newID = :newID");
        Accounts account = AccountsFacade.this.find(accountID);
        News news = newsFacade.find(newID);
        query.setParameter("accountID", account);
        query.setParameter("newID", news);
        return query.getResultList().size();
    }

    /// my history orders
    @Override
    public List<Object[]> myOrders(int accountID, int type) {
        if (type == 0) {
            Query query = em.createNativeQuery("select o.OrderID, d.Price, o.OrderDate, o.PaymentType, o.Status, o.BankName, o.TransactionStatus, d.SongID "
                    + "FROM Orders o JOIN OrderDetails d ON o.OrderID = d.OrderID WHERE o.AccountID = ? ORDER BY o.OrderID DESC");
            query.setParameter(1, accountID);
            return query.getResultList();
        } else if (type == 1) {
            Query query = em.createNativeQuery("select o.OrderID, d.Price, o.OrderDate, o.PaymentType, o.Status, o.BankName, o.TransactionStatus, d.SongID "
                    + "FROM Orders o JOIN OrderDetails d ON o.OrderID = d.OrderID WHERE o.Status = 1 AND o.TransactionStatus = 1 AND  o.AccountID = ? ORDER BY o.OrderID DESC");
            query.setParameter(1, accountID);
            return query.getResultList();
        } else {
            Query query = em.createNativeQuery("select o.OrderID, d.Price, o.OrderDate, o.PaymentType, o.Status, o.BankName, o.TransactionStatus, d.SongID "
                    + "FROM Orders o JOIN OrderDetails d ON o.OrderID = d.OrderID WHERE o.Status = 1 AND o.TransactionStatus IS NULL AND o.AccountID = ? ORDER BY o.OrderID DESC");
            query.setParameter(1, accountID);
            return query.getResultList();
        }
    }

    @Override
    public List<Object> mySongBought(int accountID) {
        Query query = em.createNativeQuery("select d.SongID FROM Orders o JOIN OrderDetails d ON o.OrderID = d.OrderID "
                + "WHERE o.Status = 1 AND o.TransactionStatus = 1 AND o.AccountID = ? ORDER BY o.OrderID DESC");
        query.setParameter(1, accountID);
        return query.getResultList();
    }
//tuan da den day 

    @Override
    public int checkLoginOrder(String id, String password) {
        Query query = em.createNativeQuery("SELECT * FROM Accounts WHERE AccountID = ? and Password = ?");
        query.setParameter(1, id);
        query.setParameter(2, password);
        if (query.getResultList().size() == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
