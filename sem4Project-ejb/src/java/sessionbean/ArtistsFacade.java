/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Albums;
import entities.Artists;
import entities.Songs;
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
public class ArtistsFacade extends AbstractFacade<Artists> implements ArtistsFacadeLocal {

    @PersistenceContext(unitName = "sem4Test-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArtistsFacade() {
        super(Artists.class);
    }

    // pagination
    @Override
    public List<Artists> Pagination(int pageNumber) {
        Query query = em.createQuery("SELECT p FROM Artists p ORDER BY p.artistID DESC");
        int pageSize = 10;
        query.setMaxResults(pageSize);
        query.setFirstResult((pageNumber - 1) * pageSize);
        return query.getResultList();
    }

    @Override
    public int countPage() {
        int sum = ArtistsFacade.this.count();
        int result = (int) Math.ceil((double) sum / 10); // lam tròn
        //System.out.println(result);
        return result;
    }

    @Override
    public List<Albums> getAlbums(int artistID) {
        Query query = em.createQuery("SELECT p FROM Albums p WHERE p.artistID = :artistID ORDER BY p.albumID DESC");
        Artists artist = ArtistsFacade.this.find(artistID);
        query.setParameter("artistID", artist);
        return query.getResultList();
    }

    @Override
    public List<Songs> getSongs(int artistID) {
        Query query = em.createQuery("SELECT p FROM Songs p WHERE p.artistID = :artistID ORDER BY p.songID DESC");
        Artists artist = ArtistsFacade.this.find(artistID);
        query.setParameter("artistID", artist);
        return query.getResultList();
    }

    @Override
    public int countAlbums(int artistID) {
        Query query = em.createQuery("SELECT p FROM Albums p WHERE p.artistID = :artistID ORDER BY p.albumID DESC");
        Artists artist = ArtistsFacade.this.find(artistID);
        query.setParameter("artistID", artist);
        return query.getResultList().size();
    }

    @Override
    public int countSongs(int artistID) {
        Query query = em.createQuery("SELECT p FROM Songs p WHERE p.artistID = :artistID");
        Artists artist = ArtistsFacade.this.find(artistID);
        query.setParameter("artistID", artist);
        return query.getResultList().size();
    }
    
     @Override
    public int countFollower(int artistID) {
        Query query = em.createQuery("SELECT p FROM ArtistFollow p WHERE p.artistID = :artistID");
        Artists artist = ArtistsFacade.this.find(artistID);
        query.setParameter("artistID", artist);
        return query.getResultList().size();
    }

    @Override
    public int countArtistInSongs(int artistID) {
        Query query = em.createQuery("SELECT p FROM ArtistInSong p WHERE p.artistID = :artistID");
        Artists artist = ArtistsFacade.this.find(artistID);
        query.setParameter("artistID", artist);
        return query.getResultList().size();
    }

    @Override
    public List<Artists> findByNames(String s) {
        Query query = em.createQuery("SELECT p FROM Artists p WHERE p.nickname LIKE :nickname OR p.fullname LIKE :fullname");
        query.setParameter("nickname", "%" + s + "%");
        query.setParameter("fullname", "%" + s + "%");
        return query.getResultList();
    }

    @Override
    public List listArtistsLikeTop() {
        String sql = "select top 10 Artists.ArtistID,Nickname,Fullname,Avatar "
                + "from Artists "
                + "full join Songs on Songs.ArtistID = Artists.ArtistID "
                + "full join SongLikes on SongLikes.SongID =Songs.SongID "
                + "group by Artists.ArtistID,Nickname,Fullname,Avatar "
                + "order by COUNT(AccountID) DESC ";
        List list = new ArrayList();
        Query query = em.createNativeQuery(sql);
        List<Object[]> authors = query.getResultList();
        String tam = null;
        for (Object[] a : authors) {
            tam = "[" + a[0] + "/" + a[1] + "/" + a[2] + "/" + a[3] + "]";
            list.add(tam);
            tam = null;
        }
        return list;
    }

}
