/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.SongReview;
import entities.Songs;
import java.time.LocalDate;
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
public class SongsFacade extends AbstractFacade<Songs> implements SongsFacadeLocal {

    @PersistenceContext(unitName = "sem4Test-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SongsFacade() {
        super(Songs.class);
    }

    // pagination
    @Override
    public List<Songs> Pagination(int pageNumber) {
        Query query = em.createQuery("SELECT p FROM Songs p ORDER BY p.songID DESC");
        int pageSize = 10;
        query.setMaxResults(pageSize);
        query.setFirstResult((pageNumber - 1) * pageSize);
        return query.getResultList();
    }

    @Override
    public int countPage() {
        int sum = SongsFacade.this.count();
        int result = (int) Math.ceil((double) sum / 10); // lam tròn
        //System.out.println(result);
        return result;
    }

    @Override
    public int countOrders(int SongID) {
        Query query = em.createQuery("SELECT i FROM OrderDetails i WHERE i.songID = :songID");
        Songs song = SongsFacade.this.find(SongID);
        query.setParameter("songID", song);
        return query.getResultList().size();
    }

    @Override
    public int countLikeSong(int songID) {
        Query query = em.createQuery("SELECT i FROM SongLikes i WHERE i.songID = :songID");
        Songs song = SongsFacade.this.find(songID);
        query.setParameter("songID", song);
        return query.getResultList().size();
    }

    @Override
    public List<Songs> getLatestSonsg() {
        Query query = em.createQuery("SELECT p FROM Songs p ORDER BY p.songID DESC");
        query.setMaxResults(100);
        return query.getResultList();
    }

    @Override
    public List<Songs> findByNames(String s) {
        if (s.isEmpty()) {
            return null;
        } else {
            Query query = em.createQuery("SELECT p FROM Songs p WHERE p.songName LIKE :songName");
            query.setParameter("songName", "%" + s + "%");
            return query.getResultList();
        }
    }

    @Override
    public List<Object[]> getStatistic(String fdate, String tdate) {
        LocalDate s = LocalDate.parse(fdate);
        LocalDate e = LocalDate.parse(tdate);
        Query query = em.createNativeQuery("SELECT d.SongID, COUNT(o.OrderID) as 'total' "
                + "FROM Orders o JOIN OrderDetails d ON o.OrderID = d.OrderID WHERE o.Status = 1 AND o.TransactionStatus = 1 AND o.OrderDate "
                + "BETWEEN ?1 and ?2 GROUP BY d.SongID ORDER BY COUNT(d.DetailID) DESC");
        query.setParameter(1, s.plusDays(-1));
        query.setParameter(2, e.plusDays(1));
        return query.getResultList();
    }

    @Override
    public List<SongReview> getSongReview(int songID) {
        Query query = em.createQuery("SELECT i FROM SongReview i WHERE i.songID = :songID ORDER BY i.reviewID DESC");
        Songs song = SongsFacade.this.find(songID);
        query.setParameter("songID", song);
        return query.getResultList();
    }

    @Override
    public List<String> getDateOrder(String fdate, String tdate) {
        LocalDate s = LocalDate.parse(fdate);
        LocalDate e = LocalDate.parse(tdate);
        Query query = em.createNativeQuery("SELECT CONVERT(varchar, OrderDate, 101) as 'date' FROM Orders o "
                + "WHERE OrderDate BETWEEN ?1 and ?2 GROUP BY  CONVERT(varchar, OrderDate, 101)");
        query.setParameter(1, s.plusDays(-1));
        query.setParameter(2, e.plusDays(1));
        return query.getResultList();
    }

    /**
     *
     * @param date
     * @return
     */
    @Override
    public int countOrdersInDay(String date) {
        try {
            Query query = em.createNativeQuery("SELECT COUNT(OrderID) FROM dbo.Orders where  datediff(day, DayTrading, ?1) = 0 and Status=1 and TransactionStatus=1");
            query.setParameter(1, date);
            //query.setParameter(2, date);
            return (int) query.getSingleResult();
        } catch (Exception ex) {
            return 0;
        }
    }

    /////////////////////// tuan's code -------------------------------------
    public List listSong() {
        String sql = "select Songs.SongID,AccountID,SongName,Path,Songs.Price,Songs.ArtistID,Artists.Fullname,Songs.Thumbnail,TransactionStatus,Status "
                + "from Songs join Artists on Songs.ArtistID = Artists.ArtistID  "
                + "full  join Suppliers on Suppliers.SupplierID= Songs.SupplierID  "
                + "full join OrderDetails on OrderDetails.SongID = Songs.SongID   "
                + "full  join Orders on Orders.OrderID= OrderDetails.OrderID ";
        Query query = em.createNativeQuery(sql);
        List<Object[]> authors = query.getResultList();
        String tam = null;
        List list = new ArrayList();
        for (Object[] a : authors) {
            tam = "[" + a[0] + "/" + a[1] + "/" + a[2] + "/" + a[3] + "/" + a[4] + "/" + a[5] + "/" + a[6] + "/" + a[7] + "/" + a[8] + "/" + a[9] + "]";
            list.add(tam);
            tam = null;
        }
        return list;
    }

    @Override
    public List listSongAll() {
        return listSong();
    }

