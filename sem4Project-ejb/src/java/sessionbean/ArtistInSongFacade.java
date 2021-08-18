/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.ArtistInSong;
import entities.Artists;
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
public class ArtistInSongFacade extends AbstractFacade<ArtistInSong> implements ArtistInSongFacadeLocal {

    @EJB
    private ArtistsFacadeLocal artistsFacade;

    @PersistenceContext(unitName = "sem4Test-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArtistInSongFacade() {
        super(ArtistInSong.class);
    }

    @Override
    public List<ArtistInSong> getAllSongs(int artistID) {
        Query query = em.createQuery("SELECT p FROM ArtistInSong p WHERE p.artistID = :artistID ORDER BY p.workID DESC");
        Artists artist = artistsFacade.find(artistID);
        query.setParameter("artistID", artist);
        return query.getResultList();
    }

}
