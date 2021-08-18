/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Albums;
import entities.Artists;
import entities.Songs;
import static java.util.Collections.list;
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
public class AlbumsFacade extends AbstractFacade<Albums> implements AlbumsFacadeLocal {

    @EJB
    private ArtistsFacadeLocal artistsFacade;

    
    @PersistenceContext(unitName = "sem4Test-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlbumsFacade() {
        super(Albums.class);
    }

    @Override
    public List<Albums> getAlbumses(int artistID, int pageNumber) {
        Query query = em.createQuery("SELECT i FROM Albums i WHERE i.artistID = :artistID");
        Artists artist = artistsFacade.find(artistID);
        query.setParameter("artistID", artist);
        int pageSize = 10;
        query.setMaxResults(pageSize);
        query.setFirstResult((pageNumber - 1) * pageSize);
        return query.getResultList();
    }

    @Override
    public int countAlbumsPage(int artistID) {
        Artists artist = artistsFacade.find(artistID);
        try {
            if (artist.getAlbumsCollection().isEmpty()) {
                return 0;
            } else {
                int count = artist.getAlbumsCollection().size();
                int result = (int) Math.ceil((double) count / 10); // lam tròn
                return result;
            }
        } catch (Exception e) {
            return 0;
        }
    }
    
    @Override
    public int countSongInAlbum(int albumID){
        Query query = em.createQuery("SELECT i FROM Songs i WHERE i.albumID = :albumID");
        Albums album = AlbumsFacade.this.find(albumID);
        query.setParameter("albumID", album);
        return query.getResultList().size();
    }
    
    @Override
    public List<Songs> getSongs(int albumID){
        Query query = em.createQuery("SELECT i FROM Songs i WHERE i.albumID = :albumID");
        Albums album = AlbumsFacade.this.find(albumID);
        query.setParameter("albumID", album);
        return query.getResultList();
    }

    @Override
    public int countAlbums(int artistID) {
        Query query = em.createQuery("SELECT i FROM Albums i WHERE i.artistID = :artistID");
        Artists artist = artistsFacade.find(artistID);
        query.setParameter("artistID", artist);
        return query.getResultList().size();
    }
    
    public void persist(Object object) {
        em.persist(object);
    }
}