    public List listIDSongOrder1(String AccID) {
        String sql = " select Songs.SongID "
                + " from Songs "
                + " full join Artists on Songs.ArtistID = Artists.ArtistID "
                + " full join Suppliers on Suppliers.SupplierID= Songs.SupplierID  "
                + " full join OrderDetails on OrderDetails.SongID = Songs.SongID "
                + " full join Orders on Orders.OrderID= OrderDetails.OrderID "
                + " where Orders.TransactionStatus =1 and Orders.Status=1 and AccountID=?";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, AccID);
        return query.getResultList();
    }

    @Override
    public List listIDSongOrder(String AccID) {
        return listIDSongOrder1(AccID);
    }

    @Override
    public List listSongAllIDArtistID(String ArtistID) {
        String sql = "select Songs.SongID,AccountID,SongName,Path,Songs.Price,Songs.ArtistID,Artists.Fullname,Songs.Thumbnail,TransactionStatus,Status "
                + "from Songs join Artists on Songs.ArtistID = Artists.ArtistID  "
                + "full  join Suppliers on Suppliers.SupplierID= Songs.SupplierID  "
                + "full join OrderDetails on OrderDetails.SongID = Songs.SongID   "
                + "full  join Orders on Orders.OrderID= OrderDetails.OrderID where Songs.ArtistID=?";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, ArtistID);
        List<Object[]> authors = query.getResultList();
        String tam = null;
        List list = new ArrayList();
        for (Object[] a : authors) {
            tam = "[" + a[0] + "/" + a[1] + "/" + a[2] + "/" + a[3] + "/" + a[4] + "/" + a[5] + "/" + a[6] + "/" + a[7] + "/" + a[8] + "/" + a[9] + "]";
            list.add(tam);
            tam = null;
        }
        System.out.println(list);
        return list;
    }

    @Override
    public String albumIdEnd() {
        String id = "";
        Query query = em.createNativeQuery("SELECT TOP 1 FvID FROM FavouriteAlbum ORDER BY FvID DESC");
        for (int i = 0; i < query.getResultList().size(); i++) {
            id = query.getResultList().get(i).toString();
        }
        return id;
    }

    @Override
    public List<Songs> getSong(int id) {
        String sql = "select  *  from Songs    "
                + "where Songs.ArtistID like   "
                + "(select Artists.ArtistID from Artists where Artists.ArtistID =?)";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, id);
        List<Songs> List = new ArrayList<Songs>();
        List = query.getResultList();
        return List;
    }
//truyen len song details

    @Override
    public List listAllSongAndArtist(String idArtists) {
        String sql = "";
        List list = new ArrayList();
        if (idArtists.equals("all")) {
            sql = "select Songs.SongID,Songs.SongName,Songs.Path,Songs.Price,Songs.ArtistID,Songs.Thumbnail,Artists.Fullname "
                    + "from Songs "
                    + "full join Artists on Songs.ArtistID = Artists.ArtistID  "
                    + "where Songs.SongID is not null ORDER BY Songs.SongID DESC";
        } else if (idArtists.equals("top10")) {
            sql = "select top 10 Songs.SongID,Songs.SongName,Songs.Path,Songs.Price,Songs.ArtistID,Songs.Thumbnail,Artists.Fullname,Views,count(SongLikes.AccountID) "
                    + "from Songs "
                    + "full join Artists on Songs.ArtistID = Artists.ArtistID  "
                    + "full join SongLikes on SongLikes.SongID = Songs.SongID "
                    + "where Artists.ArtistID  "
                    + "like (select Artists.ArtistID  from Artists where  Artists.ArtistID = ?) "
                    + "group by Songs.SongID,Songs.SongName,Songs.Path,Songs.Price,Songs.ArtistID,Songs.Thumbnail,Artists.Fullname,Views,Views,count(SongLikes.AccountID)  "
                    + "ORDER BY Views DESC";
        } else {
            sql = "select Songs.SongID,Songs.SongName,Songs.Path,Songs.Price,Songs.ArtistID,Songs.Thumbnail,Artists.Fullname,Views,count(SongLikes.AccountID)  "
                    + "from Songs  "
                    + "full join Artists on Songs.ArtistID = Artists.ArtistID  "
                    + "full join SongLikes on SongLikes.SongID = Songs.SongID  "
                    + "where Artists.ArtistID  "
                    + "like (select Artists.ArtistID  from Artists where  Artists.ArtistID = ?) "
                    + "group by Songs.SongID,Songs.SongName,Songs.Path,Songs.Price,Songs.ArtistID,Songs.Thumbnail,Artists.Fullname,Views  "
                    + "ORDER BY Views DESC ";
        }
        Query query = em.createNativeQuery(sql);
        if (idArtists.equals("all")) {
            List<Object[]> authors = query.getResultList();
            String tam = null;
            for (Object[] a : authors) {
                tam = "[" + a[0] + "/" + a[1] + "/" + a[2] + "/" + a[3] + "/" + a[4] + "/" + a[5] + "/" + a[6] + "]";
                list.add(tam);
                tam = null;
            }
        } else if (idArtists.indexOf("top10") != -1) {
            query.setParameter(1, idArtists.substring(0, idArtists.indexOf("top10")));
            List<Object[]> authors = query.getResultList();
            String tam = null;
            for (Object[] a : authors) {
                tam = "[" + a[0] + "/" + a[1] + "/" + a[2] + "/" + a[3] + "/" + a[4] + "/" + a[5] + "/" + a[6] + "/" + a[7] + "/" + a[8] + "]";
                list.add(tam);
                tam = null;
            }
        } else {
            query.setParameter(1, idArtists);
            List<Object[]> authors = query.getResultList();
            String tam = null;
            for (Object[] a : authors) {
                tam = "[" + a[0] + "/" + a[1] + "/" + a[2] + "/" + a[3] + "/" + a[4] + "/" + a[5] + "/" + a[6] + "/" + a[7] + "/" + a[8] + "]";
                list.add(tam);
                tam = null;
            }
        }
        return list;
    }
}
